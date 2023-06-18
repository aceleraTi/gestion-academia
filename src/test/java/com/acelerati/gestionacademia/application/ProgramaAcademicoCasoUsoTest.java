package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.exception.NotFoundException;
import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoPort;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.acelerati.gestionacademia.application.util.UtilProgramaAcademico.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;


class ProgramaAcademicoCasoUsoTest {
    @InjectMocks
    private ProgramaAcademicoCasoUso programaAcademicoCasoUso;

    @Mock
    private NivelEducativoPort nivelEducativoPort;

    @Mock
    private ProgramaAcademicoRepositoryPort programaAcademicoRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoElNombreProgramaTieneMenosDeOchoCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaTieneOchoCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_8_CARACTER);
        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaTieneMasDeOchoCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaTieneCuarentaCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_40_CARACTER);
        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaTieneMasDeCuarentaCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_41_CARACTER);
        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaNoEsUnico() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(true);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_NOMBRE_UNICO, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaEsUnico() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaNoContieneSoloLetrasMenor65Ascii() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_CARACTER_ESPECIAL_MENOR_65);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_LETRAS, exception.getMessage());
    }

    @Test
    void cuandoElNombreProgramaNoContieneSoloLetrasEntre91Y96Ascii() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_CARACTER_ESPECIAL_MAYOR_90_MENOR_97);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_LETRAS, exception.getMessage());
    }


    @Test
    void cuandoLaDescripcionTieneMenosDeVeinteCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);

        programaAcademico.setDescripcion(DESCRIPCION_1_CARACTER);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }


    @Test
    void cuandoLaDescripcionTieneVeinteCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);

        programaAcademico.setDescripcion(DESCRIPCION_20_CARACTER);
        programaAcademico.setIdNivelEducativo(1L);

        when(this.nivelEducativoPort.existeId(any())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(NO_EXISTE_NIVEL_EDUCATIVO, exception.getMessage());
    }

    @Test
    void cuandoLaDescripcionEsMayorAVeinteYMenorADoscientosCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);

        programaAcademico.setDescripcion(DESCRIPCION_MAYOR_20_Y_MENOR_200_CARACTER);
        programaAcademico.setIdNivelEducativo(1L);

        when(this.nivelEducativoPort.existeId(any())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(NO_EXISTE_NIVEL_EDUCATIVO, exception.getMessage());
    }

    @Test
    void cuandoLaDescripcionTieneDoscientosCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);

        programaAcademico.setDescripcion(DESCRIPCION_200_CARACTER);
        programaAcademico.setIdNivelEducativo(1L);

        when(this.nivelEducativoPort.existeId(any())).thenReturn(false);

        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(NO_EXISTE_NIVEL_EDUCATIVO, exception.getMessage());
    }

    @Test
    void cuandoLaDescripcionTieneMasDeDoscientosCaracter() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);

        programaAcademico.setDescripcion(DESCRIPCION_MAYOR_200_CARACTER);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico));
        assertNotNull(exception);
        assertEquals(ERROR_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoElProgramaAcademicoSeCrea() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setNombre(NOMBRE_ENTRE_8_Y_40_CARACTER);
        when(this.programaAcademicoCasoUso.existeNombre(any())).thenReturn(false);
        programaAcademico.setDescripcion(DESCRIPCION_MAYOR_20_Y_MENOR_200_CARACTER);

        when(this.nivelEducativoPort.existeId(any())).thenReturn(true);
        when(this.programaAcademicoRepositoryPort.crearProgramaAcademico(any()))
                .thenReturn(programaAcademico);


        ProgramaAcademico crearProgramaAcademico =
                this.programaAcademicoCasoUso.crearProgramaAcademico(programaAcademico);
        assertNotNull(crearProgramaAcademico);
        assertEquals(NOMBRE_ENTRE_8_Y_40_CARACTER, crearProgramaAcademico.getNombre());
    }

    @Test
    void cuandoExisteUnNombre() {
        when(this.programaAcademicoRepositoryPort.existeNombre(any())).thenReturn(true);

        Boolean aBoolean = this.programaAcademicoCasoUso.existeNombre(any());
        assertNotNull(aBoolean);
        assertTrue(aBoolean);
    }

    @Test
    void cuandoNoExisteUnNombre() {
        when(this.programaAcademicoRepositoryPort.existeNombre(any())).thenReturn(false);

        Boolean aBoolean = this.programaAcademicoCasoUso.existeNombre(any());
        assertNotNull(aBoolean);
        assertFalse(aBoolean);
    }


    @Test
    void cuandoExisteUnProgramaAcademicoPorId() {
        when(this.programaAcademicoRepositoryPort.buscarId(any())).thenReturn(new ProgramaAcademico());
        ProgramaAcademico programaAcademico = this.programaAcademicoCasoUso.buscarId(any());
        assertNotNull(programaAcademico);
    }

    @Test
    void cuandoNoExisteUnProgramaAcademicoPorId() {
        BadRequestException badRequestException = new BadRequestException(NO_EXISTE_PROGRAMA_ACADEMICO);
        doThrow(badRequestException).when(programaAcademicoRepositoryPort).buscarId(any());
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.programaAcademicoCasoUso.buscarId(any()));
        assertNotNull(exception);
        assertEquals(NO_EXISTE_PROGRAMA_ACADEMICO, exception.getMessage());
    }

    @Test
    void cuandoSeQuiereEliminarUnProgramaAcademicoPorIdConUnDirectorAsignado() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setIdDirector(1L);
        when(this.programaAcademicoCasoUso.buscarId(any())).thenReturn(programaAcademico);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.programaAcademicoCasoUso.eliminarId(any()));
        assertNotNull(exception);
        assertEquals(ERROR_DIRECTOR, exception.getMessage());

    }

    @Test
    void cuandoSeQuiereEliminarUnProgramaAcademicoPorIdSinUnDirectorAsignado() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setPensums(List.of(new Pensum()));
        when(this.programaAcademicoCasoUso.buscarId(any())).thenReturn(programaAcademico);
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.programaAcademicoCasoUso.eliminarId(any()));
        assertNotNull(exception);
        assertEquals(ERROR_PENSUM, exception.getMessage());

    }


    @Test
    void cuandoSeQuiereEliminarUnProgramaAcademicoPorIdSinUnDirectorAsignadoYSinPensums() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setId(1L);
        programaAcademico.setPensums(new ArrayList<>());
        when(this.programaAcademicoCasoUso.buscarId(any())).thenReturn(programaAcademico);

        doNothing().when(programaAcademicoRepositoryPort).eliminarId(any());
        ProgramaAcademico programaAcademico1 = this.programaAcademicoCasoUso.eliminarId(any());
        assertNotNull(programaAcademico1);

    }

    @Test
    void cuandoSeQuierAsignarUnDirectorAUnProgramaQueYaTieneDirector() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setId(1L);
        programaAcademico.setIdDirector(2L);
        when(this.programaAcademicoCasoUso.buscarId(any())).thenReturn(programaAcademico);


        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.programaAcademicoCasoUso.asignarDirector(1L, 2L));
        assertNotNull(exception);
        assertEquals(ERROR_DIRECTOR, exception.getMessage());

    }

    @Test
    void cuandoSeQuierAsignarUnDirectorAUnProgramaQueNoTieneDirector() {
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setId(1L);
        when(this.programaAcademicoCasoUso.buscarId(any())).thenReturn(programaAcademico);

        when(this.programaAcademicoRepositoryPort.actualizarDirector(any(), any())).thenReturn(1);

        ProgramaAcademico programaAcademico1 = this.programaAcademicoCasoUso.asignarDirector(1L, 2L);

        assertNotNull(programaAcademico1);

    }

}