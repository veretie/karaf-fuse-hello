package com.mits4u;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.util.Random;

public class RandomMessageCreator implements Processor {

    private static Random RANDOM = new Random();

    @Override
    public void process(Exchange exchange) throws Exception {

        int iRandom = RANDOM.nextInt(10);
        TicketPojo ticket = new TicketPojo(iRandom, "Ticket" + iRandom);

        Message inMessage = exchange.getIn();
        inMessage.setBody(ticket);
        exchange.setProperty("sellerId", iRandom);
        exchange.setProperty("segments", String.join(",", "A" + iRandom, "B" + iRandom, "C" + iRandom));

    }

}
