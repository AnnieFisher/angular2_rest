package com.fisha.hotel.service;

import com.fisha.hotel.entity.ReservationEntity;
import com.fisha.hotel.entity.RoomEntity;
import com.fisha.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepository repository;

    public boolean isReservable(ReservationEntity reservation){
        boolean reserved = true;
        RoomEntity room = repository.findById(reservation.getRoomEntity().getId()).get();
        List<ReservationEntity> roomReservedList = room.getReservationEntityList();
        for(ReservationEntity e: roomReservedList){
            if(e.getCheckout().compareTo(reservation.getCheckin())<0){
                reserved = false;
            }
        }

        return reserved;
    }
}
