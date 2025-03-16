package dev.aomegax.echoboard.be.config.security;

import dev.aomegax.echoboard.be.exception.AppError;
import dev.aomegax.echoboard.be.exception.AppException;
import dev.aomegax.echoboard.be.model.OAuth2ProviderType;
import dev.aomegax.echoboard.be.repository.EndUserRepository;
import dev.aomegax.echoboard.be.service.GitHubEmailFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuthEndUserService extends DefaultOAuth2UserService {

    private static final String NAME_ATTRIBUTE = "login";
    private static final String EMAIL_KEY = "email";

    private final EndUserRepository endUserRepository;

    private final GitHubEmailFetcher githubEmailFetcher;

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();


    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2ProviderType provider = OAuth2ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2User oauthUser = getOAuth2User(userRequest, provider);

        CustomEndUserUtil.checkEndUser(endUserRepository, provider, oauthUser.getAttributes());

        return oauthUser;
    }

    private OAuth2User getOAuth2User(OAuth2UserRequest userRequest, OAuth2ProviderType provider) {
        OAuth2User oauth2User = super.loadUser(userRequest);

        switch (provider) {
            case GITHUB:
                String primaryEmailAddress = extractPrimaryEmailAddress(
                        oauth2User,
                        userRequest.getAccessToken().getTokenValue());

                // Clone the original attributes into a mutable map
                Map<String, Object> updatedAttributes = new HashMap<>(oauth2User.getAttributes());

                // Add the fetched email to the attributes map
                updatedAttributes.put(EMAIL_KEY, primaryEmailAddress);

                // Return a new DefaultOAuth2User with the updated attributes
                return new DefaultOAuth2User(
                        oauth2User.getAuthorities(), // or Collections.emptyList()
                        updatedAttributes,
                        NAME_ATTRIBUTE);
            case GOOGLE:
                return oauth2User;
            default:
                throw new AppException(AppError.PROVIDER_UNKNOWN, null);
        }

    }

    private String extractPrimaryEmailAddress(
            OAuth2User oauth2User,
            String token) {
        String primaryEmailAddress = oauth2User.getAttribute(EMAIL_KEY);

        if (!(primaryEmailAddress == null || primaryEmailAddress.isBlank())) {
            return primaryEmailAddress;
        }

        return githubEmailFetcher.fetchPrimaryEmailAddress(token);
    }
}
