package pl.pawkrol.academic.IssuesManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final String CLIENT_NAME = "issues-manager-app";
    private final String CLIENT_SECRET = "07f610a2ec62c63caa209972c38103e93556c1d0";

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(CLIENT_NAME)
                .secret(CLIENT_SECRET)
                .scopes("read", "write")
                .authorizedGrantTypes("implicit", "authorization_code", "refresh_token", "password")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .accessTokenValiditySeconds(60 * 60)
                .resourceIds("oauth2-resource");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

}
