package br.com.ordensservico.aberturaordensservico.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import br.com.ordensservico.aberturaordensservico.model.Equipamento;
import br.com.ordensservico.aberturaordensservico.repository.EquipamentoRepository;


@Service
public class EquipamentoService {
    
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public Equipamento cadastrar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Optional<Equipamento> buscarPorId(Integer id) {
        return equipamentoRepository.findById(id);
    }

    public Optional<Equipamento> atualizar(
            Integer id, Equipamento novosDados) {

        Optional<Equipamento> equipamentoEncontrado = equipamentoRepository.findById(id);

        if (equipamentoEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Equipamento equipamento = equipamentoEncontrado.get();

        equipamento.setNome(novosDados.getNome());

        return Optional.of(equipamentoRepository.save(equipamento));
    }

    public boolean excluir(Integer id) {

        if (!equipamentoRepository.existsById(id)) {
            return false;
        }

        equipamentoRepository.deleteById(id);
        return true;
    }
}
