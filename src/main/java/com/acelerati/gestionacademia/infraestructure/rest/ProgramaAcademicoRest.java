package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.model.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/programas-academicos")
@RequiredArgsConstructor
@Tag(name = "Programa Academico", description = "Crear un programa academico.")
public class ProgramaAcademicoRest {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    @PostMapping()
    @Operation(summary = "Crear un programa academico.")
    public ResponseEntity<ProgramaAcademico> save(@RequestBody ProgramaAcademico programaAcademico) {
        return new ResponseEntity<>(this.programaAcademicoInputPort.crearProgramaAcademico(programaAcademico),
                HttpStatus.CREATED);
    }
}
