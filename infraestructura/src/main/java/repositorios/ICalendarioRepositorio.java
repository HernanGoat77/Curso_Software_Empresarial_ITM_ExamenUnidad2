package festivos.api.infraestructura.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festivos.api.dominio.entidades.*;

@Repository
public interface ICalendarioRepositorio extends JpaRepository<Calendario, Long> {

    @Query("SELECT COUNT(c) > 0 FROM Calendario c WHERE EXTRACT(YEAR FROM c.fecha) = :año")
    boolean existeCalendarioParaAño(@Param("año") int año);

    @Query("SELECT c FROM Calendario c WHERE EXTRACT(YEAR FROM c.fecha) = :año ORDER BY c.id")
    List<Calendario> calendarioPorAño(int año);

}
