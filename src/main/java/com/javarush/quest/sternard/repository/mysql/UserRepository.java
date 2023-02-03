package com.javarush.quest.sternard.repository.mysql;

import com.javarush.quest.sternard.entities.Role;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import com.javarush.quest.sternard.util.database.JdbcConnector;
import com.nqadmin.rowset.JdbcRowSetImpl;
import lombok.extern.slf4j.Slf4j;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.javarush.quest.sternard.util.database.DbFields.*;
import static com.javarush.quest.sternard.util.database.DbQueries.*;

@Slf4j
public class UserRepository implements Repository<User> {
    public UserRepository() {
        this.getEntitiesMap();
    }

    @Override
    public void create(User entity) {
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(MYSQL_CREATE_USER)) {

            setPreparedStatement(entity, prepStmt);
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(MYSQL_UPDATE_USER)) {

            setPreparedStatement(entity, prepStmt);
            prepStmt.setLong(6, entity.getId());
            prepStmt.executeUpdate();

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement(MYSQL_DELETE_USER)) {
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public Collection<User> getAllEntities() {
        return getEntitiesMap().values();
    }

    @Override
    public Map<Long, User> getEntitiesMap() {
        Map<Long, User> map = new HashMap<>();
        try (Connection connection = JdbcConnector.INSTANCE.getConnection();
             JdbcRowSet rowSet = new JdbcRowSetImpl(connection)) {

            rowSet.setType(ResultSet.TYPE_SCROLL_SENSITIVE);
            rowSet.setCommand(MYSQL_SELECT_ALL_USERS);
            rowSet.execute();

            while (rowSet.next()) {
                long userId = rowSet.getLong(ID);
                User user = buildUser(rowSet, userId);
                map.put(userId, user);
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return map;
    }

    private void setPreparedStatement(User entity, PreparedStatement prepStmt) throws SQLException {
        prepStmt.setString(1, entity.getLogin());
        prepStmt.setString(2, entity.getPassword());
        prepStmt.setString(3, entity.getProfileImage());
        prepStmt.setInt(4, entity.getPlayedQuestsCount());
        prepStmt.setString(5, entity.getRole().toString());
    }

    private User buildUser(JdbcRowSet rowSet, long userId) throws SQLException {
        return User.builder()
                .id(userId)
                .login(rowSet.getString(LOGIN))
                .password(rowSet.getString(PASSWORD))
                .playedQuestsCount(rowSet.getInt(PLAYED_QUESTS_COUNT))
                .profileImage(rowSet.getString(PROFILE_IMAGE))
                .role(Role.valueOf(rowSet.getString(ROLE)))
                .build();
    }

}