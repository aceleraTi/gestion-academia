package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.MateriaDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materias")
@RequiredArgsConstructor
@Tag(name = "MateriaRest", description = "Gestion de las Materias.")
public class MateriaRest {

    private final MateriaPort materiaPort;

    private final MateriaDtoMapper mapper;

    @PostMapping()
    @Operation(summary = "Agregar materia al pensum")
    public ResponseEntity<MateriaPostDto> crearMateria(@RequestBody MateriaPostDto materia) {
        return new ResponseEntity<>(
                this.mapper.toMateriaPostDto(
                        this.materiaPort.crearMateria(
                                this.mapper.toMateria(materia))),
                HttpStatus.CREATED);
    }


    @GetMapping("{id}")
    @Operation(summary = "Obtener una materia")
    public ResponseEntity<MateriaGetDto> obtenerMateria(@PathVariable Long id) {
        return new ResponseEntity<>(
                this.mapper.toMateriaGetDto(
                        this.materiaPort.obtenerMateria(id)),
                HttpStatus.OK);
    }


    @GetMapping("pensum/{idPensum}")
    @Operation(summary = "Obtener las materias de un pensum")
    public ResponseEntity<List<MateriaGetDto>> obtenerMateriasPensum(@PathVariable Long idPensum) {
        return new ResponseEntity<>(

                this.mapper.toMateriaGetDtos(
                        this.materiaPort.materiasIdPensum(idPensum)),
                HttpStatus.OK);
    }

}
