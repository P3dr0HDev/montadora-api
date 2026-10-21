package br.com.senac.api.controllers;

import br.com.senac.api.dto.ModeloReqDto;
import br.com.senac.api.entities.Modelo;
import br.com.senac.api.services.ModeloService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modelo")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @GetMapping("/listar")
    public ResponseEntity<List<Modelo>> listarTodos(){return ResponseEntity.ok(modeloService.listarTodos());}

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody ModeloReqDto modelo) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(modeloService.criar(modelo));

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

    @PutMapping("atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ModeloReqDto modelo) {
        try {
            return ResponseEntity.ok(modeloService.atualizar(id, modelo));

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
            modeloService.deletar(id);
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
