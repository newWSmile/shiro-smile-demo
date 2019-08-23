package com.smile.shirosmiledemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * user
 *
 * @author smile 2019-08-22
 */
@Entity
@Data
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户类型：admin/high/normal
     */
    private String userType;


    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户加密salt
     */
    private String salt;

    /**
     * 用户状态：effective/disable
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}