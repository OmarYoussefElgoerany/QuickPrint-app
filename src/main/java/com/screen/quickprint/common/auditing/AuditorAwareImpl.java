//package com.screen.quickprint.common.auditing;
//
//import lombok.NonNull;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component("auditorProvider")
//public class AuditorAwareImpl implements AuditorAware<String> {
//
//    @Override
//    @NonNull
//    public Optional<String> getCurrentAuditor() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth == null || !auth.isAuthenticated()) {
//            return Optional.of("system");
//        }
//
//        Object principal = auth.getPrincipal();
//
//        if (principal instanceof Jwt jwt) {
//            String email = jwt.getClaimAsString("OrganizationEmail");
//            return Optional.ofNullable(email).filter(e -> !e.isBlank()).or(() -> Optional.of("system"));
//        }
//
//        return Optional.of("system");
//    }
//}