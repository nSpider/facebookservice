package com.dray.facebookservice.repository;

import com.dray.facebookservice.models.User;

import java.util.Collection;
import java.util.List;

public interface UserRepository {
    User createUser(User user);

    User getUserById(String userId);

    void follow(String followerId, String followeeId);

    void unfollow(String followerId, String followeeId);

    List<String> getFollowee(String userId);
}
