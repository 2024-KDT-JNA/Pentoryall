package com.pentoryall.user.mapper;

import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.user.dto.LikeDTO;
import com.pentoryall.user.dto.LikePostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikePostMapper {

    List<LikePostDTO> getLikedPostsByUserCode(long userCode);

    int getLikeCount(long userCode);

    List<LikePostDTO> selectLikeByPostCode(Long seriesCode, Long code);

    int selectLikeCountByPostCode(Long code);

    List<Long> selectTop5Post();

    List<LikePostDTO> selectTop10Series();

    List<LikePostDTO> selectLikeByPostTitle(String title);
}
