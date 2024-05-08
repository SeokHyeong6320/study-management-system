package com.zerobase.fastlms.aahomework;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Override
    @Transactional(readOnly = true)
    public List<BannerDto> getAllBanners() {

        return bannerRepository.findAll()
                .stream()
                .map(BannerDto::fromEntity)
                .collect(Collectors.toList());
    }

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
                .build();


        if (!file.isEmpty()) {
            String fullFilePath = fileDir + file.getOriginalFilename();
            file.transferTo(new File(fullFilePath));
            banner.setFile(fullFilePath);
        }

        bannerRepository.save(banner);
    }

    @Override
    public void updateBanner(Long id, BannerInput param) {

        Banner findBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("couldn't find banner. id->" + id));

        findBanner.updateBanner(param);
    }


}
