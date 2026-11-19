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
public class UsuarioReverte {

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDto) {
        return UsuarioDTO.builder()
                .nome(usuarioDto.getNome())
                .email(usuarioDto.getEmail())
                .senha(usuarioDto.getSenha())
                .endereco(paraListaEnderecoDTO(usuarioDto.getEnderecos()))
                .telefone(paraListaTelefoneDTO(usuarioDto.getTelefones()))
                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDto) {
        List<EnderecoDTO> enderecos = new ArrayList<>();
        for (Endereco endereco : enderecoDto) {
            enderecos.add(paraEnderecoDTO(endereco));
        }
        return  enderecos;
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

    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDtoS) {
        return telefoneDtoS.stream().map(this::paraTelefoneDTO).toList();


    }

   public TelefoneDTO paraTelefoneDTO(Telefone telefoneDto) {
        return TelefoneDTO.builder()
                .numero(telefoneDto.getNumero())
                .ddd(telefoneDto.getDdd())
                .build();
   }

}
