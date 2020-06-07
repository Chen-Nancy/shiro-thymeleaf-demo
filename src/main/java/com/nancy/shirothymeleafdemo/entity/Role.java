package com.nancy.shirothymeleafdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author chen
 * @date 2020/6/3 0:04
 */
@Data
@ToString
@NoArgsConstructor
public class Role {
    private Integer rid;

    @NotBlank
    private String name;
}
