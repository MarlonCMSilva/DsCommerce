package com.marlonmachado.dscommerce.services;

import com.marlonmachado.dscommerce.entities.User;
import com.marlonmachado.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    public void ValidateSelfOrAdmin(long userId){
        User me = userService.authenticate();
        if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw new ForbiddenException("Acess denied");
        }
    }
}
