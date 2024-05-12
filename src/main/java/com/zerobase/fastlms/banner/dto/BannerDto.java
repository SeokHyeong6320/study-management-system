package com.zerobase.fastlms.banner.dto;

import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerTarget;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BannerDto {

    private Long id;

    private String name;

    private String file;

    private String url;

    private BannerTarget target;

    private int order;

    private Boolean openYn;

    private LocalDateTime addDt;

    private Boolean delFlag;

    long totalCount;
    long seq;


    public static BannerDto fromEntity(Banner banner) {

        return BannerDto.builder()
                .id(banner.getBannerId())
                .name(banner.getName())
                .file(banner.getFile())
                .url(banner.getUrl())
                .target(banner.getTarget())
                .order(banner.getOrder())
                .openYn(banner.getOpenYn())
                .addDt(banner.getAddDt())
                .build();
    }
}
