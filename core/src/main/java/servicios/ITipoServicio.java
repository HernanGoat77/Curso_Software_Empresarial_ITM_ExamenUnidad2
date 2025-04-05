package festivos.api.core.servicios;

import festivos.api.dominio.entidades.*;
import festivos.api.dominio.DTOs.*;

public interface ITipoServicio {

    public Tipo obtenerTipoPorNombre(String tipo);

}