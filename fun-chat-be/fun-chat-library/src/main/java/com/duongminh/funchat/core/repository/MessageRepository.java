package com.duongminh.funchat.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duongminh.funchat.core.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM public.message WHERE room_id = :roomId ORDER BY created_at ASC", nativeQuery = true)
    List<Message> findAllByRoomId(Long roomId);
    
}
