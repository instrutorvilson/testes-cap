package treina.testes.TesteApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import treina.testes.TesteApi.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/usuarios")
public class UsuarioController {

    private Map<Long, Usuario> banco = new HashMap<>();
    private AtomicLong sequence = new AtomicLong();

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Long id = sequence.incrementAndGet();
        usuario.setId(id);
        banco.put(id, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        Usuario usuario = banco.get(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return new ArrayList<>(banco.values());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        banco.remove(id);
        return ResponseEntity.noContent().build();
    }
}
