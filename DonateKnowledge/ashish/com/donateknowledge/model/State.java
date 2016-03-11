package com.donateknowledge.model;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="STATE")
public class State implements java.io.Serializable {
    @Id
    @GeneratedValue
    @Column(name="STATENO")
    private Integer stateNo;
    @Column(name="STATE")
    @NotEmpty(message="State name is mandatory")
    private String state;

    public Integer getStateNo() {
        return stateNo;
    }
    public void setStateNo(Integer stateNo) {
        this.stateNo = stateNo;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
