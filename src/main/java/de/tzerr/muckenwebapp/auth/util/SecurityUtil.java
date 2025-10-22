package de.tzerr.muckenwebapp.auth.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static String getUsernameOfAuthenticatedUser() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }
}
