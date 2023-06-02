package com.flexpag.microservicereports.service;

import com.flexpag.microservicereports.config.ConnectionJdbc;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

@Service
@RequiredArgsConstructor
public class TransactionReportsService implements FileReportsService {

    private final FileService fileService;

    @Override
    public ByteArrayOutputStream getFileName(String statusEnum, Date date, String paymentType, Integer clientId) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = ConnectionJdbc.conexao();

        StringBuilder query = new StringBuilder("SELECT t.id, t.status, t.payment_type, pc.client_id, " +
                                                "t.purchase_id, t.create_at FROM transaction t");
        query.append(" JOIN purchase p ON t.purchase_id = p.id");
        query.append(" JOIN purchase_client pc ON p.id = pc.purchase_id WHERE 1 = 1");

        if(statusEnum != null){
            query.append(" AND status = ?");
        }if(date != null){
            query.append(" AND t.create_at = ?");
        }
        if(paymentType != null){
            query.append(" AND payments_type = ?");
        }if(clientId != null){
            query.append(" AND client_id = ?");
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

            int parameterIndex = 1;

            if(statusEnum != null){
                preparedStatement.setString(parameterIndex, statusEnum);
                parameterIndex++;
            }if(date != null){
                preparedStatement.setDate(parameterIndex, date);
                parameterIndex++;
            }if(paymentType != null){
                preparedStatement.setString(parameterIndex, paymentType);
                parameterIndex++;
            }if(clientId != null){
                preparedStatement.setInt(parameterIndex, clientId);
            }

            ResultSet result = preparedStatement.executeQuery();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while (result.next()) {

                byte[] transactionId  = result.getBytes("id");
                byte[] status = result.getBytes("status");
                byte[] payment = result.getBytes("payment_type");
                byte[] purchaseId = result.getBytes("client_id");
                byte[] clientIdd = result.getBytes("purchase_id");
                byte[] createAt = result.getBytes("create_at");

                outputStream.write(transactionId);
                outputStream.write(',');
                outputStream.write(status);
                outputStream.write(',');
                outputStream.write(payment);
                outputStream.write(',');
                outputStream.write(purchaseId);
                outputStream.write(',');
                outputStream.write(clientIdd);
                outputStream.write(',');
                outputStream.write(createAt);
                outputStream.write('\n');

            }

            result.close();
            outputStream.close();
            connection.close();

            return outputStream;

        }catch (IOException ex){
            throw new IOException();
        }


    }

    @Override
    public void generateFiles(ByteArrayOutputStream byteArrayOutputStream) {
        fileService.generateFileTransaction(byteArrayOutputStream);
    }
}
