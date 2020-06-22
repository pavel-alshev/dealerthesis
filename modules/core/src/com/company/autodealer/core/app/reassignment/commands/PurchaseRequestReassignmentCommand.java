/*
 * Copyright (c) 2020 com.company.autodealer.core.app.reassignment.commands
 */
package com.company.autodealer.core.app.reassignment.commands;


import com.haulmont.thesis.core.app.reassignment.commands.AbstractDocReassignmentCommand;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import com.company.autodealer.entity.PurchaseRequest;

/**
 * @author pasha_molodoy
 */
@ManagedBean(PurchaseRequestReassignmentCommand.NAME)
public class PurchaseRequestReassignmentCommand extends AbstractDocReassignmentCommand<PurchaseRequest> {
    protected static final String NAME = "purchaserequest_reassignment_command";

    @PostConstruct
    protected void postInit() {
        type = "PurchaseRequest";
        docQuery = String.format(DOC_QUERY_TEMPLATE, "autodealer$PurchaseRequest");
    }

    @Override
    public String getName() {
        return NAME;
    }
}