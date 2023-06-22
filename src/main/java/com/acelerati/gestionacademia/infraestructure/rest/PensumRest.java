package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.Usuario;
import com.acelerati.gestionacademia.domain.enums.TipoUsuarioEnum;
import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.PensumDtoMapper;
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

@RestController
@RequestMapping("api/v1/pensums")
@RequiredArgsConstructor
@Tag(name = "PensumRest", description = "Gestion de los pensums.")
public class PensumRest {

    private final PensumInputPort pensumInputPort;

    private final PensumDtoMapper mapper;

    private final RestTemplePort restTemplate;

    @PostMapping()
    @Operation(summary = "Crear un pensum",
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
    public ResponseEntity<PensumPostDto> crearPensum(@RequestBody @Valid PensumPostDto pensum,
                                                     @RequestHeader(value = "idusuario") Long idUsuario) {
        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DIRECTOR.getId());
        return new ResponseEntity<>(
                this.mapper.toPensumPostDto(
                        this.pensumInputPort.crearPensum(
                                this.mapper.toPensum(pensum))),
                HttpStatus.CREATED);
    }


    @GetMapping("{idPensum}")
    @Operation(summary = "Obtener un pensum por id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content),


    })
    public ResponseEntity<PensumGetDto> obtenerPensum(@Parameter(description = "id del pensum que se quiere consultar") @PathVariable Long idPensum) {
        return new ResponseEntity<>(
                this.mapper.toPensumGetDto(
                        this.pensumInputPort.obtenerPensum(idPensum)),
                HttpStatus.OK);
    }


    @DeleteMapping("{idPensum}")
    @Operation(summary = "Eliminar Pensum",
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
    public ResponseEntity<PensumGetDto> eliminarPensum(@Parameter(description = "id del pensum que se quiere eliminar") @PathVariable Long idPensum,
                                                       @RequestHeader(value = "idusuario") Long idUsuario) {
        Usuario usuario = this.restTemplate.obtenerUsuario(idUsuario);
        this.restTemplate.validarPermisos(usuario.getTipoUsuario(), TipoUsuarioEnum.DIRECTOR.getId());
        return new ResponseEntity<>(
                this.mapper.toPensumGetDto(
                        this.pensumInputPort.eliminarId(idPensum)), HttpStatus.OK);
    }
}
