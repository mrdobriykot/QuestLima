package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Client {
    public static void main(String[] args) {
        SessionCreator sessionCreator = new SessionCreator();
        User user = User.builder().login("test").password("password").role(Role.GUEST).build();
        Session session = sessionCreator.getSession();
        try (session){
            Transaction tx = session.beginTransaction();
            try {
                session.persist(user);
                tx.commit();
            } catch (Exception e){
                tx.rollback();
            }
        }
        System.out.println(user);
    }
}
