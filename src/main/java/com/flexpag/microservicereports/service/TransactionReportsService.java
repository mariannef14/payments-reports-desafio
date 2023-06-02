package com.flexpag.microservicereports.service;

import com.flexpag.microservicereports.config.ConnectionJdbc;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class TransactionReportsService implements FileReportsService {


    @Override
    public ResultSet generateFile(String statusEnum, LocalDate date, String paymentType, Long clientId, String reportType) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionJdbc.conexao();

        // StringBuilder query = new StringBuilder("select * from transaction where ");
        // if(statusEnum != null){
        //     query.append("status = " + statusEnum + " ");
        // }if(paymentType != null){
        //     query.append("and payment_type = " + paymentType + "'");
        // }if(clientId != null){
        //     //SELECT * from paymentsdb.transaction, paymentsdb.purchase, paymentsdb.purchase_client where paymentsdb.transaction.purchase_id = paymentsdb.purchase.id
        //     // and 
        //     // paymentsdb.purchase.id = paymentsdb.purchase_client.purchase_id
        //     // and 
        //     // paymentsdb.client.id = paymentsdb.purchase_client.client_id;
        //     query.append("and purchase_id = purchase.id and purchase.client.id = client.id " + clientId);
        

        // PreparedStatement stmt = connection.prepareStatement(query.toString());

        StringBuilder query = new StringBuilder("SELECT t.status, t.payment_type, p.client_id ");
        query.append("FROM transaction t ");
        query.append("JOIN purchase p ON t.purchase_id = p.id ");
        query.append("JOIN purchase_client pc ON p.id = pc.purchase_id ");
        query.append("WHERE");

          if(statusEnum != null){
            query.append(" t.status =: statusEnum");
        // if (statusEnum != null) {
        //     query.append(" t.status =:  ");
        // }
        // if (paymentType != null) {
        //     query.append("AND t.payment_type = ? ");
        // }
        // if (clientId != null) {
        //     query.append("AND p.client_id = ? ");
        }
    
        PreparedStatement stmt = connection.prepareStatement(query.toString());
    
        int parameterIndex = 1;
    
        if (statusEnum != null) {
            stmt.setString(parameterIndex, statusEnum);
           // parameterIndex++;
        }
        // if (paymentType != null) {
        //     stmt.setString(parameterIndex, paymentType);
        //     parameterIndex++;
        // }
        // if (clientId != null) {
        //     stmt.setLong(parameterIndex, clientId);
        // }

        ResultSet resultado = stmt.executeQuery(query.toString());
        System.out.println(resultado);

        while(resultado.next()){
            System.out.println(resultado.getString("status"));
        }

        // stmt.close();
        // resultado.close();
        // connection.close();

        return resultado;
    }
}
