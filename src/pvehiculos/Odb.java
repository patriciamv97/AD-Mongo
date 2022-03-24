package pvehiculos;

import poracleneomongo.Composicion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Odb {

    public static void  obtenerDatosVehiculo(String codveh){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/Examen/vehicli.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Vehiculos> composicionTypedQuery = em.createQuery("SELECT v FROM Vehiculos v WHERE v.codveh=: r", Vehiculos.class);
        composicionTypedQuery.setParameter("r",codveh);
        List<Vehiculos> vehiculos = composicionTypedQuery.getResultList();
        for (Vehiculos vehiculo : vehiculos){
            System.out.println("nomevhe-> "+ vehiculo.getNomveh()+" prezoorixe-> "+vehiculo.getPrezoorixe()+" anomatricula-> "+vehiculo.getAnomatricula());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public static void  obtenerDatosCliente(String dni){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/Examen/vehicli.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Clientes> composicionTypedQuery = em.createQuery("SELECT c FROM Clientes c WHERE c.dni=: r", Clientes.class);
        composicionTypedQuery.setParameter("r",dni);
        List<Clientes> clientes = composicionTypedQuery.getResultList();
        for (Clientes cliente : clientes){
            System.out.println("nomec-> "+ cliente.getNomec()+" ncompras-> "+cliente.getNcompras());
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
