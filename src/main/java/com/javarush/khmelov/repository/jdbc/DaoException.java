package com.javarush.khmelov.repository.jdbc;

import java.sql.SQLException;

public class DaoException extends RuntimeException {

    public DaoException(SQLException cause) {
        super(cause);
    }

}
