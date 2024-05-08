package com.zerobase.fastlms.banner.controller;

import com.zerobase.fastlms.banner.*;
import com.zerobase.fastlms.banner.dto.BannerDto;
import com.zerobase.fastlms.banner.entity.Banner;
import com.zerobase.fastlms.banner.model.BannerInput;
import com.zerobase.fastlms.banner.model.BannerParam;
import com.zerobase.fastlms.banner.repository.BannerRepository;
import com.zerobase.fastlms.banner.service.BannerService;
import com.zerobase.fastlms.course.controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/banner")
@RequiredArgsConstructor
public class BannerController extends BaseController {

    private final BannerService bannerService;
    private final BannerRepository bannerRepository;

    @ModelAttribute("bannerTarget")
    public List<BannerTarget> bannerTargets() {
        ArrayList<BannerTarget> bannerTargets = new ArrayList<>();
        bannerTargets.add(BannerTarget.NEW);
        bannerTargets.add(BannerTarget.CURRENT);
        return bannerTargets;
    }

    @GetMapping("/list.do")
    public String bannerList(Model model, BannerParam parameter) {

        parameter.init();
        List<BannerDto> list = bannerService.list(parameter);

        long totalCount = 0;
        if (list != null && list.size() > 0) {
            totalCount = list.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPaperHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("bannerList", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "admin/banner/list";
    }


    @GetMapping("/add.do")
    public String bannerAddFrom(Model model) {
        model.addAttribute("bannerInput", new BannerInput());
        model.addAttribute("isAdd", true);

        return "admin/banner/add";
    }

    @PostMapping("/add.do")
    public String addBanner(
            HttpServletRequest request,
            @RequestParam MultipartFile file,
             BannerInput bannerInput
    ) {


        try {
            bannerService.addBanner(request, file, bannerInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/admin/banner/list.do";
    }

   @GetMapping("/update.do")
    public String bannerUpdateFrom(
            @RequestParam Long id,
            Model model
   ) {

        Banner findBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException
                        ("couldn't find banner. id->" + id));

        model.addAttribute("isAdd", false);
        model.addAttribute("bannerInput", findBanner);

        return "admin/banner/add";
    }

    @PostMapping("/update.do")
    public String bannerUpdate(
            @RequestParam Long id,
            @RequestParam MultipartFile file,
            BannerInput param
    ) {


        bannerService.updateBanner(id, file, param);


        return "redirect:/admin/banner/list.do";
    }

    @PostMapping("/delete.do")
    public String del(Model model, HttpServletRequest request
            , BannerInput parameter) {

        boolean result = bannerService.del(parameter.getIdList());

        return "redirect:/admin/banner/list.do";
    }


}
