package festivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festivos.api.dominio.entidades.*;

@Repository
public interface ITipoRepositorio extends JpaRepository<Tipo, Long> {

    @Query("SELECT t FROM Tipo t WHERE LOWER(TRIM(t.tipo)) = LOWER(TRIM(:tipo))")
    Tipo obtenerTipoPorNombre(@Param("tipo") String tipo);

}
