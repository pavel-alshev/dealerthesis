/*
 * Copyright (c) 2020 com.company.autodealer.entity
 */
package com.company.autodealer.entity;


/**
 * @author pasha_molodoy
 */
import com.haulmont.cuba.core.entity.annotation.EnableRestore;
import com.haulmont.cuba.core.entity.annotation.TrackEditScreenHistory;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.Column;
import com.haulmont.cuba.core.entity.StandardEntity;

@NamePattern("%s|name")
@Table(name = "AUTODEALER_CAR_MODEL")
@Entity(name = "autodealer$CarModel")
@EnableRestore
@TrackEditScreenHistory
public class CarModel extends StandardEntity {
    private static final long serialVersionUID = 4459150005482011397L;

    @Column(name = "NAME", nullable = false, length = 50)
    protected String name;

    @Column(name = "CODE", nullable = false, length = 3)
    protected String code;

    @Column(name = "COMMENT_", length = 50)
    protected String comment;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }


}