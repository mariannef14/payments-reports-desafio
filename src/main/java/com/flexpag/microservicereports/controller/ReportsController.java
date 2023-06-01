package com.flexpag.microservicereports.controller;

import com.flexpag.microservicereports.service.factory.FileServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportsController {

    private final FileServiceFactory fileServiceFactory;

    @GetMapping("/")
    public ResponseEntity<ResultSet> consultReports(@RequestParam(required = false) String status,
                                                    @RequestParam(required = false) LocalDate date,
                                                    @RequestParam(required = false) String paymentType,
                                                    @RequestParam(required = false) Long clientId,
                                                    @RequestParam(required = true) String type) throws SQLException {

        return ResponseEntity.ok(fileServiceFactory.getService(type).generateFile(status, date, paymentType,clientId, type));
    }
}
