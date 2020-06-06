package com.mits4u;

import java.io.Serializable;

public class TicketPojo implements Serializable {

    private int sellerId;

    private String ticketDescription;

    public TicketPojo() {
    }

    public TicketPojo(int sellerId, String ticketDescription) {
        this.sellerId = sellerId;
        this.ticketDescription = ticketDescription;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }
}
