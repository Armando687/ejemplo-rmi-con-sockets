/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import servidor.MethodServer;

/**
 *
 * @author jose
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String clienteId = "1";
        String inicio = "26-06-19";
        String fin = "30-06-19";
        String fechaIncio = "26-06-19";
        try {
            MethodServer hotel;
            hotel = (MethodServer) Naming.lookup("rmi://localhost/Hotel");
            System.err.println("Conectando........");
            System.out.println("Cotizando de " + inicio + " a "+ fin );
            System.out.println("Costo : " + hotel.Cotizar(inicio, fin, fechaIncio));
            System.err.println("..................");
            System.out.println("Haciendo Reserva " + inicio + " a "+ fin );
            System.out.println(hotel.Reservar(inicio, fin, clienteId, fechaIncio));
            
        } catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());;
        }
    }
    
}
