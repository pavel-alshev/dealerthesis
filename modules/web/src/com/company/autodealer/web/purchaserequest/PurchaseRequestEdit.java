package com.company.autodealer.web.purchaserequest;

import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.gui.components.WebButton;
import com.haulmont.thesis.web.actions.PrintReportAction;
import com.haulmont.thesis.web.ui.basicdoc.editor.AbstractDocEditor;
import com.haulmont.workflow.core.app.WfUtils;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import org.apache.commons.lang.StringUtils;
import com.haulmont.cuba.core.entity.Entity;

import java.util.Map;

import com.company.autodealer.entity.PurchaseRequest;

import javax.inject.Inject;

public class PurchaseRequestEdit extends AbstractDocEditor<PurchaseRequest> {

    @Inject
    UserSession userSession;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    @Override
    protected String getHiddenTabsConfig() {
        return "correspondenceHistoryTab,docLogTab,cardLinksTab,processTab,securityTab,docTransferLogTab,cardProjectsTab,versionsTab,openHistoryTab";
    }

    @Override
    public void setItem(Entity item) {
        super.setItem(item);
        printButton.addAction(new PrintReportAction("printExecutionList", this, "printDocExecutionListReportName"));
    }

    @Override
    protected Component createState() {
        if (WfUtils.isCardInState(getItem(), "New") || StringUtils.isEmpty(getItem().getState())) {
            Label label = componentsFactory.createComponent(Label.NAME);
            label.setValue(StringUtils.isEmpty(getItem().getState()) ? "" : getItem().getLocState());
            return label;
        } else {
            return super.createState();
        }
    }

    @Override
    protected void fillHiddenTabs() {
        hiddenTabs.put("office", getMessage("office"));
        hiddenTabs.put("attachmentsTab", getMessage("attachmentsTab"));
        hiddenTabs.put("docTreeTab", getMessage("docTreeTab"));
        hiddenTabs.put("cardCommentTab", getMessage("cardCommentTab"));
        super.fillHiddenTabs();
    }

    @Override
    protected void initActionsFrame() {
        super.initActionsFrame();

        String state = ",Proverka_zayavki,";

        if (userIsManager() && isState(state) && !getItem().getIsPaid()) {

            for(Component component : actionsFrame.getOwnComponents()) {
                if (component.getId().equals("Proverka_zayavki.Provereno_BTN")) {
                    component.setEnabled(false);
                }
            }
        }
    }

    private boolean userIsManager() {
        return userSession.getRoles().contains("BankOperator");
    }

    private boolean isState(String state) {
        String requestState = getItem().getState();
        return requestState.equals(state);
    }
}