/*
 * Copyright (c) 2020 com.company.autodealer.service
 */
package com.company.autodealer.service;

import com.haulmont.thesis.core.entity.Contractor;

/**
 * @author pasha_molodoy
 */
public interface CountPurchaseRequestsService {
    String NAME = "autodealer_CountPurchaseRequestsService";

    Integer countRequests(Contractor contractor);
}