package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "PostCreateDTO模型")
public class PostCreateDTO {
    @NotBlank(message = "内容不能为空")
    @Schema(description = "content")
    private String content;

    @Schema(description = "imageUrls")
    private List<String> imageUrls;
}
