package com.gestionhoteles.model;

import com.gestionhoteles.model.repository.Repository;

import java.util.ArrayList;

public class Model {
    private Repository repository;

    public void addBookingVO(BookingVO bookingVO){
        repository.addBookingVO(bookingVO);
    }

    public void editBookingVO(BookingVO bookingVO, int id){
        repository.editBookingVO(bookingVO, id);
    }

    public void deleteBookingVO(int id){
        repository.deleteBookingVO(id);
    }
    public void addClienteVO(ClientVO clientVO) throws ExceptionClient {
        repository.addClienteVO(clientVO);
    }

    public void deleteClienteVO(String dni) throws ExceptionClient {
        repository.deleteClienteVO(dni);
    }

    public void editClienteVO(ClientVO clientVO, String dni) throws ExceptionClient {
        repository.editClienteVO(clientVO, dni);
    }

    public ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient {
        return repository.GetListClienteVO();
    }


    public ArrayList<BookingVO> GetListBookingVO() throws RuntimeException{
        return this.repository.GetListBookingVO();
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }
}
