package com.cslg.gfjkpt.service;

import com.cslg.gfjkpt.beans.EquipmentParam;
import com.cslg.gfjkpt.vo.EquipmentVo;

import java.util.List;

public interface EquipmentService {

    void addEquipment(EquipmentParam equipmentParam);

    void updateEquipment(EquipmentParam equipmentParam);

    void deleteEquipmentById(int id);

    List<EquipmentVo> getEquipments();
}
