package br.com.ordensservico.aberturaordensservico.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

import br.com.ordensservico.aberturaordensservico.service.EquipamentoService;
import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import jakarta.validation.Valid;
import br.com.ordensservico.aberturaordensservico.dto.EquipamentoRequest;


@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    
    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
         @Valid @RequestBody EquipamentoRequest dados) {

            Integer setorId = dados.getSetorId();

            if (setorId == null) {
                return ResponseEntity.badRequest().build();
            }

        Optional<Equipamento> equipamento = equipamentoService.cadastrar(dados);

        if (equipamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(equipamento.get());
    }
    

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {

        List<Equipamento> equipamentos = equipamentoService.listar();

        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping("/{id}")
        public ResponseEntity<Equipamento> buscarPorId(
            @PathVariable Integer id) {

        Optional<Equipamento> equipamentoOptional = equipamentoService.buscarPorId(id);

        if (equipamentoOptional.isPresent()) {
            return ResponseEntity.ok(equipamentoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
        @PathVariable Integer id,
        @Valid @RequestBody EquipamentoRequest dados) {

        Optional<Equipamento> equipamentoAtualizado = equipamentoService.atualizar(id, dados);

        if (equipamentoAtualizado.isPresent()) {
            return ResponseEntity.ok(equipamentoAtualizado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
        @PathVariable Integer id) {

        boolean excluido = equipamentoService.excluir(id);

        if (excluido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}

