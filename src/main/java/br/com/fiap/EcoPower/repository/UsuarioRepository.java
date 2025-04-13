package br.com.fiap.EcoPower.repository;

import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioModel, String> {
    Optional<UsuarioModel> findByEmail(String email);

    Page<UsuarioModel> findByRole(PermissionRoles role, Pageable pageable);
}
