package com.marlonmachado.dscommerce.services;

import com.marlonmachado.dscommerce.dto.UserDTO;
import com.marlonmachado.dscommerce.entities.Role;
import com.marlonmachado.dscommerce.entities.User;
import com.marlonmachado.dscommerce.projections.UserDetailsProjection;
import com.marlonmachado.dscommerce.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       List<UserDetailsProjection> result = userRepository.searchUserAndRoleByEmail(username);
       if(result.isEmpty()){
           throw new UsernameNotFoundException("User not found");
       }

       User user = new User();
       user.setEmail(result.get(0).getUserName());
       user.setPassword(result.get(0).getPassword());
       for(UserDetailsProjection projection : result){
           user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
       }

       return user;
    }

    protected User authenticate() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
            String username = jwtPrincipal.getClaim("username");

           return  userRepository.findByEmail(username).get();
        }catch(Exception e){
            throw new UsernameNotFoundException("Email not found");
        }

    }

    @Transactional(readOnly = true)
    public UserDTO getMe() {
        User user = authenticate();
        return new UserDTO(user);
    }

}
