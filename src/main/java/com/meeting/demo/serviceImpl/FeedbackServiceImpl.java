package com.meeting.demo.serviceImpl;

import com.meeting.demo.entity.Feedback;
import com.meeting.demo.mapper.FeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl {

    @Autowired
    private FeedbackMapper feedbackMapper;

    public void insert(Feedback feedback){
        feedbackMapper.insert(feedback);
    }

    public List<Feedback> getAll() {
        return feedbackMapper.selectList(null);
    }


}
