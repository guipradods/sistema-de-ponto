package com.example.demo.service;

import com.example.demo.builder.UsuarioBuilder;
import com.example.demo.model.Usuario;
import com.example.demo.model.dto.UsuarioDTO;
import com.example.demo.repository.PontoRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PontoRepository pontoRepository;

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        var novoUsuario = usuarioBuilder.usuarioDtoToEntity(usuarioDTO);
        usuarioRepository.save(novoUsuario);
    }

    public Boolean checarCPFDisponivel(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findByCpf(usuarioDTO.getCpf()) == null;
    }

    public void atualizarBancoDeHoras(Usuario usuario) {
        var horasTrabalhadas = pontoRepository.findSumHorasTrabalhadas(usuario);
        usuario.setBancoDeHoras(horasTrabalhadas);
    }

    public Boolean checarCPFvalido(UsuarioDTO usuario) {
        if (usuario.getCpf().equals("00000000000") ||
                usuario.getCpf().equals("11111111111") ||
                usuario.getCpf().equals("22222222222") || usuario.getCpf().equals("33333333333") ||
                usuario.getCpf().equals("44444444444") || usuario.getCpf().equals("55555555555") ||
                usuario.getCpf().equals("66666666666") || usuario.getCpf().equals("77777777777") ||
                usuario.getCpf().equals("88888888888") || usuario.getCpf().equals("99999999999") ||
                (usuario.getCpf().length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(usuario.getCpf().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(usuario.getCpf().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == usuario.getCpf().charAt(9)) && (dig11 == usuario.getCpf().charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

}
