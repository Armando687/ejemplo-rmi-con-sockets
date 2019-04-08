/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author jose
 */
public class Server extends UnicastRemoteObject implements MethodServer{
    
    Server() throws java.rmi.RemoteException{
        super();
    }
    public static void main(String args[]){
        try {
            Server hotel;
            LocateRegistry.createRegistry(1099);
            hotel = new Server();
            Naming.bind("Hotel", hotel);
             System.out.println("El servidor esta listo\n");
        } catch (MalformedURLException | AlreadyBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public String Cotizar(String inicio, String fin, String fechaInicio) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Reservar(String inicio, String fin, String idCliente, String fechaDeCompra) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
