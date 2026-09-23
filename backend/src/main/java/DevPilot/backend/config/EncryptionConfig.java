package DevPilot.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class EncryptionConfig {

    @Bean
    public TextEncryptor tokenEncryptor() {
        return Encryptors.text(
                "my-secret-password",
                "abcdef1234567890"
        );
    }
}