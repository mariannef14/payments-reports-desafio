package com.flexpag.microservicereports.interfaces;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public interface FileReportsService {


    ByteArrayOutputStream getFileName(String statusEnum, Date date, String paymentType, Integer clientId) throws SQLException, ClassNotFoundException, IOException;

    void generateFiles(ByteArrayOutputStream byteArrayOutputStream);
}
