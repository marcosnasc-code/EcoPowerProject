package br.com.fiap.EcoPower.service;

import br.com.fiap.EcoPower.dto.UsuarioClienteCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioClienteExibicaoDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaExibicaoDTO;
import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioClienteExibicaoDTO gravarCliente(UsuarioClienteCadastroDTO usuarioClienteCadastroDTO){

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(usuarioClienteCadastroDTO.senha());

        UsuarioModel usuarioModel = new UsuarioModel();

        BeanUtils.copyProperties(usuarioClienteCadastroDTO, usuarioModel); //Copia as propriedades do DTO para o Model
        usuarioModel.setSenha(senhaCriptografada);

        return new UsuarioClienteExibicaoDTO(usuarioRepository.save(usuarioModel));
    }

    //    ========================
    public UsuarioEmpresaExibicaoDTO gravarEmpresa(UsuarioEmpresaCadastroDTO usuarioEmpresaCadastroDTO){

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(usuarioEmpresaCadastroDTO.senha());

        UsuarioModel usuarioModel = new UsuarioModel();

        BeanUtils.copyProperties(usuarioEmpresaCadastroDTO, usuarioModel); //Copia as propriedades do DTO para o Model
        usuarioModel.setSenha(senhaCriptografada);

        return new UsuarioEmpresaExibicaoDTO(usuarioRepository.save(usuarioModel));
    }

    //    ========================
    public Page<UsuarioClienteExibicaoDTO> listarTodosClientes(Pageable pageable) {
        Page<UsuarioModel> clientesPage = usuarioRepository.findByRole(PermissionRoles.CLIENTE, pageable);

        return clientesPage.map(this::convertToDto);
    }
    private UsuarioClienteExibicaoDTO convertToDto(UsuarioModel usuario) {
        return new UsuarioClienteExibicaoDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDadosCliente().getCpf()
        );
    }

    //    ========================
    public Page<UsuarioEmpresaExibicaoDTO> listarTodasEmpresas(Pageable pageable) {
        Page<UsuarioModel> clientesPage = usuarioRepository.findByRole(PermissionRoles.CLIENTE, pageable);

        return clientesPage.map(this::convertToDtoEmpresa);
    }
    private UsuarioEmpresaExibicaoDTO convertToDtoEmpresa(UsuarioModel usuario) {
        return new UsuarioEmpresaExibicaoDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDadosEmpresa().getCnpj()
        );
    }

    //    ========================
    public UsuarioClienteExibicaoDTO listarUsuarioEmail(String email){
        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository.findByEmail(email);
        if(optionalUsuarioModel.isPresent()){
            return new UsuarioClienteExibicaoDTO(optionalUsuarioModel.get());
        }else{
            throw new RuntimeException("Usuario não localizado!");
        }
    }

    //    ========================
    public UsuarioModel atualizarUsuario(UsuarioModel usuarioModel){
        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository
                                                                .findByEmail(usuarioModel
                                                                .getEmail());
        if(optionalUsuarioModel.isPresent()){
            return usuarioRepository.save(usuarioModel);
        }else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    //    ========================
    public void excluir(String email){
        Optional<UsuarioModel> usuarioModel = usuarioRepository.findByEmail(email);
        if(usuarioModel.isPresent()){
            usuarioRepository.delete(usuarioModel.get());
        }else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }
}
