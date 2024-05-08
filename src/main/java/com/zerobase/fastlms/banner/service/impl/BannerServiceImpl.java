package com.zerobase.fastlms.banner.service.impl;

import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final ServletContext servletContext;
    private final BannerMapper bannerMapper;

    @Override
    public void addBanner(
            HttpServletRequest request, MultipartFile file, BannerInput param
    ) throws IOException {

        Banner banner = Banner.builder()
                .name(param.getName())
                .url(param.getUrl())
                .target(param.getTarget())
                .order(param.getOrder())
                .openYn(param.isOpenYn())
                .addDt(LocalDateTime.now())
                .build();

        saveFile(file, banner);

        bannerRepository.save(banner);
    }

    @Override
    public void updateBanner(Long id, MultipartFile file, BannerInput param) {

        Banner findBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("couldn't find banner. id->" + id));

        try {
            if (!file.isEmpty()) {
                saveFile(file, findBanner);
            }
        } catch (IOException e) {
            throw new RuntimeException("occur exception during updating file");
        }

        findBanner.updateBanner(param);
    }

    private void saveFile(MultipartFile file, Banner banner) throws IOException {

        if (!file.isEmpty()) {

//            ClassPathResource resource
            String realPath = servletContext.getRealPath("/img/banner/");

            String fullFilePath = realPath + UUID.randomUUID() + getExtractName(file);
            file.transferTo(new File(fullFilePath));

            int webappIdx = fullFilePath.lastIndexOf("main/webapp");
            String relativePath = fullFilePath.substring(webappIdx + 11);

            banner.setFile(relativePath);
        }
    }

    /**
     * 파일명 같을 경우 대비해 파일명 암호화
     */
    private String getExtractName(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            return "";
        }

        int position = originalFilename.lastIndexOf(".");

        return originalFilename.substring(position);
    }


    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto dto : list) {
                dto.setTotalCount(totalCount);

                dto.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
//        return memberRepository.findAll();
    }

    @Override
    public boolean del(String idList) {
        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String x : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(x);
                } catch (Exception e) {
                }

                if (id > 0) {

                    Banner findBanner = bannerRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException
                                    ("couldn't find banner"));

                    String fullFilePath =
                            servletContext.getRealPath("") + findBanner.getFile();

                    File file = new File(fullFilePath);
                    file.delete();

                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> showBannerMainPage() {
        List<BannerDto> findBanners = bannerRepository.findAll().stream().map(BannerDto::fromEntity).collect(Collectors.toList());
        findBanners.sort(new Comparator<BannerDto>() {
            @Override
            public int compare(BannerDto o1, BannerDto o2) {
                int order1 = o1.getOrder();
                int order2 = o2.getOrder();

                return order1 - order2 > 0 ? 1 : -1;
            }
        });

        return findBanners;
    }


}
