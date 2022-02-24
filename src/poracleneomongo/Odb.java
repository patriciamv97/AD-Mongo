package poracleneomongo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Odb {



    public static int mostrarObxetosOdb(String codp) {
        int total_graxas_prato=0;
        int totalGraxaComponente=0;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("/home/oracle/Escritorio/BasesOdb/composta.odb");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Composicion> composicionTypedQuery = em.createQuery("SELECT z FROM Composicion z WHERE z.codp=: r", Composicion.class);
        composicionTypedQuery.setParameter("r",codp);
        List<Composicion> composicions = composicionTypedQuery.getResultList();
        for (Composicion cp : composicions) {
            //System.out.println("id: " + cp.getId() + " codp: " + cp.getCodp() + " codc: " + cp.getCodc() + " peso: " + cp.getPeso());
            TypedQuery<Componente> componenteTypedQuery = em.createQuery("SELECT ce FROM Componente ce WHERE ce.codc= :p", Componente.class);
            componenteTypedQuery.setParameter("p", cp.getCodc());
            List<Componente> componentes = componenteTypedQuery.getResultList();
            for (Componente ce : componentes) {
                System.out.println("codigo do componente: "+ ce.getCodc()+ "-> " + " graxa por cada 100 gr: " + ce.getGraxa());
                totalGraxaComponente=(ce.getGraxa()*cp.getPeso()/100);
                System.out.println("peso " +cp.getPeso()+"\ntotal de graxa do componente: "+totalGraxaComponente);
                total_graxas_prato=total_graxas_prato+totalGraxaComponente;

            }

        }
        System.out.println("\nTOTAL EN GRAXAS DO PRATO:"+ total_graxas_prato);

        em.getTransaction().commit();
        em.close();
        emf.close();
        return total_graxas_prato
  ;

    }

}
