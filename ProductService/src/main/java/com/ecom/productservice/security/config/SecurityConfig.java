//package com.ecom.productservice.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
//            throws Exception {
////        http
//////                .authorizeHttpRequests((authorize) -> authorize
//////                        .requestMatchers("/products/{id}").
//////                        hasAnyAuthority("CUSTOMER")
//////                        .requestMatchers("/products")
//////                        .hasAnyAuthority("ADMIN")
//////                        .anyRequest().authenticated()
//////                );
//        http.authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());
//        // Form login handles the redirect to the login page from the
//        // authorization server fi  lter chain
//
//
//        return http.build();
//    }
//
////    @Bean
////    public JwtAuthenticationConverter jwtAuthenticationConverter() {
////        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter =
////                new JwtGrantedAuthoritiesConverter();
////        grantedAuthoritiesConverter.setAuthoritiesClaimName("ScalerRoles");
////    JwtAuthenticationConverter jwtAuthenticationConverter=new JwtAuthenticationConverter();
////    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
////    return jwtAuthenticationConverter;
////}
//}
