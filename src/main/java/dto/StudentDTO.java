package dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
	public int student_id;
	public String login_id;
	public String login_pw;
	public String name;
	public String phone;
	public Date birth; // "YYYY-MM-DD"
	public String major;
	public int grade_level;
}
