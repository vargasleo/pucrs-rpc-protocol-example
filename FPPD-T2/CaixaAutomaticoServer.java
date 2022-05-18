import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CaixaAutomaticoServer {
    public static void caixaAutomaticoServer(){
        try {
            //Definicao do ip onde o servico ira funcionar
            System.setProperty("java.rmi.server.hostname", "localhost");
            //Registro do servico em uma porta
            LocateRegistry.createRegistry(1098);
            //Cria o objeto que implementa os metodos que serao servidos
            CaixaAutomatico c = new CaixaAutomaticoImpl();
            //Coloca na porta registrada o servico da calculadora
            Naming.bind("CaixaService", c);
            System.out.println("Conexao estabelecida.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        caixaAutomaticoServer();
    }
}
