package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Client {
    public static void main(String[] args) {
        SessionCreator sessionCreator = new SessionCreator();
        User user = User.builder().id(1L).login("test").password("password").role(Role.GUEST).build();
        Session session = sessionCreator.getSession();
        try (session){
            Transaction tx = session.beginTransaction();
            try {
                info(session);
                User dbUser = session.get(User.class, 30L);
                dbUser = session.get(User.class, 31L);
                info(session);
                dbUser.setPassword("324234234");
                info(session);
                tx.commit();
            } catch (Exception e){
                tx.rollback();
            }
        }
        System.out.println(user);
    }

    private static void info(Session session) {
        System.out.println("-".repeat(100));
        System.out.printf("Dirty: %s%nStat: %s%nSession: %s%n"
                ,session.isDirty(),session.getStatistics(),session);
        System.out.println("-".repeat(100));
    }
}
