package bat.ke.qq.com.dao.impl;

import bat.ke.qq.com.dao.UserInfoDao;
import bat.ke.qq.com.model.po.GroupInfo;
import bat.ke.qq.com.model.po.UserInfo;
import bat.ke.qq.com.util.Constant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    @Override
    public void loadUserInfo() {
        // 设置用户基本信息，共3个用户
        UserInfo userInfo = new UserInfo("001", "ant", "001", "/img/avatar/Member001.jpg");
        UserInfo userInfo2 = new UserInfo("002", "monkey", "001", "/img/avatar/Member002.jpg");
        UserInfo userInfo3 = new UserInfo("003", "fox", "001", "/img/avatar/Member003.jpg");
        UserInfo userInfo4 = new UserInfo("004", "静静", "001", "/img/avatar/Member004.jpg");
        // 设置用户好友列表
        userInfo.setFriendList(generateFriendList("001"));
        userInfo2.setFriendList(generateFriendList("002"));
        userInfo3.setFriendList(generateFriendList("003"));
        userInfo4.setFriendList(generateFriendList("004"));
        
        // 设置用户群列表，共1个群
        GroupInfo groupInfo = new GroupInfo("01", "源码学院", "/img/avatar/Group01.jpg", null);
        List<GroupInfo> groupList = new ArrayList<GroupInfo>();
        groupList.add(groupInfo);
        userInfo.setGroupList(groupList);
        userInfo2.setGroupList(groupList);
        userInfo3.setGroupList(groupList);
        userInfo4.setGroupList(groupList);
        
        Constant.userInfoMap.put("monkey", userInfo);
        Constant.userInfoMap.put("fox", userInfo2);
        Constant.userInfoMap.put("ant", userInfo3);
        Constant.userInfoMap.put("静静", userInfo4);

    }

    @Override
    public UserInfo getByUsername(String username) {
        return Constant.userInfoMap.get(username);
    }
    
    @Override
    public UserInfo getByUserId(String userId) {
        UserInfo result = null;
        Iterator<Entry<String, UserInfo>> iterator = Constant.userInfoMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, UserInfo> entry = iterator.next();
            if (entry.getValue().getUserId().equals(userId)) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }
    
    private List<UserInfo> generateFriendList(String userId) {
        UserInfo userInfo = new UserInfo("001", "monkey", "001", "/img/avatar/Member001.jpg");
        UserInfo userInfo2 = new UserInfo("002", "fox", "002", "/img/avatar/Member002.jpg");
        UserInfo userInfo3 = new UserInfo("003", "ant", "003", "/img/avatar/Member003.jpg");
        UserInfo userInfo4 = new UserInfo("004", "静静", "001", "/img/avatar/Member004.jpg");
        List<UserInfo> friendList = new ArrayList<UserInfo>();
        friendList.add(userInfo);
        friendList.add(userInfo2);
        friendList.add(userInfo3);
        friendList.add(userInfo4);

        Iterator<UserInfo> iterator = friendList.iterator();
        while(iterator.hasNext()) {
            UserInfo entry = iterator.next();
            if (userId.equals(entry.getUserId())) {
                iterator.remove();
            }
        }
        return friendList;
    }


}
