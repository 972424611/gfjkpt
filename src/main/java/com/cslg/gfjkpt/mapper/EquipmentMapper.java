package com.cslg.gfjkpt.mapper;

import com.cslg.gfjkpt.model.Equipment;

import java.util.List;

public interface EquipmentMapper {

    void insertEquipment(Equipment equipment);

    void updateEquipment(Equipment equipment);

    void deleteEquipmentById(int id);

    List<Equipment> selectEquipmentList();
}
