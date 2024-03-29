package com.pentoryall.admin.mapper;
import com.pentoryall.admin.DTO.UserManageDTO;
import com.pentoryall.admin.page.SelectCriteria;
import com.pentoryall.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface UserManageMapper {
    

    int selectTotalCount(Map<String, String> searchMap);

    List<UserManageDTO> selectAllUserList(SelectCriteria selectCriteria);
}
