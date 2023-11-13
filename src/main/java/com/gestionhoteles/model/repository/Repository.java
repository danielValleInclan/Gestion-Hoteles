package com.gestionhoteles.model.repository;

import com.gestionhoteles.model.BookingVO;
import com.gestionhoteles.model.ClientVO;
import com.gestionhoteles.model.ExceptionClient;

import java.util.ArrayList;

public interface Repository {
    ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient;

    ArrayList<BookingVO> GetListBookingVO() throws RuntimeException;
    void addClienteVO(ClientVO var1) throws ExceptionClient;

    void deleteClienteVO(String var1) throws ExceptionClient;

    void editClienteVO(ClientVO var1, String dni) throws ExceptionClient;

    void addBookingVO(BookingVO bookingVO);

    void deleteBookingVO(int id);

    void editBookingVO(BookingVO bookingVO, int id);

}
