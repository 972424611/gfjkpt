package com.cslg.gfjkpt.controller;

import com.cslg.gfjkpt.beans.EquipmentParam;
import com.cslg.gfjkpt.common.ResultJson;
import com.cslg.gfjkpt.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    //后台用@RequestBody的话，前端ajax要加上contentType: "application/json",
    @ResponseBody
    @RequestMapping(value = "/add")
    private ResultJson add(@RequestBody EquipmentParam equipmentParam) {
        System.out.println(equipmentParam.getIpAddress());
        equipmentService.addEquipment(equipmentParam);
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    private ResultJson update(EquipmentParam equipmentParam) {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    private ResultJson delete(@RequestParam("id") Integer id) {
        equipmentService.deleteEquipmentById(id);
        return ResultJson.success();
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    private ResultJson list() {
        return ResultJson.success(equipmentService.getEquipments());
    }
}
