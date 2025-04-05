package festivos.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo")
public class Tipo {

    @Id
    private Long id;

    @Column(name = "tipo", length = 100, unique = true, nullable = false)
    private String tipo;

    public Tipo() {
    }

    public Tipo(Long id, String tipo) {
        this.tipo = tipo;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
