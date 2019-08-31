package br.com.sishovet.rn;

import br.com.sishovet.dao.UsuarioDAO;
import br.com.sishovet.entidade.Usuario;
import br.com.sishovet.entidade.tipo.TipoPerfilUsuario;
import br.com.sishovet.util.SisHovetUtil;
import br.com.sishovet.util.UtilBean;
import java.util.List;

/**
 *
 * @author GreenHand
 */
public class UsuarioRN {

    private final UsuarioDAO USUARIO_DAO = new UsuarioDAO();

    public Usuario obter(String emailOuApelido) {
        return USUARIO_DAO.obter(emailOuApelido);
    }

    public List<Usuario> obterUsuarios() {
        return USUARIO_DAO.obterTodos(Usuario.class);
    }

    public boolean login(String email, String senha) {
        Usuario usuario = USUARIO_DAO.autenticar(email, senha);
        if (usuario != null) {
            return usuario.getHabilitado();
        } else {
            return Boolean.FALSE;
        }
    }

    public boolean salvar(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        if (usuario.getId() == null || usuario.getId() == 0) {
            if (UtilBean.obterContaLogada().getPerfil() == TipoPerfilUsuario.ADMINISTRADOR.getValor()) {
                usuario.setHabilitado(Boolean.TRUE);
            }
            if (okUsuario(usuario)) {
                if (!existeEmail(usuario.getEmail())) {
                    return USUARIO_DAO.criar(usuario);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            if (okUsuario(usuario)) {
                return USUARIO_DAO.alterar(usuario);
            } else {
                return false;
            }
        }
    }

    /**
     * Verifica se as informações do usuario estão ok para persistencia
     *
     * @param usuario
     * @return
     */
    public boolean okUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        } else {
            if (usuario.getPerfil() == TipoPerfilUsuario.MEDICO.getValor() && (usuario.getCrmvpa() == null || usuario.getCrmvpa().isEmpty())) {
                return false;
            } else {
                return SisHovetUtil.validarEmail(usuario.getEmail());
            }
        }
    }

    public boolean existeEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        } else {
            return USUARIO_DAO.emailCadastrado(email);
        }
    }

    public boolean alterarPerfil(Usuario usuario) {
        if (usuario.getId() == null || usuario.getId() == 0) {
            return Boolean.FALSE;
        } else if (UtilBean.obterContaLogada().getPerfil() == TipoPerfilUsuario.ADMINISTRADOR.getValor()) {
            return salvar(usuario);
        } else {
            return Boolean.FALSE;
        }
    }

    public Usuario obter(Integer id) {
        if (id == null) {
            return null;
        } else {
            return USUARIO_DAO.obter(Usuario.class, id);
        }
    }

    public boolean alterarSenha(Usuario usuario, String novaSenha) {
        if (usuario == null || novaSenha == null) {
            return false;
        } else {
            usuario.setSenha(SisHovetUtil.encriptarSHA256(novaSenha));
            return salvar(usuario);
        }
    }
}
