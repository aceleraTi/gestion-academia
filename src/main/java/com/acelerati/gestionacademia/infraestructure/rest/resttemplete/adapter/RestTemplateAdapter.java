package com.acelerati.gestionacademia.infraestructure.rest.resttemplete.adapter;

import com.acelerati.gestionacademia.domain.Usuario;
import com.acelerati.gestionacademia.domain.util.Url;
import com.acelerati.gestionacademia.infraestructure.exception.ApiException;
import com.acelerati.gestionacademia.infraestructure.exception.ConexionRechazadaException;
import com.acelerati.gestionacademia.infraestructure.exception.ForbiddenException;
import com.acelerati.gestionacademia.infraestructure.rest.resttemplete.RestTemplePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateAdapter implements RestTemplePort {
    private static final String ROLES_NO_COINCIDEN = "el rol no coincide con el usuario";

    private static final String CONEXION_RECHAZADA_GESTION_USUARIOS = "conexion rechazada con gestion usuarios";
    private final RestTemplate restTemplate;


    @Override
    public void validarTipoUsuario(Long idUsuario, Long idTipoUsuario) {
        if (Boolean.FALSE.equals(restTemplate.getForObject(Url.MICROSERVICIO_USUARIO +
                        "/validar" +
                        "/" + idUsuario +
                        "/" + idTipoUsuario,
                Boolean.class))) {
            throw new ForbiddenException(ROLES_NO_COINCIDEN);
        }
    }

    @Override
    public Usuario obtenerUsuario(Long idUsuario) {
        Usuario forObject;
        try {
            forObject = restTemplate.getForObject(Url.MICROSERVICIO_USUARIO +
                    "/" + idUsuario, Usuario.class);
        } catch (ResourceAccessException e) {
            throw new ConexionRechazadaException(CONEXION_RECHAZADA_GESTION_USUARIOS);
        } catch (HttpServerErrorException ex) {
            throw new ApiException(ex.getResponseBodyAsString());
        }
        return forObject;
    }

    public void validarPermisos(Long tipoUsuario, Long tipoUsuarioRequerido) {
        if (!tipoUsuario.equals(tipoUsuarioRequerido)) {
            throw new ForbiddenException(ROLES_NO_COINCIDEN);
        }
    }
}
