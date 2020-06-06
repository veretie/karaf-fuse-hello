package com.mits4u;

import org.apache.camel.Header;

public class HelperBean {

    public boolean isGoldenTicket(@Header("sellerId") String sellerId) {
        return Integer.valueOf(sellerId) == 1;
    }
}
