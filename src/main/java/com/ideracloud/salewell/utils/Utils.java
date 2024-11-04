package com.ideracloud.salewell.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    public static String getConnectedUserName() {
        try {
            UsernamePasswordAuthenticationToken authorities = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return authorities.getName();
        } catch (Exception e) {
        }
        return null;
    }

    public static boolean hasRole(String role) {
        try {
            UsernamePasswordAuthenticationToken authorities = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            return authorities.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals(role));
        } catch (Exception e) {
        }
        return false;
    }


}
