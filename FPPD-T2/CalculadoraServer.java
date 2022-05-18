/**
 *  Servidor calculadora
 */

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraServer {
    public static void calculadoraServer(){
        try {
            //Definicao do ip onde o servico ira funcionar
            System.setProperty("java.rmi.server.hostname", "localhost");
            //Registro do servico em uma porta
            LocateRegistry.createRegistry(1097);
            //Cria o objeto que implementa os metodos que serao servidos
            Calculadora c = new CalculadoraImp();
            //Coloca na porta registrada o servico da calculadora
            Naming.bind("CalcService", c);
            System.out.println("Conexao estabelecida.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         calculadoraServer();
    }
}
