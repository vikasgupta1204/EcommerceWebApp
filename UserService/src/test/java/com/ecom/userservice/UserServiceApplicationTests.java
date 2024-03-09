package com.ecom.userservice;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

//    @Autowired
//    private RegisteredClientRepository registeredClientRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Test
//    @Commit
//    public void registeredClientRepository() {
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret(bCryptPasswordEncoder.encode("secret"))
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("ADMIN")
//                .scope("STUDENT")        //Roles
//                .scope("MENTOR")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//       registeredClientRepository.save(oidcClient);
//    }

}
