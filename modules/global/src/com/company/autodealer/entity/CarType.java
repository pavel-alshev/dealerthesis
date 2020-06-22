/*
 * Copyright (c) 2020 com.company.autodealer.entity
 */
package com.company.autodealer.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;

/**
 * @author pasha_molodoy
 */
public enum CarType implements EnumClass<Integer> {
    CROSSOVER(10),
    UNIVERSAL(20),
    SEDAN(30);

    private Integer id;

    CarType(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CarType fromId(Integer id) {
        for (CarType at : CarType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}