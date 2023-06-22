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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/programas-academicos")
@RequiredArgsConstructor
@Tag(name = "ProgramaAcademicoRest", description = "Gestion de los programas academicos.")
public class ProgramaAcademicoRest {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    private final ProgramaAcademicoDtoMapper mapper;

    private final RestTemplePort restTemplate;


    @PostMapping()
    @Operation(summary = "Crear un programa academico",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario que hace la solicitud",
                            required = true,
                            schema = @Schema(type = "integer"))
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),


    })
    public ResponseEntity<ProgramaAcademicoPostDto> crearProgramaAcademico(
            @RequestBody @Valid ProgramaAcademicoPostDto programaAcademico,
            @RequestHeader(value = "idusuario") Long idUsuario) {

        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
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
                            description = "id del usuario que hace la solicitud",
                            required = true,
                            schema = @Schema(type = "integer"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),


    })
    public ResponseEntity<ProgramaAcademicoPostDto> asignarDirector(
            @Parameter(description = "id del programa academico") @PathVariable Long idPrograma,
            @Parameter(description = "id del director de programa que se quiere asignar") @PathVariable Long idDirector,
            @RequestHeader(value = "idusuario") Long idUsuario
    ) {

        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
        this.restTemplate.validarTipoUsuario(idDirector, TipoUsuarioEnum.DIRECTOR.getId());
        this.programaAcademicoInputPort.asignarDirector(idPrograma, idDirector);
        return ResponseEntity.noContent().build();

    }


    @DeleteMapping("{idPrograma}")
    @Operation(summary = "Eliminar Programa Academico por id",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario que hace la solicitud",
                            required = true,
                            schema = @Schema(type = "integer"))
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),


    })
    public ResponseEntity<ProgramaAcademicoGetDto>
    eliminarProgramaAcademico(@Parameter(description = "id del programa academico") @PathVariable Long idPrograma,
                              @RequestHeader(value = "idusuario")
                              Long idUsuario) {
        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DECANO.getId());
        return new ResponseEntity<>(
                this.mapper.toProgramaAcademicoGetDto(
                        this.programaAcademicoInputPort.eliminarId(idPrograma)), HttpStatus.OK);
    }


}
