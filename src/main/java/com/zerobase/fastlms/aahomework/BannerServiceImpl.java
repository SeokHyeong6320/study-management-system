package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;
    private final ServletContext servletContext;
    private final BannerMapper bannerMapper;

    @Value("${file.dir}")
    private String fileDir;



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
    public void updateBanner(Long id, BannerInput param) {

        Banner findBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("couldn't find banner. id->" + id));

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
    @Transactional(readOnly = true)
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


}
