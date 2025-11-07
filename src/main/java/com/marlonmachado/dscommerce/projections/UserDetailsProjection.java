package com.marlonmachado.dscommerce.projections;

public interface UserDetailsProjection {

    String getUserName();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
