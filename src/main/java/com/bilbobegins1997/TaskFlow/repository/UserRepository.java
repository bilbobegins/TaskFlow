package com.bilbobegins1997.TaskFlow.repository;

import com.bilbobegins1997.TaskFlow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
