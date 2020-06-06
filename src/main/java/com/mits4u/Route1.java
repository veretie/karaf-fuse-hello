package com.mits4u;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class Route1 extends RouteBuilder {

    private String deadLetterUri;
    private int maximumRedeliveries;
    private double backoffMultiplier;

    @Override
    public void configure() {

        setErrorHandlingPolicy();

        from("timer:foo")
                .log("Hello Camelllliii");

//        from(checkInUcmQueue)
//                .routeId(ID_ROUTE_CHECK_IN_UCM)
//                .to(logRouteInfo("Message received in" + checkInUcmQueue + " queue, document id: ${body.documentId}"))
//                .removeHeaders("*")   //Remove headers prior to set SOAP operations, to avoid NS conflicts
//                .to(logRouteDebug("Preparing document check in message"))
//                .process(composeUcmCheckInMessageProcessor).id(ID_NODE_COMPOSE_CHECK_IN_MESSAGE)
//                .to(logRouteDebug("Message composed for UCM check-in"))
//                .setHeader(CxfConstants.OPERATION_NAME, constant("MOCheckIn"))
//                .setHeader(EXTERNAL_SYSTEM_CALLED.getName(), constant(ExternalSystem.UCM_WS))
//                .enrich(cxfEndpointUCM, interrogateUcmCheckInResponseAggStrategy).id(ID_NODE_SEND_UCM_CHECK_IN_MESSAGE)
//                .removeHeader(EXTERNAL_SYSTEM_CALLED.getName())
//                .to(logRouteInfo("Document successfully checked-in to UCM"))
//                .to(logCompletedComponent + ":" + LOG_ENDPOINT_CHECK_IN_COMPLETED).id(ID_NODE_SUCCESSFUL_UPLOAD);

    }


    private void setErrorHandlingPolicy() {
//        errorHandler(deadLetterChannel(deadLetterUri)
//                .maximumRedeliveries(this.maximumRedeliveries)
//                .backOffMultiplier(this.backoffMultiplier)
//                .retryAttemptedLogLevel(LoggingLevel.WARN));
    }

    public void setDeadLetterUri(String deadLetterUri) {
        this.deadLetterUri = deadLetterUri;
    }

    public void setMaximumRedeliveries(int maximumRedeliveries) {
        this.maximumRedeliveries = maximumRedeliveries;
    }

    public void setBackoffMultiplier(double backoffMultiplier) {
        this.backoffMultiplier = backoffMultiplier;
    }

}