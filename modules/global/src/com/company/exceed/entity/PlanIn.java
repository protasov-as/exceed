package com.company.exceed.entity;

import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name = "EXCEED_PLAN_IN")
@Entity(name = "exceed_PlanIn")
public class PlanIn extends StandardEntity {
    private static final long serialVersionUID = 5559725482407213103L;

    @Column(name = "REG_NUMBER", nullable = false, length = 10)
    @NotNull
    private String regNumber;

    @Column(name = "ORDER_NUMBER", length = 50)
    private String orderNumber;

    @Column(name = "ARRIVAL_PLAN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivalPlanTime;

    @JoinTable(name = "EXCEED_PLAN_IN_CLIENT_LINK",
            joinColumns = @JoinColumn(name = "PLAN_IN_ID"),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID"))
    @ManyToMany
    private List<Client> contractor;

    @Column(name = "VEHICLE_NUMBER", length = 20)
    private String vehicleNumber;

    @Column(name = "WEIGHT_CAPACITY")
    private Integer weightCapacity;

    @Column(name = "DRIVER_NAME", length = 100)
    private String driverName;

    @Column(name = "DRIVER_PHONE", length = 10)
    private String driverPhone;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "STATE")
    private Integer state;

    @NumberFormat(pattern = "10")
    @Column(name = "GATE")
    @Digits(integer = 10, fraction = 0)
    private Integer gate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REG_DATE")
    private Date regDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "AT_GATES_DATE")
    private Date atGatesDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEPART_ACCEPTED_DATE")
    private Date departAcceptedDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DEPART_DATE")
    private Date departDate;

    public List<Client> getContractor() {
        return contractor;
    }

    public void setContractor(List<Client> contractor) {
        this.contractor = contractor;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public Date getDepartAcceptedDate() {
        return departAcceptedDate;
    }

    public void setDepartAcceptedDate(Date departAcceptedDate) {
        this.departAcceptedDate = departAcceptedDate;
    }

    public Date getAtGatesDate() {
        return atGatesDate;
    }

    public void setAtGatesDate(Date atGatesDate) {
        this.atGatesDate = atGatesDate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getGate() {
        return gate;
    }

    public void setGate(Integer gate) {
        this.gate = gate;
    }

    public VehicleState getState() {
        return state == null ? null : VehicleState.fromId(state);
    }

    public void setState(VehicleState state) {
        this.state = state == null ? null : state.getId();
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public VehicleStatus getStatus() {
        return status == null ? null : VehicleStatus.fromId(status);
    }

    public void setStatus(VehicleStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public WeightCapacity getWeightCapacity() {
        return weightCapacity == null ? null : WeightCapacity.fromId(weightCapacity);
    }

    public void setWeightCapacity(WeightCapacity weightCapacity) {
        this.weightCapacity = weightCapacity == null ? null : weightCapacity.getId();
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setArrivalPlanTime(Date arrivalPlanTime) {
        this.arrivalPlanTime = arrivalPlanTime;
    }

    public Date getArrivalPlanTime() {
        return arrivalPlanTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

}