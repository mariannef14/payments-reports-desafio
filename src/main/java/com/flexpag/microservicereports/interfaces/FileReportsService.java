package com.flexpag.microservicereports.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface FileReportsService {


    ResultSet generateFile(String statusEnum, LocalDate date, String paymentType, Long clientId, String reportType) throws SQLException, ClassNotFoundException;

}
