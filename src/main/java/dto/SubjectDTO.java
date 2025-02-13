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
public class SubjectDTO {
	public int subject_id;
	public String subject_name;
	public String professor;
}
