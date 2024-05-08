package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.aahomework.BannerDto;
import com.zerobase.fastlms.aahomework.BannerRepository;
import com.zerobase.fastlms.aahomework.BannerService;
import com.zerobase.fastlms.components.MailComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents;
    private final BannerService bannerService;
    
    @GetMapping("/")
    public String index(Model model) {

        List<BannerDto> findBanners = bannerService.showBannerMainPage();
        model.addAttribute("bannerList", findBanners);

        return "index";
    }
    
    
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
