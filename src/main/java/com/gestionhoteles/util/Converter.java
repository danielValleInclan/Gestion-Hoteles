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
