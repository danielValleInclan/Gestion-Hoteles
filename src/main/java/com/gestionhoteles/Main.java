package com.gestionhoteles;

import com.gestionhoteles.model.*;
import com.gestionhoteles.model.repository.Repository;
import com.gestionhoteles.model.repository.impl.RepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ExceptionClient {
        Repository repository = new RepositoryImpl();
        Model model = new Model();
        model.setClientRepository(repository);
        ArrayList<ClientVO> listaClientVO;
        ArrayList<BookingVO> bookingVOArrayList;
        model.addClienteVO(new ClientVO("12345678A", "daniel", "rd", "Alcala",
                "Sevilla", "Sevilla"));
        model.editClienteVO(new ClientVO("12345678A", "daniel2", "rd2", "Alcala",
                "Sevilla", "Sevilla"), "12345678A");
        listaClientVO = model.GetListClienteVO();
        for (ClientVO c : listaClientVO){
            System.out.println(c.getName());
        }
        bookingVOArrayList = model.GetListBookingVO();
        bookingVOArrayList.size();
        model.addBookingVO(new BookingVO(1, 1, false, LocalDate.of(2023, 7, 5),
                LocalDate.of(2023, 8, 5), "12345678A", TypeRoom.DOUBLE, Regime.FULL_BOARD));
        model.editBookingVO(new BookingVO(1, 1, false, LocalDate.of(2023, 7, 5),
                LocalDate.of(2023, 8, 5), "12345678A", TypeRoom.DOUBLE, Regime.BED_BREAKFAST), 1);
        model.deleteBookingVO(1);
    }
}
