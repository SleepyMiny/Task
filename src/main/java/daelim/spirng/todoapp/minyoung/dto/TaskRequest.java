package daelim.spirng.todoapp.minyoung.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String description;

    // 체크박스 미체크 시 전송 안 되므로 null 허용 후 서버에서 기본 false 처리
    private Boolean completed;
}