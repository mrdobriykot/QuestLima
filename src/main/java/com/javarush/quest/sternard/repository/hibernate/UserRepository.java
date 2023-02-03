package com.javarush.quest.sternard.repository.hibernate;

import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.javarush.quest.sternard.repository.Repository;
import com.javarush.quest.sternard.util.database.HibernateConnector;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class UserRepository implements Repository<User> {
    public UserRepository() {
        this.getEntitiesMap();
    }

    @Override
    public void create(User entity) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.find(User.class, id);
            if (user != null)
                session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
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
        Transaction transaction = null;
        try (Session session = HibernateConnector.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            List<User> users = query.list();
            transaction.commit();

            for (User user : users) {
                map.put(user.getId(), user);
            }

        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return map;
    }

}