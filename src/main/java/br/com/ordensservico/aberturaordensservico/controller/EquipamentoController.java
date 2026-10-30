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

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    
    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @PostMapping
    public ResponseEntity<Equipamento> cadastrar(
         @Valid @RequestBody Equipamento equipamento) {

        Equipamento equipamentoCadastrado = equipamentoService.cadastrar(equipamento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(equipamentoCadastrado);
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
        @RequestBody Equipamento novosDados) {

        Optional<Equipamento> equipamentoAtualizado = equipamentoService.atualizar(id, novosDados);

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
