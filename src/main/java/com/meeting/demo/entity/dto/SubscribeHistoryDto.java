package com.meeting.demo.entity.dto;

import com.meeting.demo.entity.MeetingRoom;
import com.meeting.demo.entity.MeetingUsers;
import lombok.Data;

@Data
public class SubscribeHistoryDto {

    private Integer id;

    private String day;

    private String subscribeTime;

    private MeetingRoom meetingRoom;

    private String status;

    private MeetingUsers meetingUsers;

    public SubscribeHistoryDto(Integer id, String day, String subscribeTime, MeetingRoom meetingRoom, String status, MeetingUsers meetingUsers) {
        this.id = id;
        this.day = day;
        this.subscribeTime = subscribeTime;
        this.meetingRoom = meetingRoom;
        this.status = status;
        this.meetingUsers = meetingUsers;
    }
}
