package io.dev_rafael01.usuario.infrastructure.security;


import io.dev_rafael01.usuario.infrastructure.entity.Usuario;
import io.dev_rafael01.usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado: " + email));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .authorities("ROLE_USER")
                .build();
    }
}
