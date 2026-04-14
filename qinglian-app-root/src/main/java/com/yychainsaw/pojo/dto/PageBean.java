package com.yychainsaw.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "PageBean模型")
public class PageBean<T> {
    @Schema(description = "total")
    private Long total;
    private List<T> items;
}