package com.bilbobegins1997.TaskFlow.repository;

import com.bilbobegins1997.TaskFlow.entity.Task;
import com.bilbobegins1997.TaskFlow.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

}
