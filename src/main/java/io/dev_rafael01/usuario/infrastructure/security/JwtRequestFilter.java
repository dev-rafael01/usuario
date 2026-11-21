package io.dev_rafael01.usuario.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(
            JwtUtil jwtUtil,
            UserDetailsService userDetailsService
    ) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        //Obtém o valor do cabeçalho "Authorization" da  requisição
        final String authorizationHeader = request.getHeader("Authorization");
        //Verificar se o cabeçalho existe e começa com "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            //Extrai o tokem JWT do cabeçalho
            final String token = authorizationHeader.substring(7);
            //Extrai o nome do usuário do tokem JTW
            final String username = jwtUtil.extrairEmailToken(token);

            //se o nome do usuário nao for nulo e o usuário não autenticado ainda
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                //Carregar os detalhes do usuario a partir do nome do usuário
                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(username);
                //valida o token JWT
                if (jwtUtil.validateToken(token, username)) {
                    //Cria um objeto de autenticação com as informaçoes de usuário
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    //define a autenticação no contexto de segurança
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }
        }
        //continua a cadeia de filtros, permitindo que a requisição prossiga
        chain.doFilter(request, response);
    }
}
