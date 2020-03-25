package bat.ke.qq.com.dao.impl;

import bat.ke.qq.com.dao.GroupInfoDao;
import bat.ke.qq.com.model.po.GroupInfo;
import bat.ke.qq.com.model.po.UserInfo;
import bat.ke.qq.com.util.Constant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupInfoDaoImpl implements GroupInfoDao {

    @Override
    public void loadGroupInfo() {
        UserInfo userInfo = new UserInfo("001", "ant", "001", "/img/avatar/Member001.jpg");
        UserInfo userInfo2 = new UserInfo("002", "monkey", "001", "/img/avatar/Member002.jpg");
        UserInfo userInfo3 = new UserInfo("003", "fox", "001", "/img/avatar/Member003.jpg");
        UserInfo userInfo4 = new UserInfo("004", "静静", "001", "/img/avatar/Member004.jpg");
        List<UserInfo> members = new ArrayList<UserInfo>();
        members.add(userInfo);
        members.add(userInfo2);
        members.add(userInfo3);
        members.add(userInfo4);
        GroupInfo groupInfo = new GroupInfo("01", "Group01", "/img/avatar/Group01.jpg", members);
        Constant.groupInfoMap.put(groupInfo.getGroupId(), groupInfo);
    }

    @Override
    public GroupInfo getByGroupId(String groupId) {
        return Constant.groupInfoMap.get(groupId);
    }

}
