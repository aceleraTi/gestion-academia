package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.enums.TipoUsuarioEnum;
import com.acelerati.gestionacademia.domain.Usuario;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.ProgramaAcademicoGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.ProgramaAcademicoPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.ProgramaAcademicoDtoMapper;
import com.acelerati.gestionacademia.infraestructure.rest.resttemplete.RestTemplePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/programas-academicos")
@RequiredArgsConstructor
@Tag(name = "ProgramaAcademicoRest", description = "Gestion de los programas academicos.")
public class ProgramaAcademicoRest {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    private final ProgramaAcademicoDtoMapper mapper;

    private final RestTemplePort restTemplate;


    @PostMapping()
    @Operation(summary = "Crear un programa academico.",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario",
                            required = true,
                            schema = @Schema(type = "integer"))
            })
    public ResponseEntity<ProgramaAcademicoPostDto> crearProgramaAcademico(
            @RequestBody ProgramaAcademicoPostDto programaAcademico,
            @RequestHeader(value = "idusuario") Long idUsuario) {

        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
        this.restTemplate.validarTipoUsuario(programaAcademico.getIdDirector(), TipoUsuarioEnum.DIRECTOR.getId());
        return new ResponseEntity<>(
                this.mapper.toProgramaAcademicoPostDto(
                        this.programaAcademicoInputPort
                                .crearProgramaAcademico(
                                        this.mapper.toProgramaAcademico(programaAcademico))),
                HttpStatus.CREATED);
    }


    @PutMapping("{idPrograma}/{idDirector}")
    @Operation(summary = "Asignar director academico en un programa Academico",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario",
                            required = true,
                            schema = @Schema(type = "integer"))
            }
    )
    public ResponseEntity<ProgramaAcademicoPostDto> asignarDirector(
            @PathVariable Long idPrograma, @PathVariable Long idDirector,
            @RequestHeader(value = "idusuario") Long idUsuario
    ) {

        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
        this.programaAcademicoInputPort.asignarDirector(idPrograma, idDirector);
        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Programa Academico",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario",
                            required = true,
                            schema = @Schema(type = "integer"))
            }
    )
    public ResponseEntity<ProgramaAcademicoGetDto> eliminarProgramaAcademico(@PathVariable Long id,
                                                                             @RequestHeader(value = "idusuario")
                                                                             Long idUsuario) {
        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
        return new ResponseEntity<>(
                this.mapper.toProgramaAcademicoGetDto(
                        this.programaAcademicoInputPort.eliminarId(id)), HttpStatus.OK);
    }


}
