package com.gestionhoteles;

import com.gestionhoteles.model.ClientVO;
import com.gestionhoteles.model.ExceptionClient;
import com.gestionhoteles.model.repository.ClientRepository;
import com.gestionhoteles.model.repository.impl.ClientRepositoryImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ExceptionClient {
        ClientRepository clientRepository = new ClientRepositoryImpl();
        ArrayList<ClientVO> listaClientVO = clientRepository.GetListClienteVO();
        clientRepository.addClienteVO(new ClientVO("12345678A", "Daniel", "Rodríguez",
                "Duq Talavera", "Alcala de Guadaira", "Sevilla"));
        System.out.println(listaClientVO);
    }
}
