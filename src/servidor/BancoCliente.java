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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 *
 * @author jose armando Lopez Rodriguez
 */
public class BancoCliente {

    private final int port = 2027;
    private final int connection = 2;
    private ArrayList<String> users = new ArrayList<String>();

    /**
     * method to receive connections
     */
    public void toReceive() {
        try {
            ServerSocket server = new ServerSocket(port); //instanciamos un servidor socket
            Socket client;
            BufferedReader fromClient;  // buffer de lectura
            PrintStream toClient;       // stream para escritura
            while (true) {   // ciclo al infinito para elfuncionamiento del server
                client = server.accept(); // aceptala conexion
                fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String cadena = fromClient.readLine(); //cadena obtenida desde el lector
                cadena = Cuentas(cadena);
                toClient = new PrintStream(client.getOutputStream()); //prepara el objetopara devolver
                System.out.println(cadena); //imprime cadena recibida desde el cliente
                toClient.flush(); // 
                toClient.println(cadena);
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    /**
     * Lista de usuarios
     */
    public  void listaUser(){
        users.add("1,3000");
        users.add("2,4000");
        users.add("3,10000");
    }
    
    public  String Cuentas(String cadena){
        String lista;
        String resultado="";
        String[] cuenta;
        for (int i = 0; i < users.size(); i++) {
            lista = users.get(i);
            cuenta = lista.split(";");
            if(cadena.equals(cuenta[0])){
                resultado = lista;
            }else{
                resultado = "0";
            }
        }
        return resultado;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String[] args) {
        BancoCliente cliente = new  BancoCliente();
        cliente.listaUser();
        cliente.toReceive();
    }
    
}    



