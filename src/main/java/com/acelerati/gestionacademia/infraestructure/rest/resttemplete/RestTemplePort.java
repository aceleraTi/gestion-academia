package com.acelerati.gestionacademia.infraestructure.rest.resttemplete;

import com.acelerati.gestionacademia.domain.Usuario;

public interface RestTemplePort {

    void validarTipoUsuario(Long idUsuario, Long idTipoUsuario);

    Usuario obtenerUsuario(Long idUsuario);

    void validarPermisos(Long tipoUsuario, Long tipoUsuarioRequerido);
}
