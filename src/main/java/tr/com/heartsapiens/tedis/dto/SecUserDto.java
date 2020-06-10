/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.heartsapiens.tedis.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 *
 */
@Data
public class SecUserDto  implements IBaseDto, UserDetails {

    private Long id;

     private String username;
    
     private String password;
    
     private String name;
    
    private String surname;
    
    private String email;

    private List<SecProfileDto> profileList;

    private KurumKurulusDto kurumKurulus;

    private Boolean durum;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getProfileList().stream()
                .filter(authority -> authority.getDurum())
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.durum;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.durum;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.durum;
    }

    @Override
    public boolean isEnabled() {
        return this.durum;
    }
}
