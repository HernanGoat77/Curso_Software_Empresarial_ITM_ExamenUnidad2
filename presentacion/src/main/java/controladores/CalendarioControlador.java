package festivos.api.aplicacion.presentacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import festivos.api.dominio.entidades.*;
import festivos.api.dominio.DTOs.*;
import festivos.api.core.servicios.*;

@RestController
@RequestMapping("/api/calendario")
public class CalendarioControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @RequestMapping(value = "/generar/{año}", method = RequestMethod.POST)
    public boolean generar(@PathVariable int año) {
        return servicio.generar(año);
    }

    @RequestMapping(value = "/listar/{año}", method = RequestMethod.GET)
    public List<Calendario> calendarioPorAño(@PathVariable int año) {
        return servicio.calendarioPorAño(año);
    }

}
