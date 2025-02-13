package dto;

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
public class GradeDTO {
	public int grade_id;
	public String score;
	public int student_id;
	public int subject_id;
}
