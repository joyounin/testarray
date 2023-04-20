package vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVo {
	private Long userno;
	private String userid;
	private String pwd;
	private String position;
	private String username;
}
