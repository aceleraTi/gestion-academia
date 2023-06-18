package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.domain.TipoUsuario;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.mapper.ProgramaAcademicoMapper;
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
@RequestMapping("programas-academicos")
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

        this.restTemplate.tienePermiso(idUsuario, TipoUsuario.DECANO.getId());

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

        this.restTemplate.tienePermiso(idUsuario, TipoUsuario.DECANO.getId());
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
    public ResponseEntity<?> eliminarProgramaAcademico(@PathVariable Long id,
                                                       @RequestHeader(value = "idusuario")
                                                       Long idUsuario) {
        this.restTemplate.tienePermiso(idUsuario, TipoUsuario.DECANO.getId());
        this.programaAcademicoInputPort.eliminarId(id);
        return ResponseEntity.noContent().build();
    }


}
