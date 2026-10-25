package br.com.senac.api.controllers;

import br.com.senac.api.dto.VersaoReqDto;
import br.com.senac.api.entities.Versao;
import br.com.senac.api.services.VersaoService;
import br.com.senac.api.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/versao")
public class VersaoController{
    @Autowired
    private VersaoService versaoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Versao>> listarTodos() {
        return ResponseEntity.ok(versaoService.listarTodos());

    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@RequestBody VersaoReqDto versao) {
        try {
            return ResponseEntity
                    .status(201)
                    .body(versaoService.criar(versao));

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
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @RequestBody VersaoReqDto versao
    ) {
        try {
            return ResponseEntity.ok(versaoService.atualizar(
                    id,versao
            ));
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
            versaoService.deletar(id);
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
