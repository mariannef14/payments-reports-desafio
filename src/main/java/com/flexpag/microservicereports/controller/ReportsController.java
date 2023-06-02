package com.flexpag.microservicereports.controller;

import com.flexpag.microservicereports.service.factory.FileServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportsController {

    private final FileServiceFactory fileServiceFactory;


    @GetMapping("/")
    public ResponseEntity<ByteArrayOutputStream> consultReports(@RequestParam(required = true) String type,
                                                 @RequestParam(required = false) String status,
                                                 @RequestParam(required = false)  Date date,
                                                 @RequestParam(required = false) String paymentType,
                                                 @RequestParam(required = false) Integer clientId) throws SQLException, ClassNotFoundException, IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        fileServiceFactory.getService(type).getFileName(status, date, paymentType, clientId);

        try{
            byteArrayOutputStream = fileServiceFactory.getService(type).getFileName(status, date, paymentType, clientId);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            fileServiceFactory.getService(type).generateFiles(byteArrayOutputStream);
            return new ResponseEntity(byteArray, headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
