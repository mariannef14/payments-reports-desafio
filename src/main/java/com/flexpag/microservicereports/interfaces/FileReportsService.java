package com.flexpag.microservicereports.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface FileReportsService {


    ResultSet generateFile(String StatusEnum, LocalDate date, String PaymentTypeEnum, Long ClientId, String reportTypeEnum) throws SQLException;

}
