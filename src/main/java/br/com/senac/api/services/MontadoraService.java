package br.com.senac.api.services;

import br.com.senac.api.dto.MontadoraReqDto;
import br.com.senac.api.entities.Montadora;
import br.com.senac.api.repositories.MontadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MontadoraService {

    @Autowired
    private MontadoraRepository montadoraRepository;

    public List<Montadora> listar() {
        return montadoraRepository.findAll();
    }

    public Montadora criar(MontadoraReqDto montadora) {
        Montadora montadoraSaida = this.montadoraReqDtoToMontadora(montadora);
        return montadoraRepository.save(montadoraSaida);
    }

    public Montadora atualizar(Long id, MontadoraReqDto montadora) {
        if(montadoraRepository.existsById(id)) {
            Montadora montadoraSaida = this.montadoraReqDtoToMontadora(montadora);
            montadoraSaida.setId(id);

            return montadoraRepository.save(montadoraSaida);
        }

        throw new RuntimeException("Montadora não encontrada!");
    }

    public void deletar (Long id) {
        if(montadoraRepository.existsById(id)) {
            montadoraRepository.deleteById(id);
            return;
        }

        throw new RuntimeException("Montadora não encontrada!");
    }

    private Montadora montadoraReqDtoToMontadora (MontadoraReqDto entrada) {
        Montadora saida = new Montadora();

        saida.setNome(entrada.getNome());

        return saida;
    }

    public Montadora listarPorId(Long id) {
        Optional<Montadora> montadoraRetorno = montadoraRepository.findById(id);
        if (montadoraRetorno.isPresent()) {
            return montadoraRetorno.get();
        }

        throw new RuntimeException("Cliente não encontrado");

    }
}
