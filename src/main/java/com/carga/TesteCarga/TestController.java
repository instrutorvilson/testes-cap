package com.carga.TesteCarga;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

    @RestController
    @RequestMapping("/api")
    public class TestController {

        // 🔹 rápido
        @GetMapping("/rapido")
        public String rapido() {
            return "OK - rápido";
        }

        // 🔹 lento (simula latência)
        @GetMapping("/lento")
        public String lento() throws InterruptedException {
            Thread.sleep(2000);
            return "OK - lento";
        }

        // 🔹 carga de CPU
        @GetMapping("/cpu")
        public String cpu() {
            long soma = 0;
            for (long i = 0; i < 500_000_000; i++) {
                soma += i;
            }
            return "CPU processado: " + soma;
        }

        // 🔹 simula banco
        @GetMapping("/dados")
        public List<String> dados() throws InterruptedException {
            Thread.sleep(500);

            List<String> lista = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                lista.add("Produto " + i);
            }

            return lista;
        }

        // 🔹 erro aleatório
        @GetMapping("/erro")
        public ResponseEntity<String> erro() {
            if (Math.random() < 0.3) {
                return ResponseEntity.status(500).body("Erro simulado");
            }
            return ResponseEntity.ok("OK");
        }

        // 🔹 endpoint POST (login fake)
        @PostMapping("/login")
        public ResponseEntity<String> login(@RequestBody LoginRequest request) throws InterruptedException {

            Thread.sleep(300); // simula processamento

            if ("admin".equals(request.getUsuario()) &&
                    "123".equals(request.getSenha())) {
                return ResponseEntity.ok("Login OK");
            }

            return ResponseEntity.status(401).body("Login inválido");
        }

        static class LoginRequest {
            private String usuario;
            private String senha;

            public String getUsuario() { return usuario; }
            public void setUsuario(String usuario) { this.usuario = usuario; }

            public String getSenha() { return senha; }
            public void setSenha(String senha) { this.senha = senha; }
        }
    }


