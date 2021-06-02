package com.example.demo.service;

import com.example.demo.model.Ponto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PontoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.Optional;

@Service
public class PontoService {

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Ponto registrarHora(Long usuarioId) {

        if (!checarDataNoBanco(LocalDate.now(), usuarioId)) {
            pontoRepository.save(new Ponto(LocalDate.now(), usuarioRepository.findById(usuarioId).get()));
        }

        Ponto ponto = pontoRepository.findByDiaDoMesAndUsuario(LocalDate.now(), usuarioRepository.findById(usuarioId).get()).orElseThrow();

        if (ponto.getPontoUm() == null) {
            ponto.setPontoUm(LocalTime.now());
            pontoRepository.save(ponto);
        } else if (ponto.getPontoDois() == null) {
            ponto.setPontoDois(LocalTime.now());
            pontoRepository.save(ponto);
        } else if (ponto.getPontoTres() == null) {
            if (ponto.getPontoDois().plusHours(1).isBefore(LocalTime.now())) {
                ponto.setPontoTres(LocalTime.now());
                pontoRepository.save(ponto);
            } else {
                return ponto;
            }
        } else if (ponto.getPontoQuatro() == null) {
            ponto.setPontoQuatro(LocalTime.now());
            atualizarHorasTrabalhadas(ponto);
            pontoRepository.save(ponto);
            usuarioService.atualizarBancoDeHoras(usuarioRepository.findById(usuarioId).get());
            pontoRepository.save(ponto);
        }

        return ponto;

    }

    public Boolean checarDataNoBanco(LocalDate data, Long usuarioId) {
        Optional<Usuario> user = usuarioRepository.findById(usuarioId);
        return pontoRepository.findByDiaDoMesAndUsuario(data, user.get()).isPresent();
    }

    public Boolean checarUsuario(Long usuarioId) {
        Optional<Usuario> user = usuarioRepository.findById(usuarioId);
        return user.isPresent();
    }

    public Boolean checarDiaDaSemanaValido(LocalDate data) {
        if (data.getDayOfWeek() == DayOfWeek.of(6) || data.getDayOfWeek() == DayOfWeek.of(7)) {
            return false;
        }
        return true;
    }

    public void atualizarHorasTrabalhadas(Ponto ponto) {

        LocalTime registroUm = ponto.getPontoQuatro().minus(Duration.ofSeconds(ponto.getPontoTres().toSecondOfDay()));
        LocalTime registroDois = ponto.getPontoDois().minus(Duration.ofSeconds(ponto.getPontoUm().toSecondOfDay()));

        double horasTrabalhadas = (double) (registroUm.plus(Duration.ofSeconds(registroDois.toSecondOfDay()))).getLong(ChronoField.SECOND_OF_DAY) / 3600;

        double horasTrabalhadasFormatado = Math.round(horasTrabalhadas * 100.0) / 100.0;

        ponto.setHorasTrabalhadas(horasTrabalhadasFormatado);

    }

}
