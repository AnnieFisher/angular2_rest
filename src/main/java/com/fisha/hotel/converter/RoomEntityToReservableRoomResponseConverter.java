package com.fisha.hotel.converter;

import com.fisha.hotel.entity.RoomEntity;
import com.fisha.hotel.model.Links;
import com.fisha.hotel.model.Self;
import com.fisha.hotel.model.response.ReservableRoomResponse;
import com.fisha.hotel.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;


public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {

        ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
        if(null != source.getId())
            reservationResponse.setId(source.getId());
        reservationResponse.setRoomNumber(source.getRoomNumber());
        reservationResponse.setPrice( Integer.valueOf(source.getPrice()) );


        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);

        reservationResponse.setLinks(links);

        return reservationResponse;
    }
}
