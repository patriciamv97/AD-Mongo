package pinvasoras1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class Odb {


    /**
     * Se comienza creando un EntityManagerFactory que carga la unidad de persistencia.
     * Esto es bastante costoso y debería hacerse sólo una vez, al arrancar la aplicación.
     */
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/EjemploExamen/zonas.odb");
    /**
     * A partir del EntityManagerFactory se crea un EntityManager, el objeto de JPA que gestiona las entidades, los contextos de persistencia y las transacciones.
     * Un contexto de persistencia es similar a una conexión a la base de datos. Es muy barato de obtener a partir del EntityManagerFactory.
     */
    static EntityManager em = emf.createEntityManager();

    public static void mostrarObxetosOdb() throws SQLException {
        em.getTransaction().begin();
        TypedQuery<Zonas> query =
                em.createQuery("SELECT z FROM Zonas z", Zonas.class);
        List<Zonas> results = query.getResultList();
        for (Zonas z : results) {
            System.out.println("codz: "+z.getCodz()+ " nomz: "+z.getNomz()+" superficie: "+z.getSuperficie());
            Mongo.consultar_por_campo_valor("codz",z.getCodz(), z.getNomz());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}
