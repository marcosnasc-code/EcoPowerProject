package br.com.fiap.EcoPower.service;

import br.com.fiap.EcoPower.dto.*;
import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;



    public UsuarioClienteExibicaoDTO gravarCliente(UsuarioClienteCadastroDTO usuarioClienteCadastroDTO){

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(usuarioClienteCadastroDTO.senha());

        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setNome(usuarioClienteCadastroDTO.nome());
        usuarioModel.setEmail(usuarioClienteCadastroDTO.email());
        usuarioModel.setSenha(senhaCriptografada);
        usuarioModel.setRole(PermissionRoles.CLIENTE);

        //Dados Endereço
        UsuarioModel.Endereco clienteEndereco = new UsuarioModel.Endereco();
        clienteEndereco.setCep(usuarioClienteCadastroDTO.endereco().cep());
        clienteEndereco.setRegiao(usuarioClienteCadastroDTO.endereco().regiao());
        usuarioModel.setEndereco(clienteEndereco);

        //Dados Cliente
        UsuarioModel.DadosCliente dadosCliente = new UsuarioModel.DadosCliente();
        dadosCliente.setCpf(usuarioClienteCadastroDTO.dadosCliente().cpf());
        List<UsuarioModel.Imovel> imoveis = usuarioClienteCadastroDTO.dadosCliente().imoveis().stream().map(imovelCadastroDTO -> {
            UsuarioModel.Imovel imovel = new UsuarioModel.Imovel();
            imovel.setImoveis(imovelCadastroDTO.tipoImoveis());

            UsuarioModel.Endereco enderecoImovel = new UsuarioModel.Endereco();
            enderecoImovel.setCep(imovelCadastroDTO.endereco().cep());
            enderecoImovel.setRegiao(imovelCadastroDTO.endereco().regiao());
            imovel.setEndereco(enderecoImovel);

            return imovel;
        }).toList();

        dadosCliente.setImoveis(imoveis);

        usuarioModel.setDadosCliente(dadosCliente);

        return new UsuarioClienteExibicaoDTO(usuarioRepository.save(usuarioModel));
    }

    //    ========================
    //    ========================
    //    ========================
    public UsuarioEmpresaExibicaoDTO gravarEmpresa(UsuarioEmpresaCadastroDTO usuarioEmpresaCadastroDTO){

        String senhaCriptografada = new BCryptPasswordEncoder()
                .encode(usuarioEmpresaCadastroDTO.senha());

        UsuarioModel usuarioModel = new UsuarioModel();

        usuarioModel.setNome(usuarioEmpresaCadastroDTO.nome());
        usuarioModel.setEmail(usuarioEmpresaCadastroDTO.email());
        usuarioModel.setSenha(senhaCriptografada);
        usuarioModel.setRole(PermissionRoles.EMPRESA);

        //Dados Endereço
        UsuarioModel.Endereco empresaEndereco = new UsuarioModel.Endereco();
        empresaEndereco.setCep(usuarioEmpresaCadastroDTO.endereco().cep());
        empresaEndereco.setRegiao(usuarioEmpresaCadastroDTO.endereco().regiao());
        usuarioModel.setEndereco(empresaEndereco);

        //Dados Empresa
        UsuarioModel.DadosEmpresa dadosEmpresa = new UsuarioModel.DadosEmpresa();
        dadosEmpresa.setCnpj(usuarioEmpresaCadastroDTO.dadosEmpresa().cnpj());
        usuarioModel.setDadosEmpresa(dadosEmpresa);

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
                usuario.getDadosCliente().getCpf(),
                usuario.getEndereco(),
                usuario.getDadosCliente()
        );
    }

    //    ========================
    public Page<UsuarioEmpresaExibicaoDTO> listarTodasEmpresas(Pageable pageable) {
        Page<UsuarioModel> clientesPage = usuarioRepository.findByRole(PermissionRoles.EMPRESA, pageable);

        return clientesPage.map(this::convertToDtoEmpresa);
    }
    private UsuarioEmpresaExibicaoDTO convertToDtoEmpresa(UsuarioModel usuario) {
        return new UsuarioEmpresaExibicaoDTO(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getEndereco(),
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
//    public UsuarioModel atualizarUsuario(UsuarioModel usuarioModel){
//        Optional<UsuarioModel> optionalUsuarioModel = usuarioRepository.findByEmail(usuarioModel.getEmail());
//        if(optionalUsuarioModel.isPresent()){
//            return usuarioRepository.save(usuarioModel);
//        }else {
//            throw new RuntimeException("Usuario não encontrado");
//        }
//    }

    public UsuarioModel atualizarCliente(UsuarioModel usuarioModel) {
        return usuarioRepository.findByEmail(usuarioModel.getEmail())
                .map(usuarioExistente -> {
                    // Atualiza apenas os campos necessários
                    if (usuarioModel.getNome() != null) {
                        usuarioExistente.setNome(usuarioModel.getNome());
                    }

                    if (usuarioModel.getSenha() != null) {
                        String senhaCriptografada = new BCryptPasswordEncoder()
                                .encode(usuarioModel.getSenha());
                        usuarioExistente.setSenha(senhaCriptografada);
                    }

                    // Atualização do endereço
                    if (usuarioModel.getEndereco() != null) {
                        if (usuarioExistente.getEndereco() == null) {
                            usuarioExistente.setEndereco(new UsuarioModel.Endereco());
                        }
                        if (usuarioModel.getEndereco().getCep() != null) {
                            usuarioExistente.getEndereco().setCep(usuarioModel.getEndereco().getCep());
                        }
                        if (usuarioModel.getEndereco().getRegiao() != null) {
                            usuarioExistente.getEndereco().setRegiao(usuarioModel.getEndereco().getRegiao());
                        }
                    }

                    // Atualização dos dados do cliente
                    if (usuarioModel.getDadosCliente() != null) {
                        if (usuarioExistente.getDadosCliente() == null) {
                            usuarioExistente.setDadosCliente(new UsuarioModel.DadosCliente());
                        }
                        if (usuarioModel.getDadosCliente().getCpf() != null) {
                            usuarioExistente.getDadosCliente().setCpf(usuarioModel.getDadosCliente().getCpf());
                        }
                    }

                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    //    ========================

    public UsuarioModel atualizarEmpresa(UsuarioModel usuarioModel) {
        return usuarioRepository.findByEmail(usuarioModel.getEmail())
                .map(usuarioExistente -> {
                    // Atualiza apenas os campos necessários
                    if (usuarioModel.getNome() != null) {
                        usuarioExistente.setNome(usuarioModel.getNome());
                    }

                    if (usuarioModel.getSenha() != null) {
                        String senhaCriptografada = new BCryptPasswordEncoder()
                                .encode(usuarioModel.getSenha());
                        usuarioExistente.setSenha(senhaCriptografada);
                    }

                    // Atualização do endereço
                    if (usuarioModel.getEndereco() != null) {
                        if (usuarioExistente.getEndereco() == null) {
                            usuarioExistente.setEndereco(new UsuarioModel.Endereco());
                        }
                        if (usuarioModel.getEndereco().getCep() != null) {
                            usuarioExistente.getEndereco().setCep(usuarioModel.getEndereco().getCep());
                        }
                        if (usuarioModel.getEndereco().getRegiao() != null) {
                            usuarioExistente.getEndereco().setRegiao(usuarioModel.getEndereco().getRegiao());
                        }
                    }

                    // Atualização dos dados do cliente
                    if (usuarioModel.getDadosEmpresa() != null) {
                        if (usuarioExistente.getDadosEmpresa() == null) {
                            usuarioExistente.setDadosEmpresa(new UsuarioModel.DadosEmpresa());
                        }
                        if (usuarioModel.getDadosEmpresa().getCnpj() != null) {
                            usuarioExistente.getDadosEmpresa().setCnpj(usuarioModel.getDadosEmpresa().getCnpj());
                        }
                    }

                    return usuarioRepository.save(usuarioExistente);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
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
