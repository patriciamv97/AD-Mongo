
package pinvasoras1;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Zonas implements Serializable {
  @Id 
    private int codz;
    private String nomz ;  
    private Double superficie ; 

    public Zonas(int codz, String nomz, Double superficie) {
        this.codz = codz;
        this.nomz = nomz;
        this.superficie = superficie;
    }

    public Zonas() {
    }

    public int getCodz() {
        return codz;
    }

    public void setCodz(int codz) {
        this.codz = codz;
    }

    public String getNomz() {
        return nomz;
    }

    public void setNomz(String nomz) {
        this.nomz = nomz;
    }

    public Double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Double superficie) {
        this.superficie = superficie;
    }

   

    
}
