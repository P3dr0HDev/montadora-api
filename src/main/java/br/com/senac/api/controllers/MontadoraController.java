package br.com.senac.api.controllers;

import br.com.senac.api.dto.MontadoraReqDto;
import br.com.senac.api.entities.Montadora;
import br.com.senac.api.services.MontadoraService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/montadora")
public class MontadoraController {

    @Autowired
    private MontadoraService montadoraService;

    @GetMapping("/listar")
    public ResponseEntity<List<Montadora>> listarTodos(){
        return ResponseEntity.ok(montadoraService.listar());
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody MontadoraReqDto montadora) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(montadoraService.criar(montadora));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody MontadoraReqDto montadora) {
        try {
            return ResponseEntity.ok(montadoraService.atualizar(id, montadora));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            montadoraService.deletar(id);
            return ResponseEntity.ok(null);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseUtil.parserMensagem(e.getMessage()));
        }
    }
}