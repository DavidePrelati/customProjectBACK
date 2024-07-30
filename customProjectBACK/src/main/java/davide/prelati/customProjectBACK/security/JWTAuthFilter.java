package davide.prelati.customProjectBACK.security;

import davide.prelati.customProjectBACK.entities.User;
import davide.prelati.customProjectBACK.exceptions.UnauthorizedException;
import davide.prelati.customProjectBACK.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTokenConfiguration jwtTokenConfiguration;
    @Autowired
    private UserService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Il token inserito è sbagliato, per favore inserisci quello corretto!");
        String accessToken = authHeader.substring(7);
        jwtTokenConfiguration.verifyToken(accessToken);
        String utenteId = jwtTokenConfiguration.extractIdFromToken(accessToken);
        User current = utenteService.findById(Long.parseLong(utenteId));
        Authentication authentication = new UsernamePasswordAuthenticationToken(current, null, current.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
