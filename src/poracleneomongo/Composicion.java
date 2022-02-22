
package poracleneomongo;
import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Composicion implements Serializable {
@Id @GeneratedValue
int id;
String codp;
String codc;
int peso;

    public Composicion(String codp, String codc, int peso) {
        this.codp = codp;
        this.codc = codc;
        this.peso = peso;
    }
    public Composicion() {
    }

    public String getCodp() {
        return codp;
    }

    public void setCodp(String codp) {
        this.codp = codp;
    }

    public String getCodc() {
        return codc;
    }

    public void setCodc(String codc) {
        this.codc = codc;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    
}
