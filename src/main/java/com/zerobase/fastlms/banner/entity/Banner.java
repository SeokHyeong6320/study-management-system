package com.zerobase.fastlms.banner.entity;

import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerTarget;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Banner {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long BannerId;

    @Column(name = "banner_name")
    private String name;

    @Setter
    @Column(name = "banner_file")
    private String file;

    @Column(name = "banner_file_url")
    private String url;

    @Column(name = "banner_open_target")
    @Enumerated(value = EnumType.STRING)
    private BannerTarget target;

    @Column(name = "banner_order")
    private int order;

    @Column(name = "banner_open_yn")
    private Boolean openYn;

    @Column(name = "banner_add_dt")
    private LocalDateTime addDt;

    public void updateBanner(BannerInput param) {
        this.name = param.getName();
        this.url = param.getUrl();
        this.target = param.getTarget();
        this.order = param.getOrder();
        this.openYn = param.isOpenYn();
    }

}
