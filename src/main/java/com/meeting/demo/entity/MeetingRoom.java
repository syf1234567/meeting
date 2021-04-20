package com.meeting.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 会议室  房间
 */
@Data
public class MeetingRoom {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "subscribe_history_ids")
    private String subscribeHistoryIds;
    @TableField(value = "max_num")
    private Integer maxNum;
    @TableField(value = "appliction_id")
    private String applictionId;
    @TableField(value = "appliction_secrit")
    private String applictionSecrit;
    @TableField(value = "product_id")
    private String productId;
    @TableField(value = "device_id")
    private String deviceId;
    @TableField(value = "msg_id")
    private Integer msgId;
    @TableField(value = "ablity")
    private String ablity;
    @TableField(value="service")
    private String service;
}
