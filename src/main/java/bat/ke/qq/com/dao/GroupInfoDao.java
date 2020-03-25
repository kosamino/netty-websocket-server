package bat.ke.qq.com.dao;


import bat.ke.qq.com.model.po.GroupInfo;

public interface GroupInfoDao {

    void loadGroupInfo();
    
    GroupInfo getByGroupId(String groupId);
}
