package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.TipoUsuario;
import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumMateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.PensumDtoMapper;
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
@RequestMapping("pensums")
@RequiredArgsConstructor
@Tag(name = "PensumRest", description = "Gestion de los pensums.")
public class PensumRest {

    private final PensumInputPort pensumInputPort;

    private final PensumDtoMapper mapper;

    private final RestTemplePort restTemple;

    @PostMapping()
    @Operation(summary = "Crear un pensum",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario",
                            required = true,
                            schema = @Schema(type = "integer"))
            })
    public ResponseEntity<PensumPostDto> crearPensum(@RequestBody PensumPostDto pensum,
                                                     @RequestHeader(value = "idusuario") Long idUsuario) {
        this.restTemple.tienePermiso(idUsuario, TipoUsuario.DIRECTOR.getId());
        return new ResponseEntity<>(
                this.mapper.toPensumPostDto(
                        this.pensumInputPort.crearPensum(
                                this.mapper.toPensum(pensum))),
                HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @Operation(summary = "Obtener un pensum"
    )
    public ResponseEntity<PensumGetDto> obtenerPensum(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.mapper.toPensumGetDto(
                        this.pensumInputPort.obtenerPensum(id)),
                HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Pensum",
            parameters = {
                    @Parameter(in = ParameterIn.HEADER,
                            name = "idusuario",
                            description = "id del usuario",
                            required = true,
                            schema = @Schema(type = "integer"))
            })
    public ResponseEntity<?> eliminarPensum(@PathVariable Long id,
                                            @RequestHeader(value = "idusuario") Long idUsuario) {
        this.restTemple.tienePermiso(idUsuario, TipoUsuario.DIRECTOR.getId());
        this.pensumInputPort.eliminarId(id);
        return ResponseEntity.noContent().build();
    }
}
