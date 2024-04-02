package com.pentoryall.comment.mapper;

import com.pentoryall.comment.dto.CommentDTO;
import com.pentoryall.comment.dto.CommentDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDetailDTO> selectCommentByPostCode(long code);
}
