package com.zerobase.fastlms.aahomework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(name = "banner_name")
    private String name;

    @Column(name = "banner_file_url")
    private String url;

    @Column(name = "banner_open_target")
    @Enumerated(value = EnumType.STRING)
    private BannerTarget target;

    @Column(name = "banner_order")
    private int order;

    @Column(name = "banner_opne_yn")
    private boolean openYn;
}
