package DevPilot.backend.services;

import java.util.UUID;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DevPilot.backend.entity.User;
import DevPilot.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class UserService {


    public final UserRepository userRepository;
    public final TextEncryptor tokenEncryptor;

    public User userFromGithub(){
        throw  new UnsupportedOperationException("unemplemented method   user not found ");

    }

    @Transactional (readOnly = true)
    public User requiredById(UUID id){
        return  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public String decryptAccessToken(User user) {
        return tokenEncryptor.decrypt(user.getAccessToken());
    }


    private static long toLong(Object value ){
        if(value  instanceof Number number){
            return  number.longValue();

        }
        return Long.parseLong(value.toString());
    }
    

    
}
