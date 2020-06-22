/*
 * Copyright (c) 2020 com.company.autodealer.web.companybrowse
 */
package com.company.autodealer.web.companybrowse;

import com.company.autodealer.service.CountPurchaseRequestsService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.thesis.core.entity.Company;
import com.haulmont.thesis.web.ui.company.CompanyBrowser;

import javax.inject.Inject;
import java.util.Map;

/**
 * @author pasha_molodoy
 */
public class ExtCompanyBrowser extends CompanyBrowser {


    @Inject
    CountPurchaseRequestsService countPurchaseRequestsService;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        companiesTable.addGeneratedColumn("Generated Column", new Table.ColumnGenerator() {
            @Override
            public Component generateCell(Entity entity) {
                Integer integer = countPurchaseRequestsService.countRequests((Company) entity);

                return new Table.PlainTextCell(integer.toString());
            }
        });
    }

}
