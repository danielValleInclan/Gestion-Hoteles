package com.gestionhoteles.model;

import com.gestionhoteles.model.repository.ClientRepository;

import java.util.ArrayList;

public class ClientModel {
    private ClientRepository clientRepository;

    public ArrayList<ClientVO> listClientVO() throws ExceptionClient{
        return this.clientRepository.GetListClienteVO();
    }

    //public void addClientVO(Client client)

    public void setClientRepository(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }
}
