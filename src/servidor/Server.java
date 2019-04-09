/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author jose
 */
public class Server extends UnicastRemoteObject implements MethodServer{
    private ArrayList<String> precios = new ArrayList<String>();
    Server() throws java.rmi.RemoteException{
        super();
    }
    public static void main(String args[]){
        try {
            Server hotel;
            LocateRegistry.createRegistry(1099);
            hotel = new Server();
            hotel.tablaPrecios();
            Naming.bind("Hotel", hotel);
             System.out.println("El servidor esta listo\n");
        } catch (MalformedURLException | AlreadyBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public String Cotizar(String inicio, String fin, String fechaInicio){
        MethodBanco consultaBanco;
        String dato;
        String[] precioDia;
        double valorDolar ;
        double monto = 0.0;
        try {
            
            consultaBanco = (MethodBanco) Naming.lookup("rmi://localhost/ConsultaBanco");
            int fechaFinal = 0; // para calcular la el intervalo
            int fInicio = 0;
            for (int i = 0; i < precios.size(); i++) {
                    dato = precios.get(i);
                    precioDia = dato.split(",");//fecha precio[0]
                if(fin.equals(precioDia[0])){
                    fechaFinal = i;
                }else{
                    System.err.println("La fecha final no es valida");
                }
                if(inicio.equals(precioDia[0])){
                    fInicio = i;
                }else{
                    System.err.println("La fecha de Inicio no es valida");
                }
            }
            //calculamos el monto
            for (int i = fInicio; i < fechaFinal; i++) {
                dato = precios.get(i);
                precioDia = dato.split(",");//fecha precio[0], valor precio[1]
                valorDolar = consultaBanco.cotizarDolar(precioDia[0]);
                
                if(valorDolar != 0 ){
                    monto +=  valorDolar * Double.parseDouble(precioDia[1]);
                }
            }
            
        
        }catch (MalformedURLException | NotBoundException | RemoteException e) {
            System.err.println(e.getMessage());
        }
        return Double.toString(monto);    
    }

    @Override
    public String Reservar(String inicio, String fin, String idCliente, String fechaDeCompra){
        int port = 2027; // puerto de comunicacion
        String result = "";
        try{
            Socket client = new Socket("localhost", port); //conectarse al socket
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toServer.println(idCliente);  //mandar alservidor 
            result = fromServer.readLine();  // devolver del servidor
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
    public void tablaPrecios(){
        precios.add("26-06-19,30");
        precios.add("27-06-19,25");
        precios.add("28-06-19,25");
        precios.add("29-06-19,35");
        precios.add("30-06-19,40");
    }
    
}
