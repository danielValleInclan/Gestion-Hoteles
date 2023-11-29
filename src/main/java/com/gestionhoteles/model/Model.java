package com.gestionhoteles.model;

import com.gestionhoteles.model.repository.Repository;
import com.gestionhoteles.util.Converter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class Model {
    private Repository repository;
    private IntegerProperty numBooking = new SimpleIntegerProperty();
    private IntegerProperty numBookingDouble = new SimpleIntegerProperty();

    public IntegerProperty getNumBooking(){
        return this.numBooking;
    }

    public void getNumDouble() throws ExeptionBooking {
        for (BookingVO bookingVO : repository.GetListBookingVO()){
            if (bookingVO.getStringTRoom().equals("DOUBLE")){
                numBookingDouble.set(numBookingDouble.get()+1);
            }
        }
    }

    public void setNumBooking(Integer num){
        this.numBooking.set(num);
    }

    public void incNumBooking(){
        this.numBooking.set(numBooking.get()+1);
    }

    public void decNumBooking(){
        this.numBooking.set(numBooking.get()-1);
    }

    public void addBookingVO(BookingVO bookingVO) throws ExeptionBooking {
        repository.addBookingVO(bookingVO);
    }

    public void editBookingVO(BookingVO bookingVO) throws ExeptionBooking {
        repository.editBookingVO(bookingVO, bookingVO.getId());
    }

    public void deleteBookingVO(int id) throws ExeptionBooking {
        repository.deleteBookingVO(id);
    }
    public void addClienteVO(ClientVO clientVO) throws ExceptionClient {
        repository.addClienteVO(clientVO);
    }

    /*
    public void deleteClienteVO(Client client) throws ExceptionClient {
        repository.deleteClienteVO(client.getDni());
    }
     */

    public void editClienteVO(ClientVO clientVO, String dni) throws ExceptionClient {
        repository.editClienteVO(clientVO, dni);
    }

    public ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient {
        return repository.GetListClienteVO();
    }


    public ArrayList<BookingVO> GetListBookingVO() throws ExeptionBooking {
        return this.repository.GetListBookingVO();
    }

    public ArrayList<BookingVO> GetListBooking_Client(String dni) throws ExeptionBooking {
        return this.repository.GetListBookingVO_Client(dni);
    }

    public int getLastIdBooking() throws ExeptionBooking {
        return GetListBookingVO().getLast().getId();
    }

    public void setRepository(Repository repository){
        this.repository = repository;
    }
}
