/*
 * Copyright (c) 2020 com.company.autodealer.web.individualbrowse
 */
package com.company.autodealer.web.individualbrowse;

import com.company.autodealer.service.CountPurchaseRequestsService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Individual;
import com.haulmont.thesis.web.ui.individual.IndividualBrowser;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author pasha_molodoy
 */
public class ExtIndividualBrowser extends IndividualBrowser {


    @Inject
    CountPurchaseRequestsService countPurchaseRequestsService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        individualsTable.addGeneratedColumn("Generated Column", new Table.ColumnGenerator() {
            @Override
            public Component generateCell(Entity entity) {
                Integer integer = countPurchaseRequestsService.countRequests((Individual) entity);

                return new Table.PlainTextCell(integer.toString());
            }
        });
    }

}
