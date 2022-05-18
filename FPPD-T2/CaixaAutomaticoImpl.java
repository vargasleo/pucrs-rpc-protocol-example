import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CaixaAutomaticoImpl extends UnicastRemoteObject implements CaixaAutomatico {

    protected CaixaAutomaticoImpl() throws RemoteException {

    }

    @Override
    public boolean deposito(BigDecimal saldo, String id) throws RemoteException, MalformedURLException, NotBoundException {
        var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");
        var contaAutenticada = administrador.buscaConta(id);

        if (saldo.compareTo(BigDecimal.ZERO) < 1) {
            return false;
        }
        contaAutenticada.setSaldo(contaAutenticada.getSaldo().add(saldo));
        return true;
    }

    @Override
    public boolean saque(BigDecimal saldo, String id) throws RemoteException, MalformedURLException, NotBoundException {
        var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");
        var contaAutenticada = administrador.buscaConta(id);

        if (saldo.compareTo(contaAutenticada.getSaldo()) < 0) {
            return false;
        }
        contaAutenticada.setSaldo(contaAutenticada.getSaldo().subtract(saldo));
        return true;
    }

    @Override
    public BigDecimal consulta(String id) throws RemoteException, MalformedURLException, NotBoundException {
        var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");
        return  administrador.buscaConta(id).getSaldo();
    }
}
