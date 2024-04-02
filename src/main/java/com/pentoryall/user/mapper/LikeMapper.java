package com.pentoryall.user.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikeMapper {
    void likeUp(long postCode, long userCode);

    void likeDown(long postCode, long userCode);
}