/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author jose
 */
public interface MethodServer extends Remote{
    
    String Cotizar(String inicio, String fin, String fechaInicio) throws RemoteException;
    String Reservar(String inicio, String fin, String idCliente, String fechaDeCompra) throws RemoteException;
            
}
