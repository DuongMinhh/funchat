package com.duongminh.funchat.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.duongminh.funchat.core.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT r.* FROM public.room r "
            + "     INNER JOIN public.room_member rm ON r.id = rm.room_id "
            + "     WHERE rm.user_id = :memberId", nativeQuery = true)
    List<Room> findAllByMemberId(Long memberId);
    
    @Query(value = "SELECT "
            + "     CASE WHEN (SELECT 1 FROM public.room_member rm INNER JOIN public.room r ON rm.room_id = r.id WHERE r.name = :name AND rm.user_id = :userId) = 1 THEN TRUE "
            + "     ELSE FALSE "
            + "     END", nativeQuery = true)
    Boolean existsByNameAndUserId(String name, Long userId);

}
