package com.marlonmachado.dscommerce.services;

import com.marlonmachado.dscommerce.entities.Role;
import com.marlonmachado.dscommerce.entities.User;
import com.marlonmachado.dscommerce.projections.UserDetailsProjection;
import com.marlonmachado.dscommerce.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

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
       user.setEmail(username);
       user.setPassword(result.get(0).getPassword());

       for(UserDetailsProjection projection : result){
           user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
       }

       return user;
    }
}
