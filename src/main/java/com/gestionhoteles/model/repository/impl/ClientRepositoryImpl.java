package com.gestionhoteles.model.repository.impl;

import com.gestionhoteles.model.ClientVO;
import com.gestionhoteles.model.ExceptionClient;
import com.gestionhoteles.model.repository.ClientRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClientRepositoryImpl implements ClientRepository {

    private final ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private Statement stmt;
    private String sentence;

    private ArrayList<ClientVO> clientVOS;
    private ClientVO clientVO;
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
    public void addClienteVO(ClientVO newClientVO) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            this.sentence = String.format("INSERT INTO Client (dni, name, lastName, address, town, province)" +
                    "VALUES ('%s', '%s', '%s', '%s', '%s')", newClientVO.getDni(), newClientVO.getName(),
                    newClientVO.getLastName(), newClientVO.getAddress(), newClientVO.getTown(), newClientVO.getProvince());
            this.stmt.executeUpdate(this.sentence);
            this.stmt.close();
            this.connectionJDBC.disconnectDB(conn);
        } catch (SQLException e) {
            throw new ExceptionClient("No se ha podido realizar la operación");
        }
    }

    @Override
    public void deleteClienteVO(Integer dniClient) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            Statement command = conn.createStatement();
            String sql = String.format("DELETE FROM Client WHERE dni = %s", dniClient);
            command.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("No se ha podido realizar la operación");
        }
    }

    @Override
    public void editClienteVO(ClientVO var1, Integer id) throws ExceptionClient {
        try {
            Connection conn = this.connectionJDBC.connectDB();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Client SET dni = '%s', name = '%s', lastName = '%s', address = '%s', " +
                    "town = '%s', province = '%s' WHERE dni = '%s", clientVO.getDni(), clientVO.getName(), clientVO.getLastName(),
                    clientVO.getAddress(), clientVO.getTown(), clientVO.getProvince(), clientVO.getDni());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
