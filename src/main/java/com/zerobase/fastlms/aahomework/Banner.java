package com.zerobase.fastlms.aahomework;

import lombok.*;

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

    @Column(name = "banner_opne_yn")
    private boolean openYn;

    public void updateBanner(BannerInput param) {
        this.name = param.getName();
        this.url = param.getUrl();
        this.target = param.getTarget();
        this.order = param.getOrder();
        this.openYn = param.isOpenYn();
    }

}
