package com.example.interactive.chat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interactive.chat.model.Message;
import com.example.interactive.chat.model.User;
/**
*
* @author Artur Korra
*/
public interface MessageRepository extends JpaRepository<Message, String> {

	List<Message> findByUserAndSeen(Optional<User> user, boolean seen);
}
