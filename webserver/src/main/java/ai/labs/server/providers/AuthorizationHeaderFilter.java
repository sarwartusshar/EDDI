package ai.labs.server.providers;

import ai.labs.runtime.ThreadContext;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

/**
 * @author ginccc
 */

@Provider
public class AuthorizationHeaderFilter implements ContainerRequestFilter {
    @Inject
    @Context
    SecurityContext securityContext;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        KeycloakPrincipal<KeycloakSecurityContext> userPrincipal =
                (KeycloakPrincipal<KeycloakSecurityContext>) this.securityContext.getUserPrincipal();
        KeycloakSecurityContext securityContext = userPrincipal.getKeycloakSecurityContext();
        ThreadContext.put("security.token", securityContext.getTokenString());
    }

}
