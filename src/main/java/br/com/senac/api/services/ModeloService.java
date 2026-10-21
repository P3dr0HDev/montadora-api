package br.com.senac.api.services;

import br.com.senac.api.dto.ModeloReqDto;
import br.com.senac.api.entities.Modelo;
import br.com.senac.api.entities.Montadora;
import br.com.senac.api.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private MontadoraService montadoraService;

    public Modelo criar(ModeloReqDto modelo){
        Montadora montadora = montadoraService.listarPorId(modelo.getMontadoraId());

        Modelo modeloPersist = this.modeloReqDtoToModelo(modelo);
        modeloPersist.setMontadora(montadora);

        return modeloRepository.save(modeloPersist);
    }

    public Modelo atualizar (Long id, ModeloReqDto modelo) {
        if(modeloRepository.existsById(id)) {
            Montadora montadora = montadoraService.listarPorId(modelo.getMontadoraId());

            Modelo modeloPersist = this.modeloReqDtoToModelo(modelo);
            modeloPersist.setId(id);
            modeloPersist.setMontadora(montadora);

            return modeloRepository.save(modeloPersist);
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public void deletar(Long id) {
        if(modeloRepository.existsById(id)) {
            modeloRepository.existsById(id);
        }

        throw new RuntimeException("Modelo não encontrado!");
    }

    public List<Modelo> listarTodos() {return modeloRepository.findAll();}


    private Modelo modeloReqDtoToModelo(ModeloReqDto entrada) {
        Modelo saida = new Modelo();
        saida.setNome(entrada.getNome());

        return saida;
    }
}
