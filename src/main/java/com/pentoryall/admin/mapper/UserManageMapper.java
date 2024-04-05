package com.pentoryall.admin.mapper;

import com.pentoryall.admin.dto.UserManageDTO;
import com.pentoryall.common.page.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserManageMapper {


    int selectTotalCount(Map<String, String> searchMap);

    List<UserManageDTO> selectAllUserList(SelectCriteria selectCriteria);

    void resumeUser(String userCode);
}
