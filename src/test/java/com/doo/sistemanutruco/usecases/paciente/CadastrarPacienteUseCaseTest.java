package com.doo.sistemanutruco.usecases.paciente;

import com.doo.sistemanutruco.entities.paciente.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class)
class CadastrarPacienteUseCaseTest {

    @Mock
    PacienteDAO dao;

    @InjectMocks
    CadastrarPacienteUseCase sut;

    @Test
    @DisplayName("Deve cadastrar Paciente com sucesso")
    void deveCadastrarPacienteComSucesso() {
        when(dao.findByCpf("123")).thenReturn(Optional.empty());
        Paciente paciente = new Paciente("123", "teste", LocalDate.of(2000, 7, 2), 16999998888L,
                "teste@email.com",90.0, 1.80, "ganhar massa");
        assertDoesNotThrow(() -> sut.cadastrar(paciente));
        verify(dao, times(1)).create(any());
    }

}