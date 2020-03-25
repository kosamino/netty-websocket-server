package bat.ke.qq.com.service.impl;

import bat.ke.qq.com.dao.UserInfoDao;
import bat.ke.qq.com.model.po.UserInfo;
import bat.ke.qq.com.model.vo.ResponseJson;
import bat.ke.qq.com.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    
    @Override
    public ResponseJson getByUserId(String userId) {
        UserInfo userInfo = userInfoDao.getByUserId(userId);
        return new ResponseJson().success()
                .setData("userInfo", userInfo);
    }

}
