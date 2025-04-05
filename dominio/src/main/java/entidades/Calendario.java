package festivos.api.dominio.entidades;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "calendario")
public class Calendario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_calendario")
    @GenericGenerator(name = "secuencia_calendario", strategy = "increment")
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id", nullable = false)
    private Tipo tipo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public Calendario() {
    }

    public Calendario(Date fecha, Tipo tipo, String descripcion) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
