package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.exception.NotFoundException;
import com.trilogyed.stwitter.model.Post;
import com.trilogyed.stwitter.service.ServiceLayer;
import com.trilogyed.stwitter.viewModel.CommentViewModel;
import com.trilogyed.stwitter.viewModel.PostCommentViewModel;
import com.trilogyed.stwitter.viewModel.PostViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"stwitter"})
@RequestMapping("/posts")
public class StwitterController {


    @Autowired
    ServiceLayer service;


    @CachePut(key = "#result.getPostId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostCommentViewModel createPost(@RequestBody @Valid PostCommentViewModel post) {

        return service.createPost(post);

    }

    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostCommentViewModel getPost(@PathVariable("id") int id) {
        PostCommentViewModel pvm = service.getPost(id);
        if (pvm == null)
            throw new NotFoundException("Cannot find id: " + id);
        return pvm;

    }

    @GetMapping("/user/{postername}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostCommentViewModel> getPostByPosterName(@PathVariable("postername") String posterName) {

        List<PostCommentViewModel> pvmList = service.getPostByPosterName(posterName);
        if (pvmList.size() == 0)
            throw new NotFoundException("Cannot find poster: " + posterName);
        return pvmList;
    }


    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public String createComment(@RequestBody @Valid CommentViewModel cvm) {

        return service.createComment(cvm);

    }
}
