package com.trilogyed.comment.controller;

import com.trilogyed.comment.exception.NotFoundException;
import com.trilogyed.comment.service.ServiceLayer;
import com.trilogyed.comment.viewModel.CommentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"comments"})
@RequestMapping("/comments")
public class CommentController {


    @Autowired
    ServiceLayer service;

    @CachePut(key = "#result.getCommentId()")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentViewModel createComment(@RequestBody @Valid CommentViewModel cvm) {

        return service.createComment(cvm);

    }

    @GetMapping("/{id}")
    @Cacheable
    @ResponseStatus(HttpStatus.OK)
    public CommentViewModel getComment(@PathVariable("id") int commentId) {
        CommentViewModel cvm = service.getComment(commentId);
        if (cvm == null)
            throw new NotFoundException("Cannot find id: " + commentId);
        return cvm;

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentViewModel> getAllComments(){
        return service.getAllComments();
    }


    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteComment(@PathVariable("id") int commentId){
        service.deleteComment(commentId);
        return "Comment successfully deleted.";
    }


    @PutMapping("/{id}")
    @CacheEvict(key = "#cvm.getCommentId()")
    @ResponseStatus(HttpStatus.OK)
    public String updateComment(@PathVariable("id") int commentId, @RequestBody @Valid CommentViewModel cvm) {
        service.updateComment(cvm);
        return "Comment successfully updated.";
    }

    @GetMapping(value = "/poster/{id}")
    public List<CommentViewModel> getCommentByPostId(@PathVariable("id") int postId) {

        List<CommentViewModel> cList = service.getCommentByPostId(postId);
        if (cList.size() == 0)
            throw new NotFoundException("Cannot find comment with this post id: " + postId);
        return cList;
    }



}
