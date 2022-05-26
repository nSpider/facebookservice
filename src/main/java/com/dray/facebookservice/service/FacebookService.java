package com.dray.facebookservice.service;

import com.dray.facebookservice.models.User;

import java.util.List;

public interface FacebookService {

    String createUser(User user);

    void createPost(String userId, String postId);

    List<String> getNewsFeed(String userId);

    void follow(String followerId, String followeeId);

    void unfollow(String followerId, String followeeId);

    void deletePost(String userId, String postId);

    List<String> getNewsFeedPaginated(String userId, int pageNumber);
}
