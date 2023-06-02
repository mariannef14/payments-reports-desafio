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

    // private static String url = "jdbc:postgresql://localhost:5432/paymentsdb";

    // private static String username = "postgres";

    // private static String password = "Mudar@2023";

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/paymentsdb";
    private static String username = "root";
    private static String password= "t3ste@14";

    @Bean
    public static Connection conexao() throws SQLException, ClassNotFoundException {

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Conexão estabelecida");
            return connection;

        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar com o banco de dados:" + e.getMessage());
        }catch (ClassNotFoundException e) {
            throw new SQLException("Erro ao carregar driver:" + e.getMessage());
        }

    }

}


