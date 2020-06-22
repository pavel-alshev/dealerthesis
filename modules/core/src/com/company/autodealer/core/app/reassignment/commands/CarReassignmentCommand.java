/*
 * Copyright (c) 2020 com.company.autodealer.core.app.reassignment.commands
 */
package com.company.autodealer.core.app.reassignment.commands;


import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.company.autodealer.entity.Car;

/**
 * @author pasha_molodoy
 */
@ManagedBean(CarReassignmentCommand.NAME)
public class CarReassignmentCommand extends AbstractDocReassignmentCommand<Car> {
    protected static final String NAME = "car_reassignment_command";

    @PostConstruct
    protected void postInit() {
        type = "Car";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "autodealer$Car");
    }

    @Override
    public String getName() {
        return NAME;
    }
}