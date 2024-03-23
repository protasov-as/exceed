package com.company.exceed.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum VehicleStatus implements EnumClass<Integer> {

    PLANNED(10),
    REGISTERED(20),
    GATES(30),
    DEPART_ACCEPTED(50),
    DEPARTED(90);

    private Integer id;

    VehicleStatus(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static VehicleStatus fromId(Integer id) {
        for (VehicleStatus at : VehicleStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}