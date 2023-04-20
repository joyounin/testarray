package jwt.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.medisure.service.UserService;
import com.douzone.medisure.vo.CustomUserDetailes;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return new CustomUserDetailes(Optional.ofNullable(userService.findById(id)).orElseThrow(()->new UsernameNotFoundException(id + " -> 존재 하지 않음.")));
    }
}