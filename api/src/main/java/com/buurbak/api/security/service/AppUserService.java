package com.buurbak.api.security.service;

import com.buurbak.api.security.exception.AppUserNotFoundException;
import com.buurbak.api.security.model.AppUser;
import com.buurbak.api.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    public boolean isEmailTaken(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    public AppUser findByUsername(String email) throws AppUserNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(() -> new AppUserNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        if (!appUser.isEnabled()) throw new UsernameNotFoundException("Invalid username or password");

        return new User(appUser.getUsername(), appUser.getPassword(), getAuthorities(appUser));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(AppUser appUser) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}
