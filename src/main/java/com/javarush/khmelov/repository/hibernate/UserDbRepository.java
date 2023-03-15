package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.exception.AppException;
import com.javarush.khmelov.repository.Repository;
import com.javarush.khmelov.repository.jdbc.DaoException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class UserDbRepository implements Repository<User> {

    private final SessionCreator sessionCreator;

    @Override
    public Collection<User> getAll() {
        Session session = sessionCreator.getSession();
        try (session){
            Transaction tx = session.beginTransaction();
            try {
                Query<User> query = session.createQuery("select u from User u order by id", User.class);
                List<User> result = query.list();
                tx.commit();
                return result;
            } catch (Exception e){
                tx.rollback();
                throw new DaoException(e);
            }
        }
    }

    /* session->cb->cq->root
     * c <- filter fields and add cb.equals(root.get(name), value)
     * cq.select(root).where(predicates);
     * result <- session.createQuery(cq).list(); */
    @Override
    public Stream<User> find(User pattern) {
        Session session = sessionCreator.getSession();
        try (session){
            Transaction tx = session.beginTransaction();
            try {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
                Root<User> userRoot = criteriaQuery.from(User.class);
                List<Predicate> predicates=new ArrayList<>();
                for (Field field : pattern.getClass().getDeclaredFields()) {
                    field.trySetAccessible();
                    Object value = field.get(pattern);
                    String name = field.getName();
                    if (!field.isAnnotationPresent(Transient.class) && value!=null){
                        Predicate predicate = criteriaBuilder.equal(userRoot.get(name), value);
                        predicates.add(predicate);
                    }
                }
                criteriaQuery.select(userRoot).where(predicates.toArray(Predicate[]::new));
                List<User> userList = session.createQuery(criteriaQuery).list();
                tx.commit();
                return userList.stream();
            } catch (Exception e){
                tx.rollback();
                throw new DaoException(e);
            }
        }
    }

    // HQL
    @Override
    public User get(long id) {
        Session session = sessionCreator.getSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                User user = session.get(User.class, id);
                tx.commit();
                return user;
            } catch (Exception e) {
                tx.rollback();
                throw new AppException(e);
            }
        }
    }

    @Override
    public void create(User user) {
        Session session = sessionCreator.getSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(user);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        }
    }

    @Override
    public void update(User user) {
        Session session = sessionCreator.getSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                session.merge(user);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        }
    }

    @Override
    public void delete(User user) {
        Session session = sessionCreator.getSession();
        try (session) {
            Transaction tx = session.beginTransaction();
            try {
                session.remove(user);
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
            }
        }
    }

}
