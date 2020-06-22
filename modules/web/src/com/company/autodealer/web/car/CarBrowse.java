package com.company.autodealer.web.car;

import java.util.Map;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.thesis.web.ui.basicdoc.browse.AbstractDocBrowser;
import com.company.autodealer.entity.Car;

public class CarBrowse extends AbstractDocBrowser<Car> {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        entityName = "autodealer$Car";
    }
}