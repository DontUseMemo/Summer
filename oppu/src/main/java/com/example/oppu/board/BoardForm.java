package com.example.oppu.board;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BoardForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    private String title;

    @NotEmpty(message = "카테고리는 필수항목입니다.")
    private String category;

    private String content;
}