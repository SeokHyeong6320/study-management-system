package com.zerobase.fastlms.aahomework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerDto {

    private String name;

    private String url;

    private BannerTarget target;

    private int order;

    private boolean openYn;


    public static BannerDto fromEntity(Banner banner) {

        return BannerDto.builder()
                .name(banner.getName())
                .url(banner.getUrl())
                .target(banner.getTarget())
                .order(banner.getOrder())
                .openYn(banner.isOpenYn())
                .build();
    }
}
