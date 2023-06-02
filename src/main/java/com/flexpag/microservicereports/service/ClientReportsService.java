package com.flexpag.microservicereports.service;

import com.flexpag.microservicereports.config.ConnectionJdbc;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientReportsService implements FileReportsService {

    private final FileService fileService;

    @Override
    public ByteArrayOutputStream getFileName(String statusEnum, Date date, String paymentType, Integer clientId) throws SQLException, ClassNotFoundException, IOException {
        Connection connection = ConnectionJdbc.conexao();

        StringBuilder query = new StringBuilder("SELECT id, name, email, contract, contract_number  FROM CLIENT");

        if(clientId != null){
            query.append(" WHERE id = ?");
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());

           if(clientId != null){
                preparedStatement.setInt(1, clientId);
            }

            ResultSet result = preparedStatement.executeQuery();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while (result.next()) {

                byte[] clientIdd = result.getBytes("id");
                byte[] name = result.getBytes("name");
                byte[] email = result.getBytes("email");
                byte[] contract = result.getBytes("contract");
                byte[] contract_number = result.getBytes("contract_number");


                outputStream.write(clientIdd);
                outputStream.write(',');
                outputStream.write(name);
                outputStream.write(',');
                outputStream.write(email);
                outputStream.write(',');
                outputStream.write(contract);
                outputStream.write(',');
                outputStream.write(contract_number);
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
        fileService.generateFileClient(byteArrayOutputStream);
    }
}
