package pvehiculos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class Odb {

    public static void obtenerDatos(String codveh, String dni, Double id) throws SQLException {
        int pf =0;
        int prezoorixe=0;
        int anomatricula=0;
        int desconto=0;
        String nomec="", nomevhe="";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/Examen/vehicli.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Vehiculos> obtenerVehiculo = em.createQuery("SELECT v FROM Vehiculos v WHERE v.codveh=: r", Vehiculos.class);
        obtenerVehiculo.setParameter("r",codveh);
        List<Vehiculos> vehiculos = obtenerVehiculo.getResultList();
        for (Vehiculos vehiculo : vehiculos){
            prezoorixe = vehiculo.getPrezoorixe();
            anomatricula = vehiculo.getAnomatricula();
            nomevhe=vehiculo.getNomveh();
            System.out.println("nomevhe-> "+ nomevhe+" prezoorixe-> "+prezoorixe+" anomatricula-> "+anomatricula);
        }
        TypedQuery<Clientes> obtenerCliente = em.createQuery("SELECT c FROM Clientes c WHERE c.dni=: r", Clientes.class);
        obtenerCliente.setParameter("r",dni);
        List<Clientes> clientes = obtenerCliente.getResultList();
        for (Clientes cliente : clientes){
            nomec=cliente.getNomec();
            System.out.println("nomec-> "+ nomec+" ncompras-> "+cliente.getNcompras());
            if (cliente.getNcompras()>0){
                desconto=500;
            }
        }
        pf =prezoorixe-((2019-anomatricula)*500)-desconto;
        System.out.println(pf);
        Postgres.inserir(id,dni,nomec,nomevhe,pf);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }


}
