package com.dray.facebookservice.service.impl;

import com.dray.facebookservice.models.Post;
import com.dray.facebookservice.models.User;
import com.dray.facebookservice.repository.PostRepository;
import com.dray.facebookservice.repository.UserRepository;
import com.dray.facebookservice.service.FacebookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacebookServiceImpl implements FacebookService {

    PostRepository postRepository;

    UserRepository userRepository;

    public FacebookServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String createUser(User user) {
        String createdUserId = null;
        try {
            User createdUser = userRepository.createUser(user);
            createdUserId = createdUser.getId();
            System.out.println("User created : " + createdUserId);
        } catch (Exception e) {
            System.out.println("User creation failed due to : " + e.getMessage());
        }
        return createdUserId;
    }

    @Override
    public void createPost(String userId, String postId) {
        try {
            checkUserValid(userId);
            postRepository.createPost(userId, postId);
            System.out.printf("Post %s created by %s %n", postId, userId);
        } catch (Exception e) {
            System.out.println("Post creation failed due to : " + e.getMessage());
        }
    }

    @Override
    public List<String> getNewsFeed(String userId) {
        List<String> result = null;
        try {
            checkUserValid(userId);
            // Add logic to add news feed
            List<String> users = new ArrayList<>();
            users.add(userId);
            users.addAll(userRepository.getFollowee(userId));
            result = postRepository.getPostsForUsers(users);
            System.out.printf("News Feed fetched : %s%n", result);
        } catch (Exception e) {
            System.out.println("Failed to load news feed due to : " + e.getMessage());
        }
        return result;
    }

    @Override
    public void follow(String followerId, String followeeId) {
        //check follower id and followee id if valid
        try {
            checkUserValid(followerId);
            checkUserValid(followeeId);
            userRepository.follow(followerId, followeeId);
            System.out.printf("%s followed %s %n", followerId, followeeId);
        } catch (Exception e) {
            System.out.println("Failed to follow due to : " + e.getMessage());
        }
    }

    @Override
    public void unfollow(String followerId, String followeeId) {
        try {
            //check follower id and followee id if valid
            checkUserValid(followerId);
            checkUserValid(followeeId);
            userRepository.unfollow(followerId, followeeId);
            System.out.printf("%s unfollowed %s %n", followerId, followeeId);
        } catch (Exception e) {
            System.out.println("Failed to unfollow due to : " + e.getMessage());
        }
    }

    @Override
    public void deletePost(String userId, String postId) {
        try {
            checkPostValid(userId, postId);
            postRepository.deletePost(userId, postId);
            System.out.printf("Post %s deleted by %s %n", postId, userId);
        } catch (Exception e) {
            System.out.println("Failed to delete post due to : " + e.getMessage());
        }

    }

    @Override
    public List<String> getNewsFeedPaginated(String userId, int pageNumber) {
        List<String> result = null;
        try {
            checkUserValid(userId);
            // Add logic to add news feed
            List<String> users = new ArrayList<>();
            users.add(userId);
            users.addAll(userRepository.getFollowee(userId));
            result = postRepository.getPostsForUsersPaginated(users, pageNumber);
            System.out.printf("News Feed page number %s fetched : %s%n", pageNumber, result);
        } catch (Exception e) {
            System.out.println("Failed to load news feed due to : " + e.getMessage());
        }
        return result;
    }

    private void checkUserValid(String userId) {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("UserId is not valid");
        }
    }

    private void checkPostValid(String userId, String postId) {
        //handle if user id valid
        //handle if post id is valid
        //handle if post id belongs to userid
        checkUserValid(userId);

        Post post = postRepository.getPost(postId);
        if (post == null) {
            throw new IllegalArgumentException("PostId is not valid");
        }

        if (!Objects.equals(post.getUserId(), userId)) {
            throw new IllegalArgumentException("Wrong combination of UserId and PostId provided");
        }
    }
}
