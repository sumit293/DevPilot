package DevPilot.backend.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import DevPilot.backend.entity.User;

/**
 * AppUserPrincipal
 */
public class AppUserPrincipal implements  OAuth2User{
    private final User user;
    private final  OAuth2User githubUser;
    private final Map<String, Object> attributes;

    public AppUserPrincipal(User user, OAuth2User githubUser) {
        this.user = user;
        this.githubUser = githubUser;
        this.attributes = githubUser.getAttributes();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return githubUser.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return githubUser.getAuthorities();
    }

    @Override
    public String getName() {
        return String.valueOf(user.getId());
    }

    public User getUser() {
        return user;
    }

}
