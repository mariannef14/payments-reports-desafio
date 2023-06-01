package com.flexpag.microservicereports.service.factory;

import com.flexpag.microservicereports.enums.ReportTypeEnum;
import com.flexpag.microservicereports.interfaces.FileReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileServiceFactory{

    private final Map<String, FileReportsService> services;

    public FileReportsService getService(String fileType){
        ReportTypeEnum reportTypeEnum = ReportTypeEnum.valueOf(fileType);
        return services.get(reportTypeEnum.getService());
    }

}
