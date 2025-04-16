package br.com.fiap.EcoPower.testcontroller;

import br.com.fiap.EcoPower.dto.DadosEmpresaDTO;
import br.com.fiap.EcoPower.dto.EnderecoCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaCadastroDTO;
import br.com.fiap.EcoPower.dto.UsuarioEmpresaExibicaoDTO;
import br.com.fiap.EcoPower.model.BrazilRegions;
import br.com.fiap.EcoPower.model.PermissionRoles;
import br.com.fiap.EcoPower.model.UsuarioModel;
import br.com.fiap.EcoPower.repository.UsuarioRepository;
import br.com.fiap.EcoPower.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarEmpresaComSucesso() {
        // Arrange
        EnderecoCadastroDTO enderecoDTO = new EnderecoCadastroDTO("12345-678", BrazilRegions.SUDESTE);
        DadosEmpresaDTO dadosEmpresaDTO = new DadosEmpresaDTO("12.345.678/0001-90");

        UsuarioEmpresaCadastroDTO empresaDTO = new UsuarioEmpresaCadastroDTO(
                null,
                "Empresa Teste",
                "empresa@email.com",
                "senha123",
                enderecoDTO,
                dadosEmpresaDTO
        );

        UsuarioModel empresaSalva = new UsuarioModel();
        empresaSalva.setNome("Empresa Teste");
        empresaSalva.setEmail("empresa@email.com");
        empresaSalva.setSenha(new BCryptPasswordEncoder().encode("senha123"));
        empresaSalva.setRole(PermissionRoles.EMPRESA);

        UsuarioModel.Endereco endereco = new UsuarioModel.Endereco();
        endereco.setCep("12345-678");
        endereco.setRegiao(BrazilRegions.SUDESTE);
        empresaSalva.setEndereco(endereco);

        UsuarioModel.DadosEmpresa dadosEmpresa = new UsuarioModel.DadosEmpresa();
        dadosEmpresa.setCnpj("12.345.678/0001-90");
        empresaSalva.setDadosEmpresa(dadosEmpresa);

        when(usuarioRepository.save(any(UsuarioModel.class))).thenReturn(empresaSalva);

        // Act
        UsuarioEmpresaExibicaoDTO resultado = usuarioService.gravarEmpresa(empresaDTO);

        // Assert
        ArgumentCaptor<UsuarioModel> captor = ArgumentCaptor.forClass(UsuarioModel.class);
        verify(usuarioRepository, times(1)).save(captor.capture());

        UsuarioModel capturado = captor.getValue();

        assertThat(capturado.getNome()).isEqualTo("Empresa Teste");
        assertThat(capturado.getEmail()).isEqualTo("empresa@email.com");
        assertThat(capturado.getRole()).isEqualTo(PermissionRoles.EMPRESA);
        assertThat(capturado.getEndereco().getCep()).isEqualTo("12345-678");
        assertThat(capturado.getDadosEmpresa().getCnpj()).isEqualTo("12.345.678/0001-90");

        assertThat(resultado.nome()).isEqualTo("Empresa Teste");
        assertThat(resultado.email()).isEqualTo("empresa@email.com");
        assertThat(resultado.cnpj()).isEqualTo("12.345.678/0001-90");
    }
}
