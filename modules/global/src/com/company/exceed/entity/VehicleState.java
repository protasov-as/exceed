package com.company.exceed.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VehicleState implements EnumClass<Integer> {

    WAITING_GATES(5),
    AT_GATES(20),
    DEPART_ACCEPTED(50);

    private Integer id;

    VehicleState(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static VehicleState fromId(Integer id) {
        for (VehicleState at : VehicleState.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}