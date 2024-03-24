package com.company.exceed.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "EXCEED_PLANIN_EVENT")
@Entity(name = "exceed_PlaninEvent")
public class PlaninEvent extends StandardEntity {
    private static final long serialVersionUID = 7475247288449267339L;

    @Column(name = "REG_NUMBER", length = 10)
    private String regNumber;

    @Column(name = "VEHICLE_NUMBER", length = 20)
    private String vehicleNumber;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "STATE")
    private Integer state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_DATE")
    private Date eventDate;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public VehicleState getState() {
        return state == null ? null : VehicleState.fromId(state);
    }

    public void setState(VehicleState state) {
        this.state = state == null ? null : state.getId();
    }

    public VehicleStatus getStatus() {
        return status == null ? null : VehicleStatus.fromId(status);
    }

    public void setStatus(VehicleStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}