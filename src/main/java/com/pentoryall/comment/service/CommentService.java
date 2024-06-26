package com.pentoryall.comment.service;

import com.pentoryall.comment.dto.CommentDTO;
import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.comment.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentMapper commentMapper;

    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<CommentDetailDTO> selectCommentByPostCode(long code) {
        return commentMapper.selectCommentByPostCode(code);
    }

    public List<CommentDetailDTO> loadComment(CommentDetailDTO commentDTO) {
        return commentMapper.loadComment(commentDTO);
    }

    public void removeReply(CommentDetailDTO commentDetailDTO) {
        commentMapper.removeReply(commentDetailDTO);
    }

    public void addComment(CommentDetailDTO commentAdd) {
        commentMapper.addComment(commentAdd);
    }

    public void updateComment(CommentDetailDTO commentDetailDTO) {
        commentMapper.updateComment(commentDetailDTO);
    }

    public List<CommentDetailDTO> selectRefCommentByPostCode(long code) {
        return commentMapper.selectRefCommentByPostCode(code);
    }

    public void addRefComment(CommentDetailDTO commentAdd) {
        commentMapper.addRefComment(commentAdd);
    }

    public List<CommentDetailDTO> loadReply(CommentDetailDTO commentDTO) {
       return commentMapper.loadReply(commentDTO);
    }

    public List<CommentDetailDTO> loadAdditionalData(CommentDetailDTO commentDTO) {
        return commentMapper.loadAdditionalData(commentDTO);
    }
}
