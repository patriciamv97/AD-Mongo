package com.ejercicios.tutorial;

import javax.persistence.*;
import java.util.List;

import static com.objectdb.o.SYH.p;

public class tutorial {
    public static void main(String[] args) {

        /**
         * Se comienza creando un EntityManagerFactory que carga la unidad de persistencia.
         * Esto es bastante costoso y debería hacerse sólo una vez, al arrancar la aplicación.
         */
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");

        /**
         *A partir del EntityManagerFactory se crea un EntityManager, el objeto de JPA que gestiona las entidades, los contextos de persistencia y las transacciones.
         * Un contexto de persistencia es similar a una conexión a la base de datos. Es muy barato de obtener a partir del EntityManagerFactory.
         */
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
      /*  em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();

       */

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:


        //2) listar todos os puntos ( o seu id , e demais atributos)
        /*

        TypedQuery<Point> query =
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println("Id : "+p.getId() +" coordenadas : "+p.toString());
        }

       */

        //3) amosar os atributos do punto con id =10
        TypedQuery<Point> query2 =
                em.createQuery("SELECT p FROM Point p WHERE p.id =10", Point.class);
        List<Point> results2 = query2.getResultList();
        for (Point p : results2) {
            System.out.println("Id : "+p.getId() +", coordenadas : "+p.toString());
        }


        //4) actualizar o punto de id =10 , coordenada y, ao valor
        //que ten mais 2, e dicir se o atributo y do punto de id=10
        //valia 4 , debe pasar a valer 6.
        em.getTransaction().begin();
        Query actualizar = em.createQuery("UPDATE Point p SET p.y = p.y + 2 WHERE p.id = 10");
        int UpdateCount = actualizar.executeUpdate();
        em.getTransaction().commit();

        // 5) Eliminar el registro con id =5
        em.getTransaction().begin();
        int eliminarRegistro = em.createQuery("DELETE  FROM Point p WHERE p.id=5").executeUpdate();
        em.getTransaction().commit();
        /*
        TypedQuery<Point> query =
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println("Id : "+p.getId() +" coordenadas : "+p.toString());
        }

         */
        //6) actualizacion masiva selectiva :
        //actualizar coordenada y ao valor 1000 para todos os
        //puntos que teñan un valor de y inferior a un valor
        //pasado por parametro (por exemplo facelo para o valor
        //6)

        em.getTransaction().begin();
        Query actualizar2 = em.createQuery("UPDATE Point p SET p.y = 1000 WHERE p.y < :z");
        int UpdateCount2 = actualizar2.setParameter("z", 6).executeUpdate();
        em.getTransaction().commit();

       /* TypedQuery<Point> query3 =
                em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results3 = query3.getResultList();
        for (Point p : results3) {
            System.out.println("Id : "+p.getId() +" coordenadas : "+p.toString());
        }

        */
        // Close the database connection:
        em.close();
        emf.close();
    }
    }

