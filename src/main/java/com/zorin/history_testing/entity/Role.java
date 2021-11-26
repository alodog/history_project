/**
 * @author Zorin Sergey
 */

package com.zorin.history_testing.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    MAINADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
