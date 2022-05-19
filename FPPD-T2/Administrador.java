import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Administrador extends Remote {
    void criarConta(String usuario, String senha, String requestId) throws RemoteException;

    void removerConta(String id, String requestId, String token) throws RemoteException;
    String autenticar(String usuario, String senha, String requestId) throws RemoteException;

    boolean deposito(BigDecimal saldo, String id, String requestId, String token) throws RemoteException;

    boolean saque(BigDecimal saldo, String id, String requestId, String token) throws RemoteException;

    BigDecimal consulta(String id, String requestId, String token) throws RemoteException;
    String gerarRequestId() throws RemoteException;

}
