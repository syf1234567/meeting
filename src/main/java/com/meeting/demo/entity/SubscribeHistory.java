package com.meeting.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 预约历史
 */
@Data
public class SubscribeHistory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value="day")
    private String day;
    @TableField(value = "subscribe_time")
    private String subscribeTime;
    @TableField(value="room_id")
    private Integer roomId;
    @TableField(value="status")
    private String status;
    @TableField(value="user_id")
    private String userId;
}
