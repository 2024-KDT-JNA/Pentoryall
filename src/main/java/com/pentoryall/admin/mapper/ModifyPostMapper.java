package com.pentoryall.admin.mapper;
import com.pentoryall.admin.page.SelectCriteria;
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


