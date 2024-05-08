package com.zerobase.fastlms.aahomework;

import com.zerobase.fastlms.admin.dto.MemberDto;
import com.zerobase.fastlms.admin.model.MemberParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface BannerService {

    void addBanner(HttpServletRequest request, MultipartFile file, BannerInput param) throws IOException;

    void updateBanner(Long id, BannerInput param);

    List<BannerDto> list(BannerParam parameter);

}
