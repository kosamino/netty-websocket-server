package bat.ke.qq.com.service;

import bat.ke.qq.com.model.vo.ResponseJson;

public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
