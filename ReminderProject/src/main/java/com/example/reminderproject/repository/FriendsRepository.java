package com.example.reminderproject.repository;

import com.example.reminderproject.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    List<Long> findFriendsByUserId(Long userId);
    Friends getFriendsByUserIdAndFriendId(Long currUserId, Long friendId);
}
