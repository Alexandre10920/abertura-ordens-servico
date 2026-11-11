package br.com.ordensservico.aberturaordensservico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;
import br.com.ordensservico.aberturaordensservico.service.OrdemServicoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController 
@RequestMapping ("/ordens-servico")
public class OrdemServicoController {
    
    private final OrdemServicoService ordemServicoService;

    public OrdemServicoController(OrdemServicoService ordemServicoService) {
        this.ordemServicoService = ordemServicoService;
    }

    @PostMapping
    public ResponseEntity<OrdemServico> cadastrar(
         @Valid @RequestBody OrdemServicoRequest dados) {

            Integer equipamentoId = dados.getEquipamentoId();

            if (equipamentoId == null) {
                return ResponseEntity.badRequest().build();
            }

        Optional<OrdemServico> ordemServico = ordemServicoService.cadastrar(dados);

        if (ordemServico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(ordemServico.get());
    }
    

    @GetMapping
    public ResponseEntity<List<OrdemServico>> listar() {

        List<OrdemServico> ordensServico = ordemServicoService.listar();

        return ResponseEntity.ok(ordensServico);
    }

    @GetMapping("/{id}")
        public ResponseEntity<OrdemServico> buscarPorId(
            @PathVariable Integer id) {

        Optional<OrdemServico> ordemServicoOptional = ordemServicoService.buscarPorId(id);

        if (ordemServicoOptional.isPresent()) {
            return ResponseEntity.ok(ordemServicoOptional.get());
        }

        return ResponseEntity.notFound().build();
    }
}
