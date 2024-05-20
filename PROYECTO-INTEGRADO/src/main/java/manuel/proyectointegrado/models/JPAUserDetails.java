package manuel.proyectointegrado.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//ES UN MODELO QUE SE USA PARA EDITAR LAS COSAS DEL LOGIN (de security)
public class JPAUserDetails implements UserDetails {

    private final String usuario;
    private final String password;
//  private final boolean activo;
    private final List<GrantedAuthority> authorities;

    public JPAUserDetails(Usuario usuario) {

        this.usuario = usuario.getUsername();
        this.password = usuario.getContrasena();
    //  this.activo = usuario.isActivo();
        this.authorities = new ArrayList<>();
        this.authorities.add(new SimpleGrantedAuthority((String) usuario.getTipo_usuario()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
