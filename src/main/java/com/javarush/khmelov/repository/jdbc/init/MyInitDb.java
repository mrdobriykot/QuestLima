package com.javarush.khmelov.repository.jdbc.init;

import com.javarush.khmelov.exception.AppException;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class MyInitDb {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(
                Objects.requireNonNull(
                        Cnn.class.getResourceAsStream("/postgres_init.sql")
                )
        ).useDelimiter(";");
        while (scanner.hasNext()) {
            String sql = scanner.next();
            process(sql);
        }

    }

    private static void process(String sql) {
        String firstWord = sql.trim().split("\\s+", 2)[0];
        String result = switch (firstWord) {
            case "SELECT" -> executeSelect(sql);
            case "INSERT" -> executeInsert(sql);
            default -> executeSql(sql);
        };
        System.out.println(result);
    }

    private static String executeSelect(String sql) {
        StringBuilder out = new StringBuilder(sql.trim() + "\n");
        try (Connection connection = Cnn.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                out.append(String.format("%-10s ", metaData.getColumnName(i)));
            }
            out.append("\n").append("-".repeat(metaData.getColumnCount()*11)).append("\n");
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    out.append(String.format("%-10s ", resultSet.getString(metaData.getColumnName(i))));
                }
                out.append("\n");
            }
            return out.toString();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    private static String executeInsert(String sql) {
        StringBuilder out = new StringBuilder(sql.trim());
        try (Connection connection = Cnn.getConnection();
             Statement statement = connection.createStatement()) {
            int rowCount = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            out.append("\nRows: ").append(rowCount).append(" Keys: ");
            while (generatedKeys.next()) {
                out.append(generatedKeys.getString(1)).append(" ");
            }
            out.append("\n");
            return out.toString();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }

    private static String executeSql(String sql) {
        try (Connection connection = Cnn.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
            return sql.trim();
        } catch (SQLException e) {
            throw new AppException(e);
        }
    }


}
