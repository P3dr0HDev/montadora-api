package br.com.senac.api.services;

import br.com.senac.api.dto.VersaoReqDto;
import br.com.senac.api.entities.Modelo;
import br.com.senac.api.entities.Montadora;
import br.com.senac.api.entities.Versao;
import br.com.senac.api.repositories.ModeloRepository;
import br.com.senac.api.repositories.VersaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersaoService {

    @Autowired
    private VersaoRepository versaoRepository;

    @Autowired
    private MontadoraService montadoraService;
    @Autowired
    private ModeloRepository modeloRepository;

    public Modelo criar(VersaoReqDto modelo) {
        Montadora montadora = montadoraService.listarPorId(modelo.getMontadoraId());

        Modelo modeloPersist = this.modeloReqDtoToModelo(modelo);
        modeloPersist.setMontadora(montadora);

        return modeloRepository.save(modeloPersist);
    }

    public Modelo atualizar(Long id, VersaoReqDto modeloReqDto) {
        if(modeloRepository.existsById(id)) {
            Montadora montadora = montadoraService.listarPorId(modeloReqDto.getMontadoraId());

            Modelo modeloPersist = this.modeloReqDtoToModelo(modeloReqDto);
            modeloPersist.setId(id);
            modeloPersist.setMontadora(montadora);

            return modeloRepository.save(modeloPersist);
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public void deletar(Long id) {
        if(modeloRepository.existsById(id)) {
            modeloRepository.deleteById(id);
            return;
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    private Modelo modeloReqDtoToModelo(VersaoReqDto entrada) {
        Versao saida = new Versao();
        saida.setMotor(entrada.getMotor());

        Montadora montadoraRetorno = montadoraService.listarPorId(entrada.getMontadoraId());
        saida.setMotor(Retorno);
        return saida;
    }
}
