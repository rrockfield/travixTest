package com.travix.medusa.busyflights.provider.toughjet;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

public class ToughJetRequest {

    @NotNull
    private String from;
    @NotNull
    private String to;
    @NotNull
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private String outboundDate;
    @NotNull
    @DateTimeFormat(pattern="YYYY-MM-dd")
    private String inboundDate;
    @Max(4)
    @NotNull
    private int numberOfAdults;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public void setOutboundDate(final String outboundDate) {
        this.outboundDate = outboundDate;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public void setInboundDate(final String inboundDate) {
        this.inboundDate = inboundDate;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(final int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }
}
