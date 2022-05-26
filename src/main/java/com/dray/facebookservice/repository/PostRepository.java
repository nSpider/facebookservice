package com.dray.facebookservice.repository;

import com.dray.facebookservice.models.Post;

import java.util.List;

public interface PostRepository {
    Post createPost(String userId, String postId);

    Post getPost(String postId);

    void deletePost(String userId, String postId);

    List<String> getPostsForUsers(List<String> users);

    List<String> getPostsForUsersPaginated(List<String> users, int pageNumber);
}
