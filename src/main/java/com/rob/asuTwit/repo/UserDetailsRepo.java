package com.rob.asuTwit.repo;

import com.rob.asuTwit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
