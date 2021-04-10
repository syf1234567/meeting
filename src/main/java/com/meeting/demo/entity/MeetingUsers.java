package com.meeting.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 会议使用人员
 */
@Data
public class MeetingUsers {
        @TableId(type=IdType.AUTO)
        private Integer id;
        @TableField(value="user_name")
        private String userName;
        @TableField(value="open_id")
        private String openId;
        @TableField(value="mobile")
        private String mobile;
}
