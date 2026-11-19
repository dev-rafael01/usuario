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
                .enderecos(paraListaEndereco(usuarioDto.getEndereco()))
                .build();
    }

    public List<Endereco> paraListaEndereco(List<EnderecoDTO> enderecoDto) {
        return enderecoDto.stream().map(this::paraEndereco).toList();
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
        List<Telefone> telefones = new ArrayList<>();
        for(TelefoneDTO telefoneDTO : telefoneDto) {
            telefones.add(paraTelefone(telefoneDTO));
        }
        return telefones;
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO){
            return Telefone.builder()

                    .numero(telefoneDTO.getNumero())
                    .ddd(telefoneDTO.getDdd())
                    .build();
    }
}
