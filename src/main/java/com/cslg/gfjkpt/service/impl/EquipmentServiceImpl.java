package com.cslg.gfjkpt.service.impl;

import com.cslg.gfjkpt.beans.EquipmentParam;
import com.cslg.gfjkpt.mapper.EquipmentMapper;
import com.cslg.gfjkpt.model.Equipment;
import com.cslg.gfjkpt.service.EquipmentService;
import com.cslg.gfjkpt.vo.EquipmentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public void addEquipment(EquipmentParam equipmentParam) {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(equipmentParam, equipment);
        equipmentMapper.insertEquipment(equipment);
    }

    @Override
    public void updateEquipment(EquipmentParam equipmentParam) {

    }

    @Override
    public void deleteEquipmentById(int id) {
        equipmentMapper.deleteEquipmentById(id);
    }

    @Override
    public List<EquipmentVo> getEquipments() {
        List<EquipmentVo> equipmentVoList = new ArrayList<>();
        List<Equipment> equipmentList = equipmentMapper.selectEquipmentList();
        for(Equipment equipment : equipmentList) {
            EquipmentVo equipmentVo = new EquipmentVo();
            BeanUtils.copyProperties(equipment, equipmentVo);
            equipmentVoList.add(equipmentVo);
        }
        return equipmentVoList;
    }
}
