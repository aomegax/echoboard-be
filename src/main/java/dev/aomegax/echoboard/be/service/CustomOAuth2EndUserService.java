package dev.aomegax.echoboard.be.service;

import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.repository.EndUserRepository;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2EndUserService extends DefaultOAuth2UserService {

    private static final String NAME_ATTRIBUTE = "login";
    private static final String EMAIL_KEY = "email";

    private final EndUserRepository endUserRepository;

    private final GitHubEmailFetcher githubEmailFetcher;

    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();


    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase(); // "GOOGLE" o "GITHUB"
        OAuth2User oauthUser = getOAuth2User(userRequest);
        Map<String, Object> attributes = oauthUser.getAttributes();

        String email;
        String name;

        if ("GOOGLE".equals(provider)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
        } else if ("GITHUB".equals(provider)) {
            email = (String) attributes.get("email");
            name = (String) attributes.get("name");
//            OAuth2User oauth2User = delegate.loadUser(userRequest);
//            String primaryEmailAddress = extractPrimaryEmailAddress(
//                    oauth2User,
//                    userRequest.getAccessToken().getTokenValue());
//
//            email = (String) attributes.get("email"); // GitHub potrebbe non fornire email!
//            name = (String) attributes.get("name");
//            if (email == null) { // Se GitHub non restituisce email, usiamo il login
//                email = (String) attributes.get("login") + "@github.com";
//            }
        } else {
            throw new RuntimeException("Provider non supportato: " + provider);
        }

        // TODO
        // Controlla se l'utente esiste già nel DB, altrimenti lo crea
//        EndUser user = endUserRepository.findByEmail(email).orElseGet(() -> {
//            EndUser newUser = new EndUser();
//            newUser.setEmail(email);
//            newUser.setProvider(provider);
//            return newUser;
//        });
//
//        user.setName(name);
//        user.setPicture(picture);
//        endUserRepository.save(user); // Salva o aggiorna l'utente

        return oauthUser;
    }

    private OAuth2User getOAuth2User(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase(); // "GOOGLE" o "GITHUB"

        if ("GOOGLE".equals(provider)) {
            return oauth2User;
        }
        else {
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
