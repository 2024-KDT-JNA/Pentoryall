package com.pentoryall.comment.mapper;

import com.pentoryall.comment.dto.CommentDTO;
import com.pentoryall.comment.dto.CommentDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDetailDTO> selectCommentByPostCode(long code);

    List<CommentDetailDTO> loadComment(CommentDetailDTO commentDTO);

    void removeReply(CommentDetailDTO commentDetailDTO);

    void addComment(CommentDetailDTO commentAdd);
}
