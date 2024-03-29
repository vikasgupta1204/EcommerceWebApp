package com.ecom.userservice.projections;

import java.util.List;

/**
 * Projection for {@link com.ecom.userservice.model.User}
 */
public interface UserProjection {
    Long getId();

    String getName();

    String getPassword();

    String getEmail();

    boolean isIsEmailVerified();

    List<RoleProjection> getRoles();
}