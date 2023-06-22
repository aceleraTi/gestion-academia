package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.application.util.UtilMateria;
import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.outputport.MateriaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static com.acelerati.gestionacademia.application.util.UtilMateria.*;

class MateriaCasoUsoTest {


    @InjectMocks
    private MateriaCasoUso materiaCasoUso;


    @Mock
    private MateriaRepositoryPort materiaRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoNombreMateriaMenorDeDosCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(UtilMateria.ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoNombreMateriaEsDeDosCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertNotEquals(ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoNombreMateriaEsMayorDeDosYMenorDeTreintaCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_ENTRE_2_Y_30_CARACTER);
        materia.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertNotEquals(ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoNombreMateriaEsDeTreintaCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_30_CARACTER);
        materia.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertNotEquals(ERROR_NOMBRE, exception.getMessage());
    }

    @Test
    void cuandoNombreMateriaMayorDeTreintaCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_MAYOR_30_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERROR_NOMBRE, exception.getMessage());
    }


    @Test
    void cuandoDescripcionMateriaEsMenorACincoCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_1_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERRO_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoDescripcionMateriaEsDeCincoCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_5_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERRO_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoDescripcionMateriaEsMayorACincoYMenorADoscientosCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_MAYOR_5_Y_MENOR_200_CARACTER);
        when(this.materiaRepositoryPort.crearMateria(any())).thenReturn(materia);
        Materia materiaCreada = this.materiaCasoUso.crearMateria(materia);
        assertNotNull(materiaCreada);
        assertEquals(NOMBRE_2_CARACTER, materiaCreada.getNombre());
    }


    @Test
    void cuandoDescripcionMateriaEsIgualADoscientosCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_200_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERRO_DESCRIPCION, exception.getMessage());
    }

    @Test
    void cuandoDescripcionMateriaEsMayorADoscientosCaracteres() {
        Materia materia = new Materia();
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_MAYOR_200_CARACTER);
        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERRO_DESCRIPCION, exception.getMessage());
    }


    @Test
    void cuandoMateriaPrerequisitoNoEsDelMismoPensum() {
        Materia materiaPrerequisito = new Materia();
        materiaPrerequisito.setIdPensum(2L);

        when(this.materiaCasoUso.obtenerMateria((any()))).thenReturn(materiaPrerequisito);


        Materia materia = new Materia();
        materia.setIdMateriaPrerequisito(1L);
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_MAYOR_5_Y_MENOR_200_CARACTER);
        materia.setIdPensum(1L);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> materiaCasoUso.crearMateria(materia));
        assertNotNull(exception);
        assertEquals(ERROR_PREREQUISITO, exception.getMessage());
    }

    @Test
    void cuandoMateriaPrerequisitoEsDelMismoPensum() {
        Materia materiaPrerequisito = new Materia();
        materiaPrerequisito.setIdPensum(1L);

        when(this.materiaCasoUso.obtenerMateria((any()))).thenReturn(materiaPrerequisito);


        Materia materia = new Materia();
        materia.setIdMateriaPrerequisito(1L);
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_MAYOR_5_Y_MENOR_200_CARACTER);
        materia.setIdPensum(1L);

        when(this.materiaRepositoryPort.crearMateria(any())).thenReturn(materia);


        Materia materiaCreada = this.materiaCasoUso.crearMateria(materia);
        assertNotNull(materiaCreada);
        assertEquals(NOMBRE_2_CARACTER, materiaCreada.getNombre());
    }

    @Test
    void cuandoSeObtieneMateriaPorIdNoExiste() {
        BadRequestException badRequestException = new BadRequestException(MATERIA_NO_EXISTE);
        doThrow(badRequestException).when(materiaRepositoryPort).buscarId(any());
        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> materiaCasoUso.obtenerMateria(any()));
        assertNotNull(exception);
        assertEquals(MATERIA_NO_EXISTE, exception.getMessage());
    }

    @Test
    void cuandoSeObtieneMateriaPorIdExiste() {
        Materia materia = new Materia();
        materia.setIdMateriaPrerequisito(1L);
        materia.setNombre(NOMBRE_2_CARACTER);
        materia.setDescripcion(DESCRIPCION_MAYOR_5_Y_MENOR_200_CARACTER);

        when(this.materiaRepositoryPort.buscarId(any())).thenReturn(materia);


        Materia materiaCreada = this.materiaCasoUso.obtenerMateria(any());
        assertNotNull(materiaCreada);
        assertEquals(NOMBRE_2_CARACTER, materiaCreada.getNombre());
    }

    @Test
    void cuandoSeObtieneUnaListaVaciaDeMateriasPorIdDelPensum() {
        when(this.materiaRepositoryPort.materiasIdPensum(any())).thenReturn(new ArrayList<>());

        List<Materia> materias = this.materiaCasoUso.materiasIdPensum(any());
        assertNotNull(materias);
        assertEquals(0, materias.size());
    }

    @Test
    void cuandoSeObtieneUnaListaDeMateriasPorIdDelPensum() {
        List<Materia> materias = Arrays.asList(new Materia(), new Materia());
        when(this.materiaRepositoryPort.materiasIdPensum(any())).thenReturn(materias);

        List<Materia> materiasRepo = this.materiaCasoUso.materiasIdPensum(any());
        assertNotNull(materiasRepo);
        assertEquals(2, materiasRepo.size());
    }
}