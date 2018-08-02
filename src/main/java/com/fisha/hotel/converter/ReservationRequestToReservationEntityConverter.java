package com.fisha.hotel.converter;

import com.fisha.hotel.entity.ReservationEntity;
import com.fisha.hotel.model.request.ReservationRequest;
import com.fisha.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {

    @Autowired
    RoomRepository roomRepository;

    @Override
    public ReservationEntity convert(ReservationRequest source) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());
        if (null != source.getId()){
            reservationEntity.setId(source.getId());
        }

        return reservationEntity;
    }
}
