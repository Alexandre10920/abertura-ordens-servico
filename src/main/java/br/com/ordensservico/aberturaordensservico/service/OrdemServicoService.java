package br.com.ordensservico.aberturaordensservico.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.model.OrdemServico;

import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;
import br.com.ordensservico.aberturaordensservico.repository.OrdemServicoRepository;
import br.com.ordensservico.aberturaordensservico.dto.OrdemServicoRequest;

@Service 
public class OrdemServicoService {
    
    private final EquipamentoRepository equipamentoRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(EquipamentoRepository equipamentoRepository, OrdemServicoRepository ordemServicoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public OrdemServico salvar(OrdemServico ordem) {

        ordem.setDataAbertura(LocalDateTime.now());

        return ordemServicoRepository.save(ordem);
    }

    public Optional<OrdemServico> cadastrar(
        OrdemServicoRequest dadosOrdemServico) {
        
        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(dadosOrdemServico.getEquipamentoId());
        
        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        OrdemServico ordemServico = new OrdemServico();
        
        ordemServico.setDescricao(dadosOrdemServico.getDescricao());
        ordemServico.setDataAbertura(java.time.LocalDateTime.now());
        ordemServico.setEquipamento(equipamentoEncontrado.get());

        return Optional.of(ordemServicoRepository.save(ordemServico));
    }
    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Integer id) {
        return ordemServicoRepository.findById(id);
    }
}
