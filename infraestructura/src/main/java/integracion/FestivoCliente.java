package festivos.api.infraestructura.integracion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import festivos.api.dominio.DTOs.*;

@Service
public class FestivoCliente {

    @Autowired
    private RestTemplate restTemplate;

    public List<FestivoDTO> obtenerFestivosAño(String año) {
        String url = "http://localhost:3030/festivos/obtener/" + año;

        ResponseEntity<List<FestivoDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<FestivoDTO>>() {
                });

        return response.getBody();
    }

}
