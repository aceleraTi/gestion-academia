package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NivelEducativoCasoUsoTest {
    @InjectMocks
    private NivelEducativoCasoUso nivelEducativoCasoUso;

    @Mock
    private NivelEducativoRepositoryPort nivelEducativoRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void cuandoExisteUnNivelEducativoPorId() {
        when(this.nivelEducativoRepositoryPort.existeId(any())).thenReturn(true);

        Boolean aBoolean = this.nivelEducativoCasoUso.existeId(any());
        assertNotNull(aBoolean);
        assertTrue(aBoolean);
    }

    @Test
    void cuandoNoExisteUnNivelEducativoPorId() {
        when(this.nivelEducativoRepositoryPort.existeId(any())).thenReturn(false);

        Boolean aBoolean = this.nivelEducativoCasoUso.existeId(any());
        assertFalse(aBoolean);
    }
}