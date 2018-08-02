package com.fisha.hotel.repository;

import com.fisha.hotel.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    Optional<RoomEntity> findById(Long id);
}
