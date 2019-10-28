package com.trilogyed.post.dao.service;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.dao.PostDaoJdbcTemplateImpl;
import com.trilogyed.post.model.Post;
import com.trilogyed.post.service.ServiceLayer;
import com.trilogyed.post.viewModel.PostViewModel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {
    PostDao postDao;
    ServiceLayer service;

    @Before
    public void setUp() throws Exception{

        setUpPostDaoDaoMock();

        service = new ServiceLayer(postDao);
    }

    private void setUpPostDaoDaoMock(){
        postDao = mock(PostDaoJdbcTemplateImpl.class);

        Post post = new Post();
        post.setPostId(1);
        post.setPostDate(LocalDate.of(2019, 9, 30));
        post.setPosterName("Test");
        post.setPost("Test");

        Post post2 = new Post();
        post2.setPostDate(LocalDate.of(2019, 9, 30));
        post2.setPosterName("Test");
        post2.setPost("Test");

        List<Post> pList = new ArrayList<>();
        pList.add(post);

        doReturn(post).when(postDao).createPost(post2);
        doReturn(post).when(postDao).getPost(1);
        doReturn(pList).when(postDao).getAllPosts();


    }


    @Test
    public void saveFindPost(){
        PostViewModel p = new PostViewModel();

        p.setPostDate(LocalDate.of(2019, 9, 30));
        p.setPosterName("Test");
        p.setPost("Test");

        p = service.createPost(p);

        PostViewModel p2 = service.getPost(p.getPostId());

        assertEquals(p, p2);

    }

    @Test
    public void getAllPosts() {

        List<PostViewModel> pList = service.getAllPosts();

        assertEquals(1, pList.size());


    }



}
