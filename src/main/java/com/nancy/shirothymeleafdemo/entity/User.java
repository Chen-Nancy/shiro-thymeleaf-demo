package com.nancy.shirothymeleafdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author chen
 * @date 2020/6/3 0:02
 */
@Data
@ToString
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer uid;

    @NotBlank
    private String username;

    private String password;

    @NotBlank
    private String phone;

    @NotNull
    private Integer sex;

    @NotNull
    private Integer age;
}
