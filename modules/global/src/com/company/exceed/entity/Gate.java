package com.company.exceed.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "EXCEED_GATE")
@Entity(name = "exceed_Gate")
@NamePattern("%s|number")
public class Gate extends StandardEntity {
    private static final long serialVersionUID = -5027790555893356207L;

    @Column(name = "NUMBER_", nullable = false, unique = true, length = 5)
    @NotNull
    private String number;

    @Temporal(TemporalType.TIME)
    @NotNull
    @Column(name = "AVAILABLE_FROM", nullable = false)
    private Date availableFrom;

    @Temporal(TemporalType.TIME)
    @NotNull
    @Column(name = "AVAILABLE_TO", nullable = false)
    private Date availableTo;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public Date getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

}