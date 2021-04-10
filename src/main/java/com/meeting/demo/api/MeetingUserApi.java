package com.meeting.demo.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.meeting.demo.entity.MeetingUsers;
import com.meeting.demo.serviceImpl.MeetingUsersServiceImpl;
import com.meeting.demo.util.Request;
import com.sun.jndi.toolkit.url.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class MeetingUserApi {

    @Autowired
    private MeetingUsersServiceImpl meetingUsersService;

    @PostMapping("/getByOpenId")
    public MeetingUsers getByOpenId(String openId) {
        return meetingUsersService.getByOpenId(openId);
    }


    @RequestMapping("/login")
    public MeetingUsers doLogin(String code, String rawData, String signature, String encrypteData, String iv) {
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject rawDataJson = JSON.parseObject(rawData);
        JSONObject SessionKeyOpenId = getSessionKeyOrOpenId(code);
        String openid = SessionKeyOpenId.getString("openid");
        String sessionKey = SessionKeyOpenId.getString("session_key");
        //User user = userService.findByOpenid( openid );
        MeetingUsers meetingUsers = meetingUsersService.getByOpenId(openid);
        if (meetingUsers == null) {
               return meetingUsersService.insert(openid);
        }else{
            return meetingUsers;
        }
        //uuid生成唯一key
        //String skey = UUID.randomUUID().toString();
        //return null;
    }

    @RequestMapping("/update")
    public void update(MeetingUsers meetingUsers){
        meetingUsersService.update(meetingUsers);
    }


    public static JSONObject getSessionKeyOrOpenId(String code) {
        //微信端登录code
        String wxCode = code;
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid", "wxf6893f6fe1eb7d91");//小程序appId
        requestUrlParam.put("secret", "3f70eed732b6f9aa698ac09f04c49e26");
        requestUrlParam.put("js_code", wxCode);//小程序端返回的code
        requestUrlParam.put("grant_type", "authorization_code");//默认参数

        //发送post请求读取调用微信接口获取openid用户唯一标识
        JSONObject jsonObject = JSON.parseObject(Request.sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }

}
