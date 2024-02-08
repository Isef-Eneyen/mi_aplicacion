package com.example.deleonperezisefeneyenmyikea.Services;

import com.example.deleonperezisefeneyenmyikea.Models.Role;
import com.example.deleonperezisefeneyenmyikea.Models.User;
import com.example.deleonperezisefeneyenmyikea.Repositories.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;


@Service
public class UserService implements UserDetailsService
{
    private UserRepository userRepository;

    private RoleReposictory roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserService(UserRepository userRepository, RoleReposictory roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(String name, String email, String password)
    {
        //Encripta la contraseña
        String encodedPass = passwordEncoder.encode(password);

        //Recupera el role de la base de datos
        Role defaultRole = roleRepository.findByRole("ROLE_USER");

        if(defaultRole == null)
        {
            throw new RuntimeException("El rol por defecto no está en la base de datos");
        }

        // Crea un nuevo usuario
        User user = new User();
        user.setUserName(name);
        user.setEmail(email);
        user.setPassword(encodedPass);

        // Añade el rol al usuario
        Collection<Role> userRoles = new HashSet<>();
        userRoles.add(defaultRole);
        user.setRoles(userRoles);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);

        if(user == null)
        {
            throw new UsernameNotFoundException("Ha ocurrido un error con el email");
        }

        return buildUserDetails(user);
    }

    private UserDetails buildUserDetails(User user)
    {
        // Obtener las autoridades (roles) del usuario
        Collection<GrantedAuthority> authorities = getAuthorities(user.getRoles());

        // Construir y retornar un objeto UserDetails
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    private Collection<GrantedAuthority> getAuthorities(Collection<Role> roles) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles)
        {
            // Agregar cada rol precedido por "ROLE_" como una autoridad
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    public Collection<User> getUsers(){return userRepository.findAll();}

    public Role SaveRole(Role role){return roleRepository.save(role);}
    public Role getRol(String role){return roleRepository.findByRole(role);}

    public User saveUserSeeder(User user)
    {
        String encodedPass = passwordEncoder.encode(user.getPassword());

        // Configura la contraseña encriptada en el usuario
        user.setPassword(encodedPass);

        return userRepository.save(user);
    }

    public User GetUserEmail(String email){return userRepository.findByEmail(email);}

    public User GetUserId(long id){return userRepository.findById(id).orElse(null);}
    public void DeleteUser(User user){userRepository.delete(user);}
    public void SaveUserChange(User user){userRepository.save(user);}

}
