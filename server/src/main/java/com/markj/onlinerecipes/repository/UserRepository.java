package com.markj.onlinerecipes.repository;

import com.markj.onlinerecipes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
