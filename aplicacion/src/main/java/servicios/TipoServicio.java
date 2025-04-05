package festivos.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import festivos.api.dominio.entidades.*;
import festivos.api.core.servicios.*;
import festivos.api.infraestructura.repositorios.*;

@Service
public class TipoServicio implements ITipoServicio {

    @Autowired
    private ITipoRepositorio repositorio;

    @Override
    public Tipo obtenerTipoPorNombre(String tipo) {
        return repositorio.obtenerTipoPorNombre(tipo);
    }

}
