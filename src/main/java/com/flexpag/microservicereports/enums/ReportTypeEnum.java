package com.flexpag.microservicereports.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReportTypeEnum {

    CLIENT("clientReportsService"),
    TRANSACTION("transactionReportsService");

    private final String service;

}
