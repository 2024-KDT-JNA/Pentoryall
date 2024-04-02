package com.pentoryall.admin.Service;

import com.pentoryall.admin.mapper.ModifyPostMapper;
import com.pentoryall.admin.page.Pagenation;
import com.pentoryall.admin.page.SelectCriteria;
import com.pentoryall.post.dto.PostDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class ModifyPostService {

    private final ModifyPostMapper modifyPostMapper;

    public ModifyPostService(ModifyPostMapper modifyPostMapper) {
        this.modifyPostMapper = modifyPostMapper;
    }

    public Map<String, Object> selectAllModifyList(Map<String, String> searchMap, int page) {
        int totalCount = modifyPostMapper.selectTotalCount(searchMap);
        log.info("modifyPostList totalCount : {}", totalCount);

        int limit = 10;
        int buttonAmount = 5;
        SelectCriteria selectCriteria = Pagenation.getSelectCriteria(page, totalCount, limit, buttonAmount, searchMap);
        log.info("modifyPostList selectCriteria : {}", selectCriteria);

        /* 요청 페이지와 검색 기준에 맞는 게시글을 조회해온다. */
        List<PostDTO> modifyPostList = modifyPostMapper.selectAllModifyList(selectCriteria);
        log.info("modifyPostList : {}", modifyPostList);

        // Model에 추가할 속성 이름을 Controller에서 사용하는 이름으로 변경
        Map<String, Object> modifyPostListAndPaging = new HashMap<>();
        modifyPostListAndPaging.put("paging", selectCriteria);
        modifyPostListAndPaging.put("modifyPostAllList", modifyPostList); // modifyPostList를 modifyPostAllList로 변경

        return modifyPostListAndPaging;
    }

    public int modifyByPostCode(long postCode, String confirmContent) {

        System.out.println("서비스 postCode : " + postCode);
        return modifyPostMapper.modifyByPostCode(postCode, confirmContent);
    }
}
