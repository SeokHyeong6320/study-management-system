package com.zerobase.fastlms.aahomework;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BannerService {

    void addBanner(HttpServletRequest request, MultipartFile file, BannerInput param) throws IOException;

    void updateBanner(Long id, MultipartFile file, BannerInput param);

    List<BannerDto> list(BannerParam parameter);

    boolean del(String idList);

    List<BannerDto> showBannerMainPage();
}
