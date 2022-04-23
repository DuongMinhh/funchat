package com.duongminh.funchat.core.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duongminh.funchat.core.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM {h-schema}message WHERE room_id = :roomId", nativeQuery = true)
    List<Message> findAllByRoomId(Long roomId, Pageable pageable);
    
    @Query(value = "SELECT * FROM {h-schema}message WHERE room_id = :roomId", nativeQuery = true)
    List<Message> findAllByRoomId(Long roomId);
    
    @Query(value = "SELECT COUNT(*) FROM {h-schema}message WHERE room_id = :roomId", nativeQuery = true)
    Long countByRoomId(Long roomId);
    
}
