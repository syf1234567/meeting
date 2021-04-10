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

}
