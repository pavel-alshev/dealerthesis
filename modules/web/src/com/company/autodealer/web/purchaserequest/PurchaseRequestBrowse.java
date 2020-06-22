package com.company.autodealer.web.purchaserequest;

import java.util.Map;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.autodealer.entity.PurchaseRequest;

public class PurchaseRequestBrowse extends AbstractDocBrowser<PurchaseRequest> {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "autodealer$PurchaseRequest";
    }
}