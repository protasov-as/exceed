package com.company.exceed.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum WeightCapacity implements EnumClass<Integer> {

    VAN(1),
    TRUCK(2);

    private Integer id;

    WeightCapacity(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static WeightCapacity fromId(Integer id) {
        for (WeightCapacity at : WeightCapacity.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}