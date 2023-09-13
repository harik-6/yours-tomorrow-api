package com.example.demo.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSubmissionDto {
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("exam_id")
    private String examId;
    @JsonProperty("question_id")
    private String questionId;
    @JsonProperty("answer_id")
    private String answerId;
}
