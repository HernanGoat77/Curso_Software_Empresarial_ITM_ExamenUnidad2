package festivos.api.core.servicios;

import java.util.List;

import org.springframework.data.repository.query.Param;

import festivos.api.dominio.entidades.*;
import festivos.api.dominio.DTOs.*;

public interface ICalendarioServicio {

    public List<FestivoDTO> obtenerFestivosAño(String año);

    public boolean existeCalendarioParaAño(int año);

    public boolean generar(int año);

    public List<Calendario> calendarioPorAño(int año);

}