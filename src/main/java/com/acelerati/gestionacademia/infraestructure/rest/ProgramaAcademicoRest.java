package com.acelerati.gestionacademia.infraestructure.rest;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.mapper.ProgramaAcademicoMapper;
import com.acelerati.gestionacademia.infraestructure.rest.dto.ProgramaAcademicoPostDto;
import com.acelerati.gestionacademia.infraestructure.rest.mapper.ProgramaAcademicoDtoMapper;
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
@RequestMapping("programas-academicos")
@RequiredArgsConstructor
@Tag(name = "ProgramaAcademicoRest", description = "Gestion de los programas academicos.")
public class ProgramaAcademicoRest {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    private final ProgramaAcademicoDtoMapper mapper;

    @PostMapping()
    @Operation(summary = "Crear un programa academico.")
    public ResponseEntity<ProgramaAcademicoPostDto> crearProgramaAcademico(
            @RequestBody ProgramaAcademicoPostDto programaAcademico) {
        return new ResponseEntity<>(
                this.mapper.toProgramaAcademicoPostDto(
                        this.programaAcademicoInputPort
                                .crearProgramaAcademico(
                                        this.mapper.toProgramaAcademico(programaAcademico))),
                HttpStatus.CREATED);
    }
}
