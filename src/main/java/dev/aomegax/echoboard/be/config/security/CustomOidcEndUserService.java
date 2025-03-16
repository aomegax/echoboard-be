package dev.aomegax.echoboard.be.config.security;

import dev.aomegax.echoboard.be.model.OAuth2ProviderType;
import dev.aomegax.echoboard.be.repository.EndUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomOidcEndUserService extends OidcUserService {

    private final EndUserRepository endUserRepository;

    @Override
    @Transactional
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OAuth2ProviderType provider = OAuth2ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OidcUser oidcUser = super.loadUser(userRequest);

        CustomEndUserUtil.checkEndUser(endUserRepository, provider, oidcUser.getAttributes());

        return oidcUser;
    }
}
