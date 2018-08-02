package com.fisha.hotel.rest;

import com.fisha.hotel.entity.ReservationEntity;
import com.fisha.hotel.entity.RoomEntity;
import com.fisha.hotel.model.request.ReservationRequest;
import com.fisha.hotel.model.response.ReservableRoomResponse;
import com.fisha.hotel.model.response.ReservationResponse;
import com.fisha.hotel.repository.PageableRoomRepository;
import com.fisha.hotel.repository.ReservationRepository;
import com.fisha.hotel.repository.RoomRepository;
import com.fisha.hotel.service.ReservablePageService;
import com.fisha.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

    @Autowired
    PageableRoomRepository pageableRoomRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ConversionService conversionService;

    @Autowired
    ReservablePageService reservablePageService;

    @Autowired
    RoomService roomService;


    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(name = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(name = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout) {

        List<ReservableRoomResponse> reservables = reservablePageService.responseOfReservable();

        return reservables;
    }


    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {

        RoomEntity roomEntity = roomRepository.findById(roomId).orElse(null);

        return new ResponseEntity<>(roomEntity, HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);

        RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId()).orElse(null);
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);
        reservationEntity.setRoomEntity(roomEntity);

        ReservationResponse reservationResponse =
                conversionService.convert(reservationEntity, ReservationResponse.class);


        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {

        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
