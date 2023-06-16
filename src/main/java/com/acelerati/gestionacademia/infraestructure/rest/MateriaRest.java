package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/materia")
@RequiredArgsConstructor
@Tag(name = "MateriaRest", description = "Gestion de las Materias.")
public class MateriaRest {

    private final MateriaPort materiaPort;

    @PostMapping()
    @Operation(summary = "Agregar materia al pensum")
    public ResponseEntity<Materia> crearMateria(@RequestBody Materia materia) {
        return new ResponseEntity<>(this.materiaPort.crearMateria(materia),
                HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @Operation(summary = "Obtener una materia")
    public ResponseEntity<Materia> obtenerMateria(@PathVariable Long id) {
        return new ResponseEntity<>(this.materiaPort.obtenerMateria(id),
                HttpStatus.OK);
    }

//    @GetMapping()
//    @Operation(summary = "True si existe una materia, false si no existe")
//    public ResponseEntity<?> obtenerMateriaValue(@PathVariable Long id) {
//        return new ResponseEntity<>(this.materiaPort.existeMateria(id),
//                HttpStatus.OK);
//    }
}
