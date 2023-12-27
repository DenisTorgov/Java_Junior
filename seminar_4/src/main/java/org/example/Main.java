package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.models.Course;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Задача 2
 * ========
 * <p>
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */
public class Main {
    public static void main (String[] args) {
        System.out.println("Hello world!");
        try  {

            // Создание сессии
            SessionFactory sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Course.class)
                    .buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();

            // Начало транзакции
            session.beginTransaction();
//            String sql = "CREATE TABLE IF NOT EXISTS courses "
//                    + "(id INT PRIMARY KEY, title VARCHAR(50), duration INT)";
//            session.createSQLQuery(sql).addEntity(Course.class).executeUpdate();
            Course course;
            for (int i = 0; i < 5; i++) {
                course = Course.create();
                session.save(course);
            }
            System.out.println("Object course save successfully");
            session.getTransaction().commit();

            // Чтение объекта из базы данных
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Course> coursesList = session
                    .createQuery("select a from Course a", Course.class)
                    .getResultList();
            for (Course c: coursesList) {
                System.out.println(c);
            }
            session.getTransaction().commit();
            System.out.println("Object read successfully");

            // Обновление объекта
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Course updateCourse = session.get(Course.class, 2);
            updateCourse.updateTitle();
            updateCourse.updateDuration();
            session.update(updateCourse);
            System.out.println("Object course where id=1 updated successfully");
            session.getTransaction().commit();
            System.out.println(updateCourse);

            // Удалене объекта
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Course deleteCourse = session.get(Course.class, 1);
            if (deleteCourse != null) {
                session.delete(deleteCourse);
                System.out.println("Object course where id=1 deleted successfully");
            }
            session.getTransaction().commit();

//            // Добавление первого курса
//            session = sessionFactory.getCurrentSession();
//            session.beginTransaction();
//            Course course1 = Course.create();
//            course1.setId(1);
//            session.update(course1);
//            System.out.println("Object course where id=1 updated successfully");
//            session.getTransaction().commit();
//            System.out.println(course1);

            session.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
