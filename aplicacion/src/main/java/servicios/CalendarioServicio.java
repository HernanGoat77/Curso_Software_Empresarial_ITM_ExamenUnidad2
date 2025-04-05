package festivos.api.aplicacion.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import festivos.api.dominio.entidades.*;
import festivos.api.core.servicios.*;
import festivos.api.infraestructura.repositorios.*;
import festivos.api.dominio.DTOs.*;
import festivos.api.infraestructura.integracion.*;

@Service
public class CalendarioServicio implements ICalendarioServicio {

    private ICalendarioRepositorio repositorio;

    private ITipoRepositorio tiporepositorio;

    private FestivoCliente integracion;

    public CalendarioServicio(ICalendarioRepositorio repositorio, ITipoRepositorio tiporepositorio,
            FestivoCliente integracion) {
        this.repositorio = repositorio;
        this.integracion = integracion;
        this.tiporepositorio = tiporepositorio;
    }

    @Override
    public List<FestivoDTO> obtenerFestivosAño(String año) {
        return integracion.obtenerFestivosAño(año);
    }

    @Override
    public boolean existeCalendarioParaAño(int año) {
        return repositorio.existeCalendarioParaAño(año);
    }

    @Override
    public boolean generar(int año) {
        try {

            if (repositorio.existeCalendarioParaAño(año)) {
                System.out.println("Ya existe calendario para el año " + año);
                return false;
            }

            // Obtenengo la lista de festivos desde la API Festivos
            List<FestivoDTO> festivosDto = integracion.obtenerFestivosAño(String.valueOf(año));

            LocalDate fechaInicio = LocalDate.of(año, 1, 1);
            LocalDate fechaFin = LocalDate.of(año, 12, 31);

            for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
                String tipo;
                Long id;
                final LocalDate fechaActual = fecha;

                boolean esFestivo = festivosDto.stream()
                        .anyMatch(f -> f.getFecha().equals(fechaActual));

                if (esFestivo) {
                    tipo = "Día festivo";
                    id = 3L;

                } else if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    tipo = "Fin de Semana";
                    id = 2L;
                } else {
                    tipo = "Día laboral";
                    id = 1L;
                }
                Date fechaHoraUTC = Date.from(
                        fecha.atStartOfDay(ZoneId.of("America/Bogota"))
                                .toInstant());

                Tipo tipoEntidad = tiporepositorio.obtenerTipoPorNombre(tipo);
                if (tipoEntidad == null) {
                    tipoEntidad = tiporepositorio.save(new Tipo(id, tipo));

                }

                String descripcion = fecha.getDayOfWeek().toString().toLowerCase();

                switch (fecha.getDayOfWeek()) {
                    case MONDAY:
                        descripcion = "Lunes";
                        break;
                    case TUESDAY:
                        descripcion = "Martes";
                        break;
                    case WEDNESDAY:
                        descripcion = "Miércoles";
                        break;
                    case THURSDAY:
                        descripcion = "Jueves";
                        break;
                    case FRIDAY:
                        descripcion = "Viernes";
                        break;
                    case SATURDAY:
                        descripcion = "Sábado";
                        break;
                    case SUNDAY:
                        descripcion = "Domingo";
                        break;
                }

                Calendario nuevoDia = new Calendario(fechaHoraUTC, tipoEntidad, descripcion);
                repositorio.save(nuevoDia);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Calendario> calendarioPorAño(int año) {
        return repositorio.calendarioPorAño(año);
    }
}
