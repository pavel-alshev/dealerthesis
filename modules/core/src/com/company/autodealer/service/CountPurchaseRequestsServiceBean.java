/*
 * Copyright (c) 2020 com.company.autodealer.service
 */
package com.company.autodealer.service;

import com.company.autodealer.entity.PurchaseRequest;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.View;
import com.haulmont.thesis.core.entity.Contractor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author pasha_molodoy
 */
@Service(CountPurchaseRequestsService.NAME)
public class CountPurchaseRequestsServiceBean implements CountPurchaseRequestsService {

    @Inject
    private Persistence persistence;

    @Inject
    private Metadata metadata;

    @Override
    public Integer countRequests(Contractor contractor) {
        Transaction tx = persistence.createTransaction();
        try {
            EntityManager manager = persistence.getEntityManager();
            TypedQuery<PurchaseRequest> query = manager.
                    createQuery("select p from auto$PurchaseRequest p " +
                            "where p.contractor.id = :contractorId", PurchaseRequest.class);
            query.setView(metadata.getViewRepository().getView(PurchaseRequest.class, View.MINIMAL));
            query.setParameter("contractorId", contractor.getId());
            List<PurchaseRequest> requests = query.getResultList();
            tx.commit();
            if (CollectionUtils.isNotEmpty(requests)) {
                return requests.size();
            } else {
                return 0;
            }
        } finally {
            tx.end();
        }
    }

}