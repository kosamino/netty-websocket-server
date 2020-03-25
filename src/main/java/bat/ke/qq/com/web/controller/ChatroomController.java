package bat.ke.qq.com.web.controller;


import bat.ke.qq.com.model.vo.ResponseJson;
import bat.ke.qq.com.service.UserInfoService;
import bat.ke.qq.com.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chatroom")
public class ChatroomController {

    @Autowired
    UserInfoService userInfoService;

    /**
     * 登录成功后，调用此接口进行页面跳转
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toChatroom() {
        return "chatroom";
    }
    
    /**
     * 登录成功跳转页面后，调用此接口获取用户信息
     * @return
     */
    @RequestMapping(value = "/get_userinfo", method = RequestMethod.POST) 
    @ResponseBody
    public ResponseJson getUserInfo(HttpSession session) {
        Object userId = session.getAttribute(Constant.USER_TOKEN);
        return userInfoService.getByUserId((String)userId);
    }
}
