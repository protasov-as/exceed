package com.company.exceed.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "EXCEED_CLIENT")
@Entity(name = "exceed_Client")
@NamePattern("%s|name")
public class Client extends StandardEntity {
    private static final long serialVersionUID = 6755477358573960394L;

    @NotNull
    @Column(name = "CODE", nullable = false, unique = true)
    private Integer code;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "ADDRESS", length = 200)
    private String address;

    @Column(name = "CONTACT", length = 100)
    private String contact;

    @JoinTable(name = "EXCEED_PLAN_IN_CLIENT_LINK",
            joinColumns = @JoinColumn(name = "CLIENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLAN_IN_ID"))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PlanIn> planIns;

    public List<PlanIn> getPlanIns() {
        return planIns;
    }

    public void setPlanIns(List<PlanIn> planIns) {
        this.planIns = planIns;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}