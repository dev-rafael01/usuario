package io.dev_rafael01.usuario.business;

import io.dev_rafael01.usuario.business.converter.UsuarioConverter;
import io.dev_rafael01.usuario.business.converter.UsuarioReverte;
import io.dev_rafael01.usuario.business.dto.UsuarioDTO;
import io.dev_rafael01.usuario.infrastructure.entity.Usuario;
import io.dev_rafael01.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final UsuarioReverte usuarioReverte;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDto){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDto);
        usuario = usuarioRepository.save(usuario);
        return usuarioReverte.paraUsuarioDTO(usuario);

    }
}
