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

    public Versao criar(VersaoReqDto versao) {
        Montadora montadora = montadoraService.listarPorId(versao.getMontadoraId());
        Modelo modelo = modeloRepository.findById(versao.getModeloId()).orElse(null);

        Versao versaoPersist = this.versaoReqDtoToVersao(versao);
        versaoPersist.setModelo(modelo);

        return versaoRepository.save(versaoPersist);
    }

    public Versao atualizar(Long id, VersaoReqDto versao) {
        if(versaoRepository.existsById(id)) {
            Montadora montadora = montadoraService.listarPorId(versao.getMontadoraId());
            Modelo modelo = modeloRepository.findById(versao.getModeloId()).orElse(null);
            Versao versaoPersist = this.versaoReqDtoToVersao(versao);
            versaoPersist.setId(id);
            versaoPersist.setMotor(versao.getMotor());

            return versaoRepository.save(versaoPersist);
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public void deletar(Long id) {
        if(versaoRepository.existsById(id)) {
            versaoRepository.deleteById(id);
            return;
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    private Versao versaoReqDtoToVersao(VersaoReqDto entrada) {
        Versao saida = new Versao();
        saida.setMotor(entrada.getMotor());

        Modelo modeloRetorno = modeloRepository.findById(entrada.getModeloId()).orElse(null);
        saida.setModelo(modeloRetorno);
        return saida;
    }
}
