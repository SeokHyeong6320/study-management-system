package com.zerobase.fastlms.aahomework;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService{

    private final BannerRepository bannerRepository;
}
