package com.zerobase.fastlms.aahomework;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BannerDto {

    private String name;

    private String file;

    private String url;

    private BannerTarget target;

    private int order;

    private Boolean openYn;

    private LocalDateTime addDt;

    long totalCount;
    long seq;


    public static BannerDto fromEntity(Banner banner) {

        return BannerDto.builder()
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
