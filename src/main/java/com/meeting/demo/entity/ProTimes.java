package com.meeting.demo.entity;

import lombok.Data;

@Data
public class ProTimes {

    private Integer[] open;

    private Integer[] pro;

    public ProTimes(Integer[] open, Integer[] pro) {
        this.open = open;
        this.pro = pro;
    }
}
