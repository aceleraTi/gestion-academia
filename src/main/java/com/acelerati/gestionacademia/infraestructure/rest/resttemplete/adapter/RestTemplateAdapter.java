package com.acelerati.gestionacademia.infraestructure.rest.resttemplete.adapter;

import com.acelerati.gestionacademia.domain.TipoUsuario;
import com.acelerati.gestionacademia.domain.util.Url;
import com.acelerati.gestionacademia.infraestructure.exception.ForbiddenException;
import com.acelerati.gestionacademia.infraestructure.rest.resttemplete.RestTemplePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateAdapter implements RestTemplePort {

    private static final String SIN_PERMISOS = "no tiene permisos para realizar esta accion";

    private final RestTemplate restTemplate;

    @Override
    public void tienePermiso(Long idUsuario, Long idTipoUsuario) {
        if (Boolean.FALSE.equals(restTemplate.getForObject(Url.MICROSERVICIO_USUARIO +
                        "/validar" +
                        "/" + idUsuario +
                        "/" + TipoUsuario.DECANO.getId(),
                Boolean.class))) {
            throw new ForbiddenException(SIN_PERMISOS);
        }
    }
}
