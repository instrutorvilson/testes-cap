package com.testes.Testeui.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testes.Testeui.modelos.Usuario;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioController() {
        usuarios.add(new Usuario(1L, "Ana", "ana@email.com"));
        usuarios.add(new Usuario(2L, "Carlos", "carlos@email.com"));
    }

    @GetMapping
    public List<Usuario> listar() throws InterruptedException {
        Thread.sleep(2000); // simula delay (IMPORTANTE p/ Selenium)
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public String criar(@RequestBody Usuario usuario) {
    	usuarios.add(usuario);
        return "Usuário criado com sucesso!";
    }
}
