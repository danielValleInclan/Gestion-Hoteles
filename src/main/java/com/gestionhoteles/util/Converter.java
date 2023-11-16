package com.gestionhoteles.util;

import com.gestionhoteles.model.Client;
import com.gestionhoteles.model.ClientVO;
import javafx.beans.property.StringProperty;

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
}
