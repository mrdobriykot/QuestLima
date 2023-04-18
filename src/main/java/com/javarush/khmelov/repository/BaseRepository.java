package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.AbstractEntity;
import com.javarush.khmelov.config.SessionCreator;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class BaseRepository<T extends AbstractEntity> implements Repository<T> {

    private final SessionCreator sessionCreator;
    private final Class<T> type;

    @Transactional
    @Override
    public Collection<T> getAll() {
        Session session = sessionCreator.getSession();
        Query<T> query = session.createQuery("select u from " + type.getSimpleName() + " u order by id", type);
        return query.list();
    }

    /* session->cb->cq->root
     * c <- filter fields and add cb.equals(root.get(name), value)
     * cq.select(root).where(predicates);
     * result <- session.createQuery(cq).list(); */
    @SneakyThrows
    @Override
    @Transactional
    public Stream<T> find(T pattern) {
        Session session = sessionCreator.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> userRoot = criteriaQuery.from(type);
        List<Predicate> predicates = new ArrayList<>();
        for (Field field : pattern.getClass().getDeclaredFields()) {
            field.trySetAccessible();
            Object value = field.get(pattern);
            String name = field.getName();
            if (!field.isAnnotationPresent(Transient.class) &&
                !field.isAnnotationPresent(ManyToOne.class) &&
                !field.isAnnotationPresent(OneToMany.class) &&
                !field.isAnnotationPresent(OneToOne.class) &&
                !field.isAnnotationPresent(ManyToMany.class)
                && value != null) {
                Predicate predicate = criteriaBuilder.equal(userRoot.get(name), value);
                predicates.add(predicate);
            }
        }
        criteriaQuery.select(userRoot).where(predicates.toArray(Predicate[]::new));
        return session.createQuery(criteriaQuery).list().stream();
    }

    // HQL
    @Override
    @Transactional
    public T get(long id) {
        return sessionCreator.getSession().get(type, id);
    }

    @Override
    @Transactional
    public void create(T entity) {
        sessionCreator.getSession().persist(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        Session session = sessionCreator.getSession();
        session.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        sessionCreator.getSession().remove(entity);
    }

}
