package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumMateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.PensumDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
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

    @PostMapping()
    @Operation(summary = "Crear un pensum")
    public ResponseEntity<PensumPostDto> crearPensum(@RequestBody PensumPostDto pensum) {
        return new ResponseEntity<>(
                this.mapper.toPensumPostDto(
                        this.pensumInputPort.crearPensum(
                                this.mapper.toPensum(pensum))),
                HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @Operation(summary = "Obtener un pensum")
    public ResponseEntity<PensumGetDto> obtenerPensum(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.mapper.toPensumGetDto(
                        this.pensumInputPort.obtenerPensum(id)),
                HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar Pensum")
    public ResponseEntity<?> eliminarPensum(@PathVariable Long id) {
        this.pensumInputPort.eliminarId(id);
        return ResponseEntity.noContent().build();
    }
}
