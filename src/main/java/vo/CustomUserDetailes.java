package vo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class CustomUserDetailes implements UserDetails{

   private final UserVo uservo;
   
   public CustomUserDetailes(UserVo uservo) {
      this.uservo = uservo;
   }
   
   //해당유저 권한 리턴
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities(){
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new GrantedAuthority(){
         @Override
         public String getAuthority() {
            return uservo.getPosition();
         }
      });
      return authorities;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }
   
   @Override
   public boolean isAccountNonLocked() {
      return true;
   }
   
   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }
   
   @Override
   public boolean isEnabled() {
      return true;
   }

   @Override
   public String getPassword() {
      return uservo.getPwd();
   }
   
   @Override
   public String getUsername() {
      return uservo.getUserid();
   }
   
}
