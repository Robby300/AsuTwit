package com.rob.asuTwit.repo;

import com.rob.asuTwit.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
