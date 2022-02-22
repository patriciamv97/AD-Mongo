
package poracleneomongo;
import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Componente implements Serializable {
@Id
String codc; 
String nomec;
int graxa;

    public Componente(String codc, String nomec, int graxa) {
        this.codc = codc;
        this.nomec = nomec;
        this.graxa = graxa;
    }
    public Componente() {
       
    }
    
    public String getCodc() {
        return codc;
    }

    public void setCodc(String cod) {
        this.codc = codc;
    }

    public String getNomec() {
        return nomec;
    }

    public void setNomec(String nomec) {
        this.nomec = nomec;
    }

    public int getGraxa() {
        return graxa;
    }

    public void setGraxa(int graxa) {
        this.graxa = graxa;
    }

   
}
