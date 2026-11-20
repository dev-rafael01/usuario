package io.dev_rafael01.usuario.business;


import io.dev_rafael01.usuario.business.converter.UsuarioConverter;
import io.dev_rafael01.usuario.business.dto.UsuarioDTO;
import io.dev_rafael01.usuario.infrastructure.entity.Usuario;
import io.dev_rafael01.usuario.infrastructure.exceptions.ConflictException;
import io.dev_rafael01.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder PasswordEncoder;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDto){
        emailExiste(usuarioDto.getEmail());
        usuarioDto.setSenha(PasswordEncoder.encode(usuarioDto.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDto);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));

    }
    public void emailExiste(String email){
        try {
            boolean existe = verificarEmailExistente(email);
            if(existe){
                throw new ConflictException("Email ja cadastrado " + email);
            }
        }catch (ConflictException e ){
            throw new ConflictException("Email ja cadastrado", e.getCause());
        }
    }

    public boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario salvarUsuarioPorEmail(@RequestParam String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email ja cadastrado " + email));
    }

    public void deleteUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

}
