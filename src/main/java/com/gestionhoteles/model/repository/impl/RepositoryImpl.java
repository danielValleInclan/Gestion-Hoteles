package com.gestionhoteles.model.repository.impl;

import com.gestionhoteles.model.*;
import com.gestionhoteles.model.repository.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class RepositoryImpl implements Repository {

    private final ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private Statement stmt;
    private String sentence;

    private ArrayList<ClientVO> clientVOS;
    private ArrayList<BookingVO> bookingVOS;
    private ClientVO clientVO;
    private BookingVO bookingVO;
    @Override
    public ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.clientVOS = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentence = "SELECT * FROM Client";
            ResultSet rs = this.stmt.executeQuery(this.sentence);

            while (rs.next()){
                String dni = rs.getString("dni");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String town = rs.getString("town");
                String province = rs.getString("province");
                this.clientVO = new ClientVO(dni, name, lastName, address, town, province);
                this.clientVOS.add(clientVO);
            }

            this.connectionJDBC.disconnectDB(conn);
            return this.clientVOS;
        } catch (SQLException e) {
            throw new ExceptionClient("No se ha podido realizar la conexión");
        }
    }

    @Override
    public ArrayList<BookingVO> GetListBookingVO() throws ExeptionBooking {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.bookingVOS = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentence = "SELECT * FROM Booking";
            ResultSet rs = this.stmt.executeQuery(this.sentence);

            while (rs.next()){
                int id = rs.getInt("id");
                LocalDate arrivalDate = rs.getDate("arrivalDate").toLocalDate();
                LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
                int nRoom = rs.getInt("nRoom");
                String typeRoom = rs.getString("typeRoom");
                boolean smoke = rs.getBoolean("smoke");
                String regime = rs.getString("regime");
                String clientDni = rs.getString("client");
                this.bookingVO = new BookingVO(id, nRoom, smoke, arrivalDate, departureDate, clientDni, TypeRoom.valueOf(typeRoom),
                        Regime.valueOf(regime));
                this.bookingVOS.add(bookingVO);
            }

            this.connectionJDBC.disconnectDB(conn);
            return this.bookingVOS;
        } catch (SQLException e) {
            throw new ExeptionBooking("No se ha podido realizar la conexión");
        }
    }

    @Override
    public void addClienteVO(ClientVO newClientVO) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            this.sentence = String.format("INSERT INTO Client (dni, name, lastName, address, town, province)" +
                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", newClientVO.getDni(), newClientVO.getName(),
                    newClientVO.getLastName(), newClientVO.getAddress(), newClientVO.getTown(), newClientVO.getProvince());
            this.stmt.executeUpdate(this.sentence);
            this.stmt.close();
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExceptionClient("No se ha podido realizar la operación");
        }
    }

    @Override
    public void addBookingVO(BookingVO bookingVO) throws ExeptionBooking {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            this.sentence = String.format("INSERT INTO Booking (arrivalDate, departureDate, nRoom, typeRoom, smoke," +
                            " regime, client) " +
                            "VALUES ('%s', '%s', %d, '%s', %b, '%s', '%s')",
                    bookingVO.getArrivalDate(),
                    bookingVO.getDepartureDate(),
                    bookingVO.getnRoom(),
                    bookingVO.getStringTRoom(),
                    bookingVO.getSmoke(),
                    bookingVO.getStringRegime(),
                    bookingVO.getClientDni());
            this.stmt.executeUpdate(this.sentence);
            this.stmt.close();
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExeptionBooking("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deleteClienteVO(String dniClient) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("DELETE FROM Client WHERE dni = '%s'", dniClient);
            command.executeUpdate(sql);
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExceptionClient("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deleteBookingVO(int id) throws ExeptionBooking{
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("DELETE FROM Booking WHERE id = '%d'", id);
            command.executeUpdate(sql);
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExeptionBooking("No se ha podido realizar la operación");
        }
    }

    @Override
    public void editClienteVO(ClientVO clientVO, String dni) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("UPDATE Client SET name = '%s', lastName = '%s', address = '%s', " +
                    "town = '%s', province = '%s' WHERE dni = '%s'",
                    clientVO.getName(),
                    clientVO.getLastName(),
                    clientVO.getAddress(),
                    clientVO.getTown(),
                    clientVO.getProvince(),
                    dni);
            command.executeUpdate(sql);

            command.close();
            this.stmt.close();
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExceptionClient("No se ha podido realizar la operación");
        }
    }

    @Override
    public void editBookingVO(BookingVO bookingVO, int id) throws ExeptionBooking  {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("UPDATE Booking SET id = %d, arrivalDate = '%s', departureDate = '%s', " +
                            "   nRoom = %d, typeRoom = '%s', smoke = %b, regime = '%s', client = '%s' WHERE id = %d",
                    bookingVO.getId(),
                    bookingVO.getArrivalDate(),
                    bookingVO.getDepartureDate(),
                    bookingVO.getnRoom(),
                    bookingVO.getStringTRoom(),
                    bookingVO.getSmoke(),
                    bookingVO.getStringRegime(),
                    bookingVO.getClientDni(),
                    id);
            command.executeUpdate(sql);
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExeptionBooking("No se ha podido realizar la operación");
        }
    }

    @Override
    public ArrayList<BookingVO> GetListBookingVO_Client(String dni) throws ExeptionBooking {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.bookingVOS = new ArrayList<>();
            this.stmt = conn.createStatement();
            this.sentence = String.format("SELECT * FROM Booking WHERE client = '%s' %n", dni);
            ResultSet rs = this.stmt.executeQuery(this.sentence);

            while (rs.next()){
                int id = rs.getInt("id");
                LocalDate arrivalDate = rs.getDate("arrivalDate").toLocalDate();
                LocalDate departureDate = rs.getDate("departureDate").toLocalDate();
                int nRoom = rs.getInt("nRoom");
                String typeRoom = rs.getString("typeRoom");
                boolean smoke = rs.getBoolean("smoke");
                String regime = rs.getString("regime");
                String clientDni = rs.getString("client");
                this.bookingVO = new BookingVO(id, nRoom, smoke, arrivalDate, departureDate, clientDni, TypeRoom.valueOf(typeRoom),
                        Regime.valueOf(regime));
                this.bookingVOS.add(bookingVO);
            }

            this.connectionJDBC.disconnectDB(conn);
            return this.bookingVOS;
        } catch (SQLException e) {
            throw new ExeptionBooking("No se ha podido realizar la conexión");
        }
    }
}
