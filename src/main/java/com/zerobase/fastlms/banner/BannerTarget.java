package com.zerobase.fastlms.banner;

import lombok.Getter;

public enum BannerTarget {

    NEW("새 창으로 열기"),
    CURRENT("현재 창으로 열기");

    @Getter
    private String description;

    BannerTarget(String description) {
        this.description = description;
    }
}
