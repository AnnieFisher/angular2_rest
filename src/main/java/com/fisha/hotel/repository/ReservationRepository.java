package com.fisha.hotel.repository;

import com.fisha.hotel.entity.ReservationEntity;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {


}
