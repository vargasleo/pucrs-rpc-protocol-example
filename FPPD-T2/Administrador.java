import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Administrador extends Remote {
    void criarConta(String id, String senha) throws RemoteException;
    void removerConta(String id) throws RemoteException;
    Conta buscaConta(String id) throws RemoteException;
}
