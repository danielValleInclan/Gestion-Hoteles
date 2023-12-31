package com.gestionhoteles.util;

import com.gestionhoteles.model.Booking;
import com.gestionhoteles.model.BookingVO;
import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.ClientVO;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Converter {

    public ArrayList<Client> convertListCVO(ArrayList<ClientVO> clientVOS){
        ArrayList<Client> clients = new ArrayList<>();
        for (ClientVO clientVO : clientVOS){
            clients.add(convertClientVO(clientVO));
        }
        return clients;
    }

    public ClientVO convertClient(Client client){
        return new ClientVO(
                client.getDni(),
                client.getName(),
                client.getLastName(),
                client.getAddres(),
                client.getTown(),
                client.getProvince()
        );
    }

    public Client convertClientVO(ClientVO clientVO){
        return new Client(
                clientVO.getDni(),
                clientVO.getName(),
                clientVO.getLastName(),
                clientVO.getAddress(),
                clientVO.getTown(),
                clientVO.getProvince()
        );
    }

    public ArrayList<Booking> convertListBVO(ArrayList<BookingVO> bookingVOS){
        ArrayList<Booking> bookings = new ArrayList<>();
        for (BookingVO bookingVO : bookingVOS){
            bookings.add(convertBookingVO(bookingVO));
        }
        return bookings;
    }

    public Booking convertBookingVO(BookingVO bookingVO){
        Booking booking = new Booking();
        booking.setId(String.valueOf(bookingVO.getId()));
        booking.setArrivalDate(String.valueOf(bookingVO.getArrivalDate()));
        booking.setDepartureDate(String.valueOf(bookingVO.getDepartureDate()));
        booking.setnRoom(String.valueOf(bookingVO.getnRoom()));
        booking.setTypeRoom(bookingVO.getStringTRoom());
        booking.setRegime(bookingVO.getStringRegime());
        booking.setClientDni(bookingVO.getClientDni());
        booking.setSmoke(String.valueOf(bookingVO.getSmoke()));
        return booking;
    }

    public BookingVO convertBooking(Booking booking){
        BookingVO bookingVO = new BookingVO();
        bookingVO.setArrivalDate(LocalDate.parse(booking.getArrivalDate()));
        bookingVO.setDepartureDate(LocalDate.parse(booking.getDepartureDate()));
        bookingVO.setnRoom(booking.getnRoom());
        bookingVO.setTypeRoom(booking.getTypeRoom());
        bookingVO.setRegime(booking.getRegime());
        bookingVO.setClientDni(booking.getClientDni());
        bookingVO.setSmoke(Boolean.parseBoolean(booking.getSmoke()));
        return bookingVO;
    }
}
