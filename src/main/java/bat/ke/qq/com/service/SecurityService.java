package bat.ke.qq.com.service;

import bat.ke.qq.com.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;

public interface SecurityService {

    ResponseJson login(String username, String password, HttpSession session);

    ResponseJson logout(HttpSession session);
}
