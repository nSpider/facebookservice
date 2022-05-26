package com.dray.facebookservice;

import com.dray.facebookservice.models.User;
import com.dray.facebookservice.repository.impl.PostRepositoryImpl;
import com.dray.facebookservice.repository.impl.UserRepositoryImpl;
import com.dray.facebookservice.service.FacebookService;
import com.dray.facebookservice.service.impl.FacebookServiceImpl;

public class Client {

    public static void main(String[] args) {
        FacebookService facebookService = new FacebookServiceImpl(new PostRepositoryImpl(), new UserRepositoryImpl());

//        User user_1 = new User();
//        user_1.setFirstName("Dipayan");
//        user_1.setLastName("Ray");
//        user_1.setEmailId("nspiderdip@gmail.com");
//
//        User user_2 = new User();
//        user_2.setFirstName("Issac");
//        user_2.setLastName("Newton");
//        user_2.setEmailId("in@gmail.com");
//
//        String user1 = facebookService.createUser(user_1);
//        String user2 = facebookService.createUser(user_2);
//
//        facebookService.createPost(user1, "post-1");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        facebookService.createPost(user2, "post-2");
//
//        facebookService.getNewsFeed(user1);
//
//        facebookService.getNewsFeedPaginated(user1, 2);
//
//        facebookService.follow(user1, user2);
//
//        facebookService.getNewsFeed(user1);
//
//        facebookService.getNewsFeedPaginated(user1, 2);
//
//        facebookService.deletePost(user1, "post-1");
//
//        facebookService.getNewsFeed(user1);
//
//        facebookService.getNewsFeedPaginated(user1, 2);

        User user_1 = new User();
        user_1.setFirstName("Dipayan");
        user_1.setLastName("Ray");
        user_1.setEmailId("nspiderdip@gmail.com");

        User user_2 = new User();
        user_2.setFirstName("Issac");
        user_2.setLastName("Newton");
        user_2.setEmailId("in@gmail.com");

        User user_3 = new User();
        user_3.setFirstName("Name 3");
        user_3.setLastName("Newton");
        user_3.setEmailId("in@gmail.com");

        String user1 = facebookService.createUser(user_1);
        String user2 = facebookService.createUser(user_2);
        String user3 = facebookService.createUser(user_3);

        facebookService.createPost(user1, "post-1");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        facebookService.createPost(user1, "post-2");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        facebookService.createPost(user2, "post-3");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        facebookService.createPost(user2, "post-4");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        facebookService.createPost(user3, "post-5");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        facebookService.createPost(user3, "post-6");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        facebookService.follow(user1, user2);
        facebookService.follow(user1, user3);

        facebookService.deletePost(user2, "post-3");

        facebookService.getNewsFeed(user1);

        facebookService.unfollow(user1, user3);

        facebookService.getNewsFeed(user1);

    }


}
