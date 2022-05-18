import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class AdministradorServer {
    public static void admServer(){
        try {
            //Definicao do ip onde o servico ira funcionar
            System.setProperty("java.rmi.server.hostname", "localhost");
            //Registro do servico em uma porta
            LocateRegistry.createRegistry(1099);
            //Cria o objeto que implementa os metodos que serao servidos
            Administrador a = new AdministradorImpl();
            //Coloca na porta registrada o servico da calculadora
            Naming.bind("AdmService", a);
            System.out.println("Conexao estabelecida.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        admServer();
    }
}
