package controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.medisure.dto.JsonResult;
import com.douzone.medisure.vo.UserVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jwt.security.JwtFilter;
import jwt.security.TokenProvider;

@Tag(name = "유저 테이블", description="유저 관련 API")
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final TokenProvider tokenProvider = null;
    private final AuthenticationManagerBuilder authenticationManagerBuilder = null;
	
	
//	@PostMapping("")
//	public ResponseEntity<JsonResult> user(@Parameter(description ="로그인", name="IdandPassword")@RequestBody UserVo uservo) throws Exception {
//		return ResponseEntity 
//				.status(HttpStatus.OK) 
//				.body(JsonResult.success(userService.findByIdandPassword(uservo)));
//	}
	
	@Operation(summary="로그인하기", description="RequestBody로 Id, Password를 입력받는다")
	@PostMapping("")
    public ResponseEntity<JsonResult> login(@RequestBody UserVo uservo) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(uservo.getUserid(), uservo.getPwd());

        //authenticationManagerBuilder가 호출되면서 CustomUserDetailService가 로드됨.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

      //SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
      
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        System.out.println(jwt);
        return ResponseEntity 
				.status(HttpStatus.OK) 
				.body(JsonResult.success(jwt));
    }
}
