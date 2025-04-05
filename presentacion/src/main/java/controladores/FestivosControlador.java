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
@RequestMapping("/festivos")
public class FestivosControlador {

    @Autowired
    private ICalendarioServicio servicio;

    @RequestMapping(value = "/obtener/{año}", method = RequestMethod.GET)
    public List<FestivoDTO> obtener(@PathVariable String año) {
        return servicio.obtenerFestivosAño(año);
    }

}
