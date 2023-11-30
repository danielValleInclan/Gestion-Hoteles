package com.gestionhoteles.model;

import com.gestionhoteles.model.repository.Repository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que representa el modelo de la aplicación.
 */
public class Model {
    private Repository repository;
    private IntegerProperty numBookingDouble = new SimpleIntegerProperty();
    private IntegerProperty numBookingDoubleInd = new SimpleIntegerProperty();
    private IntegerProperty numBookingJunior = new SimpleIntegerProperty();
    private IntegerProperty numBookingSuite = new SimpleIntegerProperty();

    /**
     * Obtiene el número de reservas de habitaciones dobles.
     * @return Número de reservas de habitaciones dobles.
     * @throws ExeptionBooking Si hay un error al obtener las reservas.
     */
    public IntegerProperty getNumBookingDoubles() throws ExeptionBooking {
        // Inicializa la propiedad a 0
        numBookingDouble.set(0);

        // Recorre la lista de reservas y cuenta las de tipo DOUBLE que son válidas
        for (BookingVO bookingVO : repository.GetListBookingVO()){
            if (bookingVO.getStringTRoom().equals("DOUBLE") && isDateValid(bookingVO)){
                numBookingDouble.set(numBookingDouble.get()+1);
            }
        }
        return numBookingDouble;
    }

    /**
     * Obtiene el número de reservas de habitaciones dobles individuales.
     * @return Número de reservas de habitaciones dobles individuales.
     * @throws ExeptionBooking Si hay un error al obtener las reservas.
     */
    public IntegerProperty getNumBookingDoubleInd() throws ExeptionBooking {
        numBookingDoubleInd.set(0);

        for (BookingVO bookingVO : repository.GetListBookingVO()){
            if (bookingVO.getStringTRoom().equals("DOUBLE_SIGLE_USE") && isDateValid(bookingVO)){
                numBookingDoubleInd.set(numBookingDoubleInd.get()+1);
            }
        }
        return numBookingDoubleInd;
    }

    /**
     * Obtiene el número de reservas de suites junior.
     * @return Número de reservas de suites junior.
     * @throws ExeptionBooking Si hay un error al obtener las reservas.
     */
    public IntegerProperty getNumBookingJunior() throws ExeptionBooking {
        numBookingJunior.set(0);

        for (BookingVO bookingVO : repository.GetListBookingVO()){
            if (bookingVO.getStringTRoom().equals("JUNIOR_SUITE") && isDateValid(bookingVO)){
                numBookingJunior.set(numBookingJunior.get()+1);
            }
        }
        return numBookingJunior;
    }

    /**
     * Obtiene el número de reservas de suites.
     * @return Número de reservas de suites.
     * @throws ExeptionBooking Si hay un error al obtener las reservas.
     */
    public IntegerProperty getNumBookingSuite() throws ExeptionBooking {
        numBookingSuite.set(0);

        for (BookingVO bookingVO : repository.GetListBookingVO()){
            if (bookingVO.getStringTRoom().equals("SUITE") && isDateValid(bookingVO)){
                numBookingSuite.set(numBookingSuite.get()+1);
            }
        }
        return numBookingSuite;
    }

    /**
     * Verifica si una reserva es válida en función de sus fechas.
     * @param bookingVO La reserva a verificar.
     * @return `true` si la reserva es válida, `false` de lo contrario.
     */
    private boolean isDateValid(BookingVO bookingVO){
        return (bookingVO.getDepartureDate().isAfter(LocalDate.now())
                || bookingVO.getDepartureDate().isEqual(LocalDate.now()))
                && (bookingVO.getArrivalDate().isBefore(LocalDate.now())
                || bookingVO.getArrivalDate().isEqual(LocalDate.now()));
    }

    /**
     * Agrega una nueva reserva al repositorio.
     * @param bookingVO La reserva a agregar.
     * @throws ExeptionBooking Si hay un error al agregar la reserva.
     */
    public void addBookingVO(BookingVO bookingVO) throws ExeptionBooking {
        repository.addBookingVO(bookingVO);
    }

    /**
     * Edita una reserva existente en el repositorio.
     * @param bookingVO La reserva actualizada.
     * @throws ExeptionBooking Si hay un error al editar la reserva.
     */
    public void editBookingVO(BookingVO bookingVO) throws ExeptionBooking {
        repository.editBookingVO(bookingVO, bookingVO.getId());
    }

    /**
     * Elimina una reserva del repositorio.
     * @param id El identificador de la reserva a eliminar.
     * @throws ExeptionBooking Si hay un error al eliminar la reserva.
     */
    public void deleteBookingVO(int id) throws ExeptionBooking {
        repository.deleteBookingVO(id);
    }

    /**
     * Agrega un nuevo cliente al repositorio.
     * @param clientVO El cliente a agregar.
     * @throws ExceptionClient Si hay un error al agregar el cliente.
     */
    public void addClienteVO(ClientVO clientVO) throws ExceptionClient {
        repository.addClienteVO(clientVO);
    }

    /**
     * Edita un cliente existente en el repositorio.
     * @param clientVO El cliente actualizado.
     * @param dni El DNI del cliente a editar.
     * @throws ExceptionClient Si hay un error al editar el cliente.
     */
    public void editClienteVO(ClientVO clientVO, String dni) throws ExceptionClient {
        repository.editClienteVO(clientVO, dni);
    }

    /**
     * Obtiene la lista de clientes del repositorio.
     * @return Lista de clientes.
     * @throws ExceptionClient Si hay un error al obtener la lista de clientes.
     */
    public ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient {
        return repository.GetListClienteVO();
    }

    /**
     * Obtiene la lista de todas las reservas del repositorio.
     * @return Lista de reservas.
     * @throws ExeptionBooking Si hay un error al obtener la lista de reservas.
     */
    public ArrayList<BookingVO> GetListBookingVO() throws ExeptionBooking {
        return this.repository.GetListBookingVO();
    }

    /**
     * Obtiene la lista de reservas de un cliente específico.
     * @param dni El DNI del cliente.
     * @return Lista de reservas del cliente.
     * @throws ExeptionBooking Si hay un error al obtener la lista de reservas del cliente.
     */
    public ArrayList<BookingVO> GetListBooking_Client(String dni) throws ExeptionBooking {
        return this.repository.GetListBookingVO_Client(dni);
    }

    /**
     * Obtiene el último identificador de reserva.
     * @return El último identificador de reserva.
     * @throws ExeptionBooking Si hay un error al obtener el último identificador.
     */
    public int getLastIdBooking() throws ExeptionBooking {
        return GetListBookingVO().getLast().getId();
    }

    /**
     * Establece el repositorio para el modelo.
     * @param repository El repositorio a establecer.
     */
    public void setRepository(Repository repository){
        this.repository = repository;
    }
}
