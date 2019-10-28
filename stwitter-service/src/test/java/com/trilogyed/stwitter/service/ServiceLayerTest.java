package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.model.Comment;
import com.trilogyed.stwitter.util.feign.CommentFeign;
import com.trilogyed.stwitter.util.feign.PostFeign;
import com.trilogyed.stwitter.util.feign.messages.CommentEntry;
import com.trilogyed.stwitter.viewModel.CommentViewModel;
import com.trilogyed.stwitter.viewModel.PostCommentViewModel;
import com.trilogyed.stwitter.viewModel.PostViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceLayerTest {


    ServiceLayer service;
    PostFeign pClient;
    CommentFeign cClient;
    RabbitTemplate rabbitTemplate;




    @Before
    public void setUp() throws Exception {

//        setUpPostFeignMock();
        setUpCommentFeignMock();
        setUpRabbitTemplateMock();
        service = new ServiceLayer(pClient,cClient, rabbitTemplate);

    }

    @After
    public void tearDown() throws Exception {

    }


    public void setUpRabbitTemplateMock(){
        rabbitTemplate = mock(RabbitTemplate.class);
        String EXCHANGE = "comment-exchange";

        String ROUTING_KEY = "comment.create.stwitter.service";

        CommentEntry ce = new CommentEntry(1,1, "Test", "2019-9-30", "Test");

        doNothing().when(rabbitTemplate).convertAndSend(EXCHANGE,ROUTING_KEY, ce);

    }
//
//    public void setUpPostFeignMock() {
//        pClient = mock(PostFeign.class);
//
//        PostCommentViewModel p = new PostCommentViewModel();
//        p.setPostId(1);
//        p.setPost("Test");
//        p.setPostDate(LocalDate.of(2019, 9, 30));
//        p.setPosterName("Test");
//
//        CommentViewModel c = new CommentViewModel();
//        c.setCommentId(1);
//        c.setPostId(1);
//        c.setCommenterName("Test");
//        c.setCommentDate("2019-9-30");
//        c.setComment("Test");
//
//
//        CommentViewModel c2 = new CommentViewModel();
//        c.setCommentId(1);
//        c.setPostId(1);
//        c.setCommenterName("Test");
//        c.setCommentDate("2019-9-30");
//        c.setComment("Test");
//
//        List<CommentViewModel> cList = new ArrayList<>();
//        cList.add(c);
//        cList.add(c2);
//
//        List<String> comments = new ArrayList<>();
//
//        for(int i = 0; i < cList.size(); i++ ){
//            comments.add(cList.get(i).getComment());
//        }
//
//        p.setComments(comments);
//
//
//
//        PostCommentViewModel p2 = new PostCommentViewModel();
//        p2.setPost("Test");
//        p2.setPostDate(LocalDate.of(2019, 9, 30));
//        p2.setPosterName("Test");
//        p2.setComments(comments);
//
//
//        List<PostCommentViewModel> pList = new ArrayList<>();
//        pList.add(p);
//
//
//        PostViewModel pvm = new PostViewModel();
//        p.setPostId(5);
//        p.setPostDate(LocalDate.of(2019, 9, 30));
//        p.setPosterName("Test");
//        p.setPost("Test");
//
//        PostViewModel pvm2 = new PostViewModel();
//        p.setPostDate(LocalDate.of(2019, 9, 30));
//        p.setPosterName("Test");
//        p.setPost("Test");
//
//
//        List<PostViewModel> pvmList = new ArrayList<>();
//        pvmList.add(pvm);
//
//
//
//
//        doReturn(pvm).when(pClient).createPost(pvm2);
//        doReturn(p).when(pClient).getPost(5);
//        doReturn(pList).when(pClient).getPostByPosterName("Test");
//
//    }
//
    public void setUpCommentFeignMock() {
        cClient = mock(CommentFeign.class);
        CommentViewModel c = new CommentViewModel();
        c.setCommentId(1);
        c.setPostId(1);
        c.setCommenterName("Test");
        c.setCommentDate("2019-9-30");
        c.setComment("Test");


        CommentViewModel c2 = new CommentViewModel();
//        c.setCommentId(1);
        c.setPostId(1);
        c.setCommenterName("Test");
        c.setCommentDate("2019-9-30");
        c.setComment("Test");

        List<CommentViewModel> cList = new ArrayList<>();
        cList.add(c);

        doReturn(c).when(cClient).createComment(c2);
        doReturn(c).when(cClient).getComment(1);

    }

    @Test
    public void createComment() {

        CommentViewModel c = new CommentViewModel();
        c.setCommentId(1);
        c.setPostId(1);
        c.setCommenterName("Test");
        c.setCommentDate("2019-9-30");
        c.setComment("Test");

        service.createComment(c);

        CommentViewModel c2 = cClient.getComment(c.getCommentId());

        assertEquals(c,c2);


    }
}