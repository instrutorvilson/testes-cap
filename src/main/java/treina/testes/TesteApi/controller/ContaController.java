package treina.testes.TesteApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import treina.testes.TesteApi.model.Conta;
import treina.testes.TesteApi.service.ContaService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/contas")
public class ContaController {
    @Autowired
    private ContaService service;

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        Conta novaConta = service.salvar(conta);
        return ResponseEntity
                .created(URI.create("/contas/" + novaConta.getId()))
                .body(novaConta);
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        List<Conta> contas = service.listar();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscar(@PathVariable Long id) {
        try {
            Conta conta = service.buscarPorId(id);
            return ResponseEntity.ok(conta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id,
                                           @RequestBody Conta conta) {
        try {
            Conta atualizada = service.atualizar(id, conta);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
