package com.example.rrsystem.Security;

import com.example.rrsystem.Entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("This user does not exist");
        }

        return new UserPrincipal(user);
    }

    public UserDetails loadUserById(Long userId) throws UsernameNotFoundException {
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));

        return new UserPrincipal(user);
    }

}
