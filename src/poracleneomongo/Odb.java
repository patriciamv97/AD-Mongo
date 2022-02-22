package poracleneomongo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Odb {

    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/BasesOdb/composta.odb");
    static EntityManager em = emf.createEntityManager();

    public static void mostrarObxetosOdb() {
        em.getTransaction().begin();
        TypedQuery<Composicion> composicionTypedQuery =
                em.createQuery("SELECT z FROM Composicion z", Composicion.class);
        List<Composicion> composicions = composicionTypedQuery.getResultList();
        for (Composicion z : composicions) {
            System.out.println("id: "+z.getId()+ " codp: "+z.getCodp()+ " codc: "+z.getCodc()+" peso: "+z.getPeso());
        }
        TypedQuery<Componente> componenteTypedQuery =
                em.createQuery("SELECT z FROM Componente z", Componente.class);
        List<Componente> componentes = componenteTypedQuery.getResultList();
        for (Componente z : componentes) {
            System.out.println("codc: "+z.getCodc()+ "nomec: "+z.getNomec()+ " graxa: "+z.getGraxa());
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
