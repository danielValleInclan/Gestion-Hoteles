package com.gestionhoteles.model.repository;

import com.gestionhoteles.model.ClientVO;
import com.gestionhoteles.model.ExceptionClient;

import java.util.ArrayList;

public interface ClientRepository {
    ArrayList<ClientVO> GetListClienteVO() throws ExceptionClient;

    void addClienteVO(ClientVO var1) throws ExceptionClient;

    void deleteClienteVO(Integer var1) throws ExceptionClient;

    void editClienteVO(ClientVO var1, Integer id) throws ExceptionClient;

}
