package com.meeting.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Feedback {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "content")
    private String content;
    @TableField(value = "contact")
    private String contact;
    @TableField(value = "user_id")
    private Integer userId;
}
