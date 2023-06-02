package com.flexpag.microservicereports.service;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileService {

    public void generateFileTransaction(ByteArrayOutputStream byteArrayOutputStream){
        try {
            FileWriter fileWriter = new FileWriter("Reports_Transaction.xls");
            fileWriter.write("id, status, payment type, client id, purchase id, create at\n");
            fileWriter.write(byteArrayOutputStream.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public void generateFileClient(ByteArrayOutputStream byteArrayOutputStream){
        try {
            FileWriter fileWriter = new FileWriter("Reports_Client.xls");
            fileWriter.write("id, nome, email, contract, contract_number \n");
            fileWriter.write(byteArrayOutputStream.toString());

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
