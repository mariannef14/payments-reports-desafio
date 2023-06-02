package com.flexpag.microservicereports.service;

import com.flexpag.microservicereports.config.ConnectionJdbc;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class ClientReportsService implements FileReportsService {

    @Override
    public ResultSet generateFile(String statusEnum, LocalDate date, String paymentType, Long clientId, String reportType) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionJdbc.conexao();

        String query = "select * from client where";
        PreparedStatement stmt = connection.prepareStatement(query.toString());

        
        ResultSet resultado = stmt.executeQuery(query);

        while(resultado.next()){
            int id = resultado.getInt("id");
            String contract = resultado.getString("contract");
            Long contractNumber = resultado.getLong("contractNumber");
            String email = resultado.getString("email");
        }


        return resultado;
    }
}
