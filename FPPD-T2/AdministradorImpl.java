import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

public class AdministradorImpl extends UnicastRemoteObject implements Administrador {

    Map<String, Conta> contas = new HashMap<>();

    protected AdministradorImpl() throws RemoteException {
    }

    @Override
    public void criarConta(String id, String senha) throws RemoteException {
        ofNullable(contas.get(id))
                .ifPresentOrElse(c -> {
                    throw new RuntimeException("Internal error: account with the requested id already exists");
                }, () -> {
                    contas.put(id, new Conta(id, senha, BigDecimal.ZERO));
                    System.out.println("Account open with success.");
                });

    }

    @Override
    public void removerConta(String id) throws RemoteException {
        ofNullable(contas.get(id))
                .ifPresentOrElse(c -> {
                    contas.remove(id);
                    System.out.println("Account removed with success.");
                }, () -> {
                    throw new RuntimeException("Internal error: account with the request id doesn't exists");
                });
    }

    @Override
    public Conta buscaConta(String id) throws RemoteException {
        return contas.get(id);
    }
}
