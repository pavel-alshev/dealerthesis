

/*
 * Copyright (c) 2020 com.haulmont.thesis.core.entity
 */
package com.company.autodealer.entity;

import com.haulmont.chile.core.datatypes.Datatypes;
import com.haulmont.chile.core.model.MetaClass;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.Messages;

import com.haulmont.thesis.core.entity.Doc;

import com.haulmont.thesis.core.global.EntityCopyUtils;
import org.apache.commons.lang.StringUtils;
import com.haulmont.thesis.core.entity.HasDetailedDescription;
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.DiscriminatorValue;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.haulmont.cuba.core.entity.annotation.Listeners;

/**
 * @author pasha_molodoy
 */
@Listeners("thesis_DocEntityListener")
@PrimaryKeyJoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
@DiscriminatorValue("1100")
@Table(name = "AUTODEALER_CAR")
@Entity(name = "autodealer$Car")
@EnableRestore
@TrackEditScreenHistory
public class Car extends Doc implements HasDetailedDescription {
    private static final long serialVersionUID = -154533472462792699L;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CAR_MODEL_ID")
    protected CarModel carModel;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "YEAR_OF_ISSUE", length = 50)
    protected Integer yearOfIssue;

    @Column(name = "PRICE", nullable = false, length = 50)
    protected BigDecimal price;

    @Column(name = "CAR_TYPE")
    protected Integer carType;

    public void setCarType(CarType carType) {
        this.carType = carType == null ? null : carType.getId();
    }

    public CarType getCarType() {
        return carType == null ? null : CarType.fromId(carType);
    }


    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public void copyFrom(Doc srcDoc, Set<com.haulmont.cuba.core.entity.Entity> toCommit, boolean copySignatures,
                         boolean onlyLastAttachmentsVersion, boolean useOriginalAttachmentCreatorAndCreateTs,
                         boolean copyAllVersionMainAttachment) {
        super.copyFrom(srcDoc, toCommit, copySignatures, onlyLastAttachmentsVersion,
                useOriginalAttachmentCreatorAndCreateTs, copyAllVersionMainAttachment);
        Metadata metadata = AppBeans.get(Metadata.NAME);
        MetaClass metaClass = metadata.getClassNN(getClass());
        EntityCopyUtils.copyProperties(srcDoc, this, metaClass.getOwnProperties(), toCommit);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String getDetailedDescription(Locale locale, boolean includeState, boolean includeShortInfo) {
        Messages messages = AppBeans.get(Messages.NAME);
        String dateFormat = Datatypes.getFormatStrings(locale).getDateFormat();
        String description;
        String locState = getLocState(locale);

        description =
            getDocKind().getName() + " "
                + (StringUtils.isBlank(getNumber()) ? "" : messages.getMessage(Doc.class, "notification.number", locale) + " " + getNumber() + " ")
                + (getDate() == null ? "" : messages.getMessage(Doc.class, "notification.from", locale) + " "
                + new SimpleDateFormat(dateFormat).format(getDate()))
                + (includeState && StringUtils.isNotBlank(locState) ? " [" + locState + "]" : "");


        if (includeShortInfo && (StringUtils.isNotBlank(getTheme()) || StringUtils.isNotBlank(getComment()))) {
            int length = description.length();
            int infoLength = MAX_SUBJECT_LENGTH - length;

            if (infoLength < MIN_SHORT_INFO_LENGTH) return description;

            String shortInfo = StringUtils.defaultIfBlank(getTheme(), getComment());
            return description + " - " + StringUtils.abbreviate(shortInfo, infoLength);
        } else {
            return description;
        }
    }

    
}