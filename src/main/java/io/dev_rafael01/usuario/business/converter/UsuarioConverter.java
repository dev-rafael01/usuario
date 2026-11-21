package io.dev_rafael01.usuario.business.converter;

import io.dev_rafael01.usuario.business.dto.EnderecoDTO;
import io.dev_rafael01.usuario.business.dto.TelefoneDTO;
import io.dev_rafael01.usuario.business.dto.UsuarioDTO;
import io.dev_rafael01.usuario.infrastructure.entity.Endereco;
import io.dev_rafael01.usuario.infrastructure.entity.Telefone;
import io.dev_rafael01.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioConverter {

    public Usuario paraUsuario(UsuarioDTO usuarioDto) {
        return Usuario.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .enderecos(paraListaEndereco(usuarioDto.getEnderecos()))
                .telefones(paraListaTelefone(usuarioDto.getTelefones()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDto) {
        List<Endereco> enderecos = new ArrayList<>();
        for(EnderecoDTO enderecoDTO : enderecoDto){
            enderecos.add(paraEndereco(enderecoDTO));
        }

        return enderecos;
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDto) {
        return Endereco.builder()
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .cidade(enderecoDto.getCidade())
                .complemento(enderecoDto.getComplemento())
                .cep(enderecoDto.getCep())
                .estado(enderecoDto.getEstado())
                .build();
    }

    public List<Telefone> paraListaTelefone(List<TelefoneDTO> telefoneDto) {
        return telefoneDto.stream().map(this::paraTelefone).toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
            return Telefone.builder()

                    .numero(telefoneDTO.getNumero())
                    .ddd(telefoneDTO.getDdd())
                    .build();
    }


    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDto) {
        return UsuarioDTO.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDto.getEnderecos()))
                .telefones(paraListaTelefoneDTO(usuarioDto.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDto) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        for(Endereco enderecoDTO : enderecoDto){
            enderecos.add(paraEnderecoDTO(enderecoDTO));
        }

        return enderecos;
    }

    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDto) {
        return EnderecoDTO.builder()
                .rua(enderecoDto.getRua())
                .numero(enderecoDto.getNumero())
                .cidade(enderecoDto.getCidade())
                .complemento(enderecoDto.getComplemento())
                .cep(enderecoDto.getCep())
                .estado(enderecoDto.getEstado())
                .build();
    }

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDto) {
        return telefoneDto.stream().map(this::paraTelefoneDTO).toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
}
