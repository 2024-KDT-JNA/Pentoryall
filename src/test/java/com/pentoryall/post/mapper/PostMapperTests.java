package com.pentoryall.post.mapper;

import com.pentoryall.PentoryallApplication;
import com.pentoryall.comment.dto.CommentDTO;
import com.pentoryall.comment.dto.CommentDetailDTO;
import com.pentoryall.comment.mapper.CommentMapper;
import com.pentoryall.post.controller.PostController;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.user.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = { PentoryallApplication.class })
public class PostMapperTests {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostController postController;
    @Autowired
    private CommentMapper commentMapper;


//    private MockMvc mockMvc;
//    @BeforeEach
//    public void setUp(){
//        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
//    }

    @Test
    public void injection(){ assertNotNull(postMapper);}

    @Test
    @DisplayName("새로운 포스트 추가 테스트")
    @Transactional
    public void testInsertPost(){
        PostDTO post = new PostDTO();
        post.setUserCode(1L);
        post.setTitle("시리즈 테스트 제목");
        post.setContent("시리즈 테스트 내용");
        post.setThumbnailImage("시리즈 테스트 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        assertDoesNotThrow(() -> postMapper.insertPost(post));
    }

    @Test
    @DisplayName("포스트 업데이트 테스트")
    @Transactional
    public void testUpdatePost() {
        PostDTO post = new PostDTO();
        post.setUserCode(1L);
        post.setTitle("업데이트 전 제목");
        post.setContent("업데이트 전 내용");
        post.setThumbnailImage("업데이트 전 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO insertedPost = postMapper.getLatestPost();
        System.out.println("insertedPost = " + insertedPost);

        post.setCode(insertedPost.getCode());
        post.setTitle("업데이트 후 제목");
        post.setContent("업데이트 후 내용");

        assertDoesNotThrow(() -> postMapper.updatePostService(post));

        PostDTO updatedPost = postMapper.getLatestPost();
        System.out.println("updatedPost = " + updatedPost);
        assertEquals("업데이트 후 제목", updatedPost.getTitle());
        assertEquals("업데이트 후 내용", updatedPost.getContent());
    }

    @Test
    @DisplayName("포스트 조회 테스트")
    @Transactional
    public void testGetPostInformationByPostCode() {
        PostDTO post = new PostDTO();
        post.setUserCode(1L);
        post.setTitle("조회 제목");
        post.setContent("조회 내용");
        post.setThumbnailImage("조회 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO insertedPost = postMapper.getLatestPost();
        System.out.println("insertedPost = " + insertedPost);

        PostDTO actualPost = postMapper.getPostInformationByPostCode(insertedPost.getCode());

        assertEquals("조회 제목", actualPost.getTitle());
        assertEquals("조회 내용", actualPost.getContent());
    }

    @Test
    @DisplayName("포스트 삭제 테스트")
    @Transactional
    public void testDeletePostByPostCode() {
        // Given
        PostDTO post = new PostDTO();
        post.setUserCode(1L);
        post.setTitle("삭제 제목");
        post.setContent("삭제 내용");
        post.setThumbnailImage("삭제 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO latestPost = postMapper.getLatestPost();

        // When
        postMapper.deletePostByPostCode(latestPost.getCode());

        PostDTO deletedPost = postMapper.getPostInformationByPostCode(latestPost.getCode());
        assertNull(deletedPost, "포스트가 성공적으로 삭제되었는지 확인");
    }

    @Test
    @DisplayName("댓글 추가 테스트")
    @Transactional
    public void testAddComment() {
        UserDTO user = new UserDTO();
        user.setCode(1L);
        PostDTO post = new PostDTO();
        post.setUserCode(user.getCode());
        post.setTitle("삭제 제목");
        post.setContent("삭제 내용");
        post.setThumbnailImage("삭제 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO latestPost = postMapper.getLatestPost();

        CommentDetailDTO comment = new CommentDetailDTO();
        comment.setPostCode(latestPost.getCode());
        comment.setContent("조회할 댓글 내용");
        comment.setUser(user);
        assertDoesNotThrow(() -> commentMapper.addComment(comment));
    }

    @Test
    @DisplayName("댓글 업데이트 테스트")
    @Transactional
    public void testUpdateComment() {
        UserDTO user = new UserDTO();
        user.setCode(1L);
        PostDTO post = new PostDTO();
        post.setUserCode(user.getCode());
        post.setTitle("업데이트 전 제목");
        post.setContent("업데이트 전 내용");
        post.setThumbnailImage("업데이트 전 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO latestPost = postMapper.getLatestPost();

        CommentDetailDTO comment = new CommentDetailDTO();
        comment.setPostCode(latestPost.getCode());
        comment.setContent("업데이트 전 댓글 내용");
        comment.setUser(user);

        commentMapper.addComment(comment);


        CommentDetailDTO latestComment = commentMapper.getLatestComment();

        latestComment.setContent("업데이트 후 댓글 내용");

        commentMapper.updateComment(latestComment);
        CommentDetailDTO updatedComment = commentMapper.getLatestComment();

        assertEquals("업데이트 후 댓글 내용", updatedComment.getContent());
    }

    @Test
    @DisplayName("댓글 조회 테스트")
    @Transactional
    public void testSelectCommentByPostCode() {
        UserDTO user = new UserDTO();
        user.setCode(1L);
        PostDTO post = new PostDTO();
        post.setUserCode(user.getCode());
        post.setTitle("조회 테스트 제목");
        post.setContent("조회 테스트 내용");
        post.setThumbnailImage("조회 테스트 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO latestPost = postMapper.getLatestPost();

        CommentDetailDTO comment = new CommentDetailDTO();
        comment.setPostCode(latestPost.getCode());
        comment.setContent("조회 테스트 댓글 내용");
        comment.setUser(user);

        commentMapper.addComment(comment);

        CommentDetailDTO latestComment = commentMapper.getLatestComment();

        assertEquals("조회 테스트 댓글 내용", latestComment.getContent());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    @Transactional
    public void testRemoveReply() {
        UserDTO user = new UserDTO();
        user.setCode(1L);
        PostDTO post = new PostDTO();
        post.setUserCode(user.getCode());
        post.setTitle("삭제 테스트 제목");
        post.setContent("삭제 테스트 내용");
        post.setThumbnailImage("삭제 테스트 썸네일 경로");
        post.setPrice(300);
        post.setIsPaid('Y');
        post.setIsAdult('N');
        post.setIsPublic('N');

        postMapper.insertPost(post);

        PostDTO latestPost = postMapper.getLatestPost();

        CommentDetailDTO comment = new CommentDetailDTO();
        comment.setPostCode(latestPost.getCode());
        comment.setContent("삭제 테스트 댓글 내용");
        comment.setUser(user);

        commentMapper.addComment(comment);

        CommentDetailDTO commentDetail = commentMapper.getLatestComment();
        System.out.println("commentDetail = " + commentDetail);

        commentMapper.removeReply(commentDetail);

        // Then: 삭제된 댓글의 상태 확인
        CommentDetailDTO deletedComment = commentMapper.findCommentByCode(commentDetail.getCode());
        System.out.println("deletedComment = " + deletedComment);
        assertEquals('Y', deletedComment.getIsDeleted());
    }



}
