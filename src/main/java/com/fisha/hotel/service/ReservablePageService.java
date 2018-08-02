package com.fisha.hotel.service;

import com.fisha.hotel.converter.RoomEntityToReservableRoomResponseConverter;
import com.fisha.hotel.entity.RoomEntity;
import com.fisha.hotel.model.response.ReservableRoomResponse;
import com.fisha.hotel.repository.PageableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservablePageService {

    @Autowired
    PageableRoomRepository pageableRoomRepository;

    public List<ReservableRoomResponse> responseOfReservable() {
        PageRequest request = PageRequest.of(0, Integer.MAX_VALUE);
        List<RoomEntity> roomList = findAll(request);
        RoomEntityToReservableRoomResponseConverter converter = new RoomEntityToReservableRoomResponseConverter();
        List<ReservableRoomResponse> reservableRooms = new ArrayList<>();
        for (RoomEntity room : roomList) {
            reservableRooms.add(converter.convert(room));
        }

        return reservableRooms;
    }


    public List<RoomEntity> findAll(PageRequest request) {
        Page<RoomEntity> roomsPage = pageableRoomRepository.findAll(request);
        return roomsPage.getContent();
    }
}
