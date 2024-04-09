package com.pentoryall.admin.mappers;

import com.pentoryall.common.page.SelectCriteria;
import com.pentoryall.post.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ModifyPostMapper {

    int selectTotalCount(Map<String, String> searchMap);

    List<PostDTO> selectAllModifyList(SelectCriteria selectCriteria);

    int modifyByPostCode(long postCode, String confirmContent);
}


