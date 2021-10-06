package com.docom.pingenerator.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pin_history")
public class PinHistory {
    @Id
    @SequenceGenerator(
            sequenceName = "pin_sequence",
            name = "pin_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY,
            generator = "pin_sequence"
    )
    @Column(name = "pin_id")
    private Long pinId;
    private String pin;
    private String msisdn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date generation_time;
    private int validated;
    private int retries;
    public PinHistory(){}

    public PinHistory(String pin, String msisdn,
                      Date generation_time, int validated, int retries) {
        this.pinId = pinId;
        this.pin = pin;
        this.msisdn = msisdn;
        this.generation_time = generation_time;
        this.validated = validated;
        this.retries = retries;
    }

    public int getValidated() {
        return validated;
    }

    public void setValidated(int validated) {
        this.validated = validated;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getPinId() {
        return pinId;
    }

    public void setPinId(Long pinId) {
        this.pinId = pinId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getGeneration_time() {
        return generation_time;
    }

    public void setGeneration_time(Date generation_time) {
        this.generation_time = generation_time;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }
}
