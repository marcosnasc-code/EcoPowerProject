package br.com.fiap.EcoPower.repository;

import br.com.fiap.EcoPower.model.UsuarioModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {
    Optional<UsuarioModel> findByEmail(String email);
}
