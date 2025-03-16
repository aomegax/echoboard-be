package dev.aomegax.echoboard.be.config.security;

import dev.aomegax.echoboard.be.exception.AppError;
import dev.aomegax.echoboard.be.exception.AppException;
import dev.aomegax.echoboard.be.model.EndUser;
import dev.aomegax.echoboard.be.model.OAuth2ProviderType;
import dev.aomegax.echoboard.be.repository.EndUserRepository;

import java.util.Map;
import java.util.Optional;

public class CustomEndUserUtil {

    public static void checkEndUser(EndUserRepository endUserRepository, OAuth2ProviderType provider, Map<String, Object> attributes) {
        String email = (String) attributes.get("email");
        Optional<EndUser> endUserOpt = endUserRepository.findByEmail(email);
        if (endUserOpt.isPresent()) {
            boolean toSave = false;
            EndUser endUser = endUserOpt.get();
            switch (provider) {
                case GITHUB:
                    if (endUser.getGithubEmail() == null) {
                        endUser.setGithubEmail(email);
                    }
                    break;
                case GOOGLE:
                    if (endUser.getGoogleEmail() == null) {
                        endUser.setGoogleEmail(email);
                    }
                    break;
                default:
                    throw new AppException(AppError.PROVIDER_UNKNOWN, null);
            }

            if (toSave) {
                endUserRepository.save(endUser);
            }
        }
        else {
            String[] name = ((String) attributes.get("name")).trim().split("\s", 2);
            EndUser endUser = EndUser.builder()
                    .firstName(name[0])
                    .lastName(name[1])
                    .googleEmail(provider == OAuth2ProviderType.GOOGLE ? email : null)
                    .githubEmail(provider == OAuth2ProviderType.GITHUB ? email : null)
                    .build();
            endUserRepository.save(endUser);
        }

    }
}
