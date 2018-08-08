package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.PreviewService;
import com.cslg.gfjkpt.vo.HomePageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/preview")
public class PreviewController {

    private final PreviewService previewService;

    @Autowired
    public PreviewController(PreviewService previewService) {
        this.previewService = previewService;
    }

    @ResponseBody
    @RequestMapping("/list")
    public ResultJson list() {
        List<HomePageVo> homePageVoList = previewService.getHomePageList();
        return ResultJson.success(homePageVoList);
    }
}
