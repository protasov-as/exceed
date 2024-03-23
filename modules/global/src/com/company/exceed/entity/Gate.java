package com.company.exceed.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Table(name = "EXCEED_GATE")
@Entity(name = "exceed_Gate")
public class Gate extends StandardEntity {
    private static final long serialVersionUID = -5027790555893356207L;

    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    @Positive(message = "{msg://exceed_Gate.number.validation.Positive}")
    @Max(message = "{msg://exceed_Gate.number.validation.Max}", value = 99999)
    private Integer number;

    @Temporal(TemporalType.TIME)
    @NotNull
    @Column(name = "AVAILABLE_FROM", nullable = false)
    private Date availableFrom;

    @Temporal(TemporalType.TIME)
    @NotNull
    @Column(name = "AVAILABLE_TO", nullable = false)
    private Date availableTo;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}