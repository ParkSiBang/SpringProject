package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GradeDetailDTO {
	public int grade_id;
	public String name;
	public String subject_name;
	public String professor;
	public String score;
}
