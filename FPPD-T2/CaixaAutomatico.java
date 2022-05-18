import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface CaixaAutomatico extends Remote {
    boolean deposito(BigDecimal saldo, String id) throws RemoteException, MalformedURLException, NotBoundException;
    boolean saque(BigDecimal saldo, String id) throws RemoteException, MalformedURLException, NotBoundException;
    BigDecimal consulta(String id) throws RemoteException, MalformedURLException, NotBoundException;
}
