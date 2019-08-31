package br.com.sishovet.spring;


import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.rn.UsuarioRN;
import br.com.sishovet.util.SisHovetUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class AuthenticationHandler implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = SisHovetUtil.encriptarSHA256(a.getCredentials().toString());
        UsuarioRN usuarioRN = new UsuarioRN();
        if (usuarioRN.login(username, password)) {
            Usuario c = usuarioRN.obter(username);
            GrantedAuthorityImpl ga = new GrantedAuthorityImpl("ROLE_"+c.getPerfil());
            List<GrantedAuthority> auts = new ArrayList<GrantedAuthority>();
            auts.add(ga);
            UsernamePasswordAuthenticationToken resposta = new UsernamePasswordAuthenticationToken(username, password, auts);
            return resposta;
        } else {
            throw new BadCredentialsException("Email ou Senha inválidos");

        }
    }

    @Override
    public boolean supports(Class<? extends Object> type) {
        return true;
    }

}
