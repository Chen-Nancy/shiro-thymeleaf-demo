package com.nancy.shirothymeleafdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chen
 * @date 2020/6/3 0:04
 */
@Data
@ToString
@NoArgsConstructor
public class Permission {
    private Integer pid;

    @NotBlank
    private String name;

    @NotBlank
    private String resource;

    @NotNull
    private Integer parentId;

    @NotNull
    private Integer level;

    private List<Permission> childPerms;
}
