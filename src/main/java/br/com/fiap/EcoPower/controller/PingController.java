package br.com.fiap.EcoPower.controller;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PingController {

        private final MongoTemplate mongoTemplate;

        public PingController(MongoTemplate mongoTemplate) {
            this.mongoTemplate = mongoTemplate;
        }

        @GetMapping("/ping")
        public ResponseEntity<String> ping() {
            try {
                // Faz uma operação simples pra verificar conexão
                mongoTemplate.getDb().runCommand(new org.bson.Document("ping", 1));
                return ResponseEntity.ok("✅ Conectado ao MongoDB!");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("❌ Erro ao conectar com MongoDB: " + e.getMessage());
            }
        }
    }

