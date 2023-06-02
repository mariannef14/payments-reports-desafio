package com.flexpag.microservicereports.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ConnectionJdbc {

     private static String url = "jdbc:postgresql://localhost:5432/paymentsdb";

     private static String username = "postgres";

     private static String password = "Mudar@2023";

    @Bean
    public static Connection conexao() throws SQLException {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            return connection;

        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados:" + e.getMessage());
        }

    }

}


