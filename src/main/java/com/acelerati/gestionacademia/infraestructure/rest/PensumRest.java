package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pensum")
@RequiredArgsConstructor
@Tag(name = "PensumRest", description = "Gestion de los pensums.")
public class PensumRest {

    private final PensumInputPort pensumInputPort;

    @PostMapping()
    @Operation(summary = "Crear un pensum")
    public ResponseEntity<Pensum> crearPensum(@RequestBody Pensum pensum) {
        return new ResponseEntity<>(this.pensumInputPort.crearPensum(pensum),
                HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @Operation(summary = "Obtener un pensum")
    public ResponseEntity<Pensum> obtenerPensum(@PathVariable Long id) {
        return new ResponseEntity<>(this.pensumInputPort.obtenerPensum(id),
                HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "True si existe un pensum, false si no existe")
    public ResponseEntity<?> obtenerPensumValue(@PathVariable Long id) {
        return new ResponseEntity<>(this.pensumInputPort.existePensum(id),
                HttpStatus.OK);
    }
}
