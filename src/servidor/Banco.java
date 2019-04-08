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
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Banco extends UnicastRemoteObject
                    implements MethodBanco{
    private ArrayList<String> tabla= new ArrayList<String>();
    Banco() throws java.rmi.RemoteException{
        super();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Banco consultaBanco;
            LocateRegistry.createRegistry(1099);
            consultaBanco = new Banco();
            consultaBanco.tablaCotizaciones();
            Naming.bind("ConsultaBanco", consultaBanco);
            System.out.println("El servidor esta listo\n");
        } catch (MalformedURLException | AlreadyBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String cotizarDolar(String fecha)  {
        String cotizacionDia;
        String[] dato;
        String valor = "";
        for (int i = 0; i < tabla.size(); i++) {
            cotizacionDia= tabla.get(i);
            dato = cotizacionDia.split(",");
            if(fecha.equals(dato[0])){
                valor = dato[1];
            }else{
                valor="0";
            }
        }
        return valor;
    }
    public void tablaCotizaciones(){
        tabla.add("26-06-19,6.90");
        tabla.add("27-06-19,6.91");
        tabla.add("28-06-19,6.93");
        tabla.add("29-06-19,6.92");
        tabla.add("30-06-19,6.96");       
    }
    
}
