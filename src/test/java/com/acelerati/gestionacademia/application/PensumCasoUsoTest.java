package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import com.acelerati.gestionacademia.infraestructure.outputport.PensumRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static com.acelerati.gestionacademia.application.util.UtilMateria.MATERIA_NO_EXISTE;
import static com.acelerati.gestionacademia.application.util.UtilPensum.ERROR_EXISTE_PENSUM;
import static com.acelerati.gestionacademia.application.util.UtilPensum.NO_EXISTE_PENSUM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class PensumCasoUsoTest {
    @InjectMocks
    private PensumCasoUso PensumCasoUso;


    @Mock
    private ProgramaAcademicoInputPort programaAcademicoInputPort;
    @Mock
    private PensumRepositoryPort pensumRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoExisteUnPensumCreadoConElMismoAnio() {
        Pensum pensum = new Pensum();
        pensum.setAnio(2022);
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setPensums(List.of(pensum));
        when(this.programaAcademicoInputPort.buscarId(any())).thenReturn(programaAcademico);


        BadRequestException exception = assertThrows(
                BadRequestException.class, () ->
                        this.PensumCasoUso.crearPensum(pensum));

        assertNotNull(exception);
        assertEquals(ERROR_EXISTE_PENSUM, exception.getMessage());

    }

    @Test
    void cuandoNoExisteUnPensumCreadoConElMismoAnio() {
        Pensum pensum = new Pensum();
        pensum.setAnio(2022);
        ProgramaAcademico programaAcademico = new ProgramaAcademico();
        programaAcademico.setPensums(List.of(pensum));
        when(this.programaAcademicoInputPort.buscarId(any())).thenReturn(programaAcademico);

        Pensum pensumCrear = new Pensum();
        pensum.setAnio(2023);

        when(this.pensumRepositoryPort.crearPensum(any())).thenReturn(pensumCrear);


        Pensum pen = this.PensumCasoUso.crearPensum(pensumCrear);

        assertNotNull(pen);
        assertEquals(pensumCrear.getAnio(), pen.getAnio());

    }


    @Test
    void cuandoExisteUnPensumPorId() {
        when(this.pensumRepositoryPort.obtenerPensum(any())).thenReturn(new Pensum());

        Pensum pensum = this.PensumCasoUso.obtenerPensum(any());
        assertNotNull(pensum);
    }

    @Test
    void cuandoNoExisteUnPensumPorId() {
        BadRequestException badRequestException = new BadRequestException(NO_EXISTE_PENSUM);
        doThrow(badRequestException).when(pensumRepositoryPort).obtenerPensum(any());
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.PensumCasoUso.obtenerPensum(any()));
        assertNotNull(exception);
        assertEquals(NO_EXISTE_PENSUM, exception.getMessage());
    }


    @Test
    void cuandoExisteUnPensumPorIdEsVerdadero() {
        when(this.pensumRepositoryPort.existeId(any())).thenReturn(true);

        Boolean aBoolean = this.PensumCasoUso.existePensum(any());
        assertTrue(aBoolean);
    }

    @Test
    void cuandoExisteUnPensumPorIdEsFalso() {
        when(this.pensumRepositoryPort.existeId(any())).thenReturn(false);

        Boolean aBoolean = this.PensumCasoUso.existePensum(any());
        assertFalse(aBoolean);
    }



}