package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.Usuario;
import com.acelerati.gestionacademia.domain.enums.TipoUsuarioEnum;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.MateriaDtoMapper;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/materias")
@RequiredArgsConstructor
@Tag(name = "MateriaRest", description = "Gestion de las Materias.")
public class MateriaRest {

    private final MateriaPort materiaPort;

    private final MateriaDtoMapper mapper;

    private final RestTemplePort restTemplate;


    @PostMapping()
    @Operation(summary = "Agregar materia al pensum",
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
    public ResponseEntity<MateriaPostDto> crearMateria(@RequestBody @Valid MateriaPostDto materia,
                                                       @RequestHeader(value = "idusuario") Long idUsuario) {

        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DIRECTOR.getId());
        return new ResponseEntity<>(
                this.mapper.toMateriaPostDto(
                        this.materiaPort.crearMateria(
                                this.mapper.toMateria(materia))),
                HttpStatus.CREATED);
    }


    @GetMapping("{idMateria}")
    @Operation(summary = "Obtener una materia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),


    })
    public ResponseEntity<MateriaGetDto> obtenerMateria(@Parameter(description = "id de la materia") @PathVariable Long idMateria) {
        return new ResponseEntity<>(
                this.mapper.toMateriaGetDto(
                        this.materiaPort.obtenerMateria(idMateria)),
                HttpStatus.OK);
    }


    @GetMapping("pensum/{idPensum}")
    @Operation(summary = "Obtener las materias de un pensum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),

    })
    public ResponseEntity<List<MateriaGetDto>> obtenerMateriasPensum(
            @Parameter(description = "id del pensum del cual se quieren obterner sus materias") @PathVariable Long idPensum) {
        return new ResponseEntity<>(
                this.mapper.toMateriaGetDtos(
                        this.materiaPort.materiasIdPensum(idPensum)),
                HttpStatus.OK);
    }

}
