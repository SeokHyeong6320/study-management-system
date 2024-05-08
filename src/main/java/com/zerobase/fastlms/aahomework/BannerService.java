package com.zerobase.fastlms.aahomework;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BannerService {
    List<BannerDto> getAllBanners();

    void addBanner(HttpServletRequest request, MultipartFile file, BannerInput param) throws IOException;

    void updateBanner(Long id, BannerInput param);

}
