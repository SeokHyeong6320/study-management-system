package com.zerobase.fastlms.aahomework;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/list.do")
    public String bannerList() {

        return "admin/banner/list";
    }

    @GetMapping("/add.do")
    public String bannerAddFrom() {

        return "admin/banner/add";
    }

    @PostMapping("/add.do")
    public String bannerAdd() {

        return "redirect:/admin/banner/list.do";
    }

    

}
