package com.mits4u;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class BookingRoutes extends RouteBuilder {

    private String deadLetterUri;
    private String logsQueueUri;
    private String emailQueueUri;
    private String mailQueueUri;

    private int maximumRedeliveries;
    private double backoffMultiplier;
    private RandomMessageCreator randomMessageCreator;

    @Override
    public void configure() {

        setErrorHandlingPolicy();

        from("timer:tickTock?period=5000").id("tickTock")
                .throttle(9)
                    .timePeriodMillis(50000) //every 10th request will fail
                    .rejectExecution(true)
                .log("Creating new message")
                .process(randomMessageCreator).id("creator")
                .log("Generated ticket with sellerId=${header[sellerId]}, exchangeId=${exchangeId}")
                .wireTap(logsQueueUri).id("logWiretap")
                .choice()
                    .when(header("sellerId").isEqualTo(0))
                        .log("Unknown sellerId, failing")
                        .throwException(new RuntimeException("Unknown sellerId"))
                    .when(header("sellerId").isLessThan(6))
                        .to(emailQueueUri).id("email")
                    .otherwise()
                        .log("sellerId=${header[sellerId]} ticket mail will be sent manually")
                        .to(mailQueueUri).id("mail")
                .end();

        from(emailQueueUri).id("emailQueue")
                .log("sellerId= ticket email processing, exchangeId=${exchangeId}");
//                .filter().method(HelperBean.class, "isGoldenTicket")
//                    .log("Golden ticket!!")
//                .end();
                //enrich
                //split
                //aggregate



    }


    private void setErrorHandlingPolicy() {
        errorHandler(deadLetterChannel(deadLetterUri)
                .maximumRedeliveries(this.maximumRedeliveries)
                .backOffMultiplier(this.backoffMultiplier)
                .retryAttemptedLogLevel(LoggingLevel.WARN));
    }

    public void setDeadLetterUri(String deadLetterUri) {
        this.deadLetterUri = deadLetterUri;
    }

    public void setEmailQueueUri(String emailQueueUri) {
        this.emailQueueUri = emailQueueUri;
    }

    public void setMailQueueUri(String mailQueueUri) {
        this.mailQueueUri = mailQueueUri;
    }

    public void setLogsQueueUri(String logsQueueUri) {
        this.logsQueueUri = logsQueueUri;
    }

    public void setMaximumRedeliveries(int maximumRedeliveries) {
        this.maximumRedeliveries = maximumRedeliveries;
    }

    public void setBackoffMultiplier(double backoffMultiplier) {
        this.backoffMultiplier = backoffMultiplier;
    }

    public void setRandomMessageCreator(RandomMessageCreator randomMessageCreator) {
        this.randomMessageCreator = randomMessageCreator;
    }


}