package com.yychainsaw.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class PostUpdateDTO {
    @NotBlank(message = "内容不能为空")
    private String content;

    private List<String> imageUrls;

}
