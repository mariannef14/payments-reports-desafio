package com.flexpag.microservicereports.service;

import com.flexpag.microservicereports.config.ConnectionJdbc;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

@Service
public class TransactionReportsService implements FileReportsService {


    @Override
    public ResultSet generateFile(String StatusEnum, LocalDate date, String PaymentTypeEnum, Long ClientId, String reportTypeEnum) throws SQLException {
        Connection connection = ConnectionJdbc.conexao();
        Statement stmt = connection.createStatement();

        String query = "select * from Client";
        ResultSet resultado = stmt.executeQuery(query);

        return resultado;
    }
}
