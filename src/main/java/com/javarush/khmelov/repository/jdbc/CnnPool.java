package com.javarush.khmelov.repository.jdbc;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@UtilityClass
public class CnnPool {

    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URI = "jdbc:postgresql://localhost:2345/game";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final int SIZE = 10;

    public static final BlockingQueue<Object> queue = new ArrayBlockingQueue<>(SIZE);
    public static final ArrayList<Connection> connections = new ArrayList<>(SIZE);


    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fill();
    }

    @SneakyThrows
    private void fill() {
        for (int i = 0; i < SIZE; i++) {
            Connection connection = DriverManager.getConnection(URI, USER, PASSWORD);
            Object proxyCnn = Proxy.newProxyInstance(
                    CnnPool.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> "close".equals(method.getName())
                            ? Boolean.valueOf(queue.add(proxy))
                            : method.invoke(connection, args));
            queue.put(proxyCnn);
            connections.add(connection);
        }
    }

    @SneakyThrows
    public Connection get() {
        return (Connection) queue.take();
    }


    @SneakyThrows
    public void destroy() {
        for (Connection connection : connections) {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            connection.close();
        }
    }
}

