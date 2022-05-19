import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

public class AdministradorImpl extends UnicastRemoteObject implements Administrador {

    private Map<String, Conta> contas = new HashMap<>();
    private Map<String, Integer> requestIdList = new HashMap<>();

    protected AdministradorImpl() throws RemoteException {
    }

    @Override
    public void criarConta(String usuario, String senha, String requestId) throws RemoteException {
        validateRequestId(requestId);
        ofNullable(contas.get(usuario))
                .ifPresentOrElse(c -> {
                    throw new RuntimeException("Internal error: account with the requested id already exists");
                }, () -> {
                    contas.put(usuario, new Conta(usuario, senha, BigDecimal.ZERO));
                    System.out.println("Account open with success.");
                });

    }

    @Override
    public void removerConta(String id, String requestId, String token) throws RemoteException {
        validaAutenticacaoConta(id, token);
        validateRequestId(requestId);
        ofNullable(contas.get(id))
                .ifPresentOrElse(c -> {
                    contas.remove(id);
                    System.out.println("Account removed with success.");
                }, () -> {
                    throw new RuntimeException("Internal error: account with the request id doesn't exists");
                });
    }

    @Override
    public String autenticar(String usuario, String senha, String requestId) throws RemoteException {
        validateRequestId(requestId);
        var conta = contas.get(usuario);

        if (isNull(conta) || !conta.getSenha().equals(senha)) {
            throw new RuntimeException("Credenciais inválidas");
        }

        var token = UUID.randomUUID().toString();
        conta.addToken(token);

        return token;
    }

    @Override
    public String gerarRequestId() {
        var token = UUID.randomUUID().toString();
        this.requestIdList.put(token, 0);

        return token;
    }

    @Override
    public boolean deposito(BigDecimal saldo, String id, String requestId, String token) throws RemoteException {
        validateRequestId(requestId);
        validaAutenticacaoConta(id, token);
        var contaAutenticada = contas.get(id);

        if (saldo.compareTo(BigDecimal.ZERO) < 1) {
            return false;
        }
        contaAutenticada.setSaldo(contaAutenticada.getSaldo().add(saldo));
        return true;
    }

    @Override
    public boolean saque(BigDecimal saldo, String id, String requestId, String token) throws RemoteException {
        validateRequestId(requestId);
        validaAutenticacaoConta(id, token);
        var contaAutenticada = contas.get(id);

        if (saldo.compareTo(contaAutenticada.getSaldo()) < 0) {
            return false;
        }
        contaAutenticada.setSaldo(contaAutenticada.getSaldo().subtract(saldo));
        return true;
    }

    @Override
    public BigDecimal consulta(String id, String requestId, String token) throws RemoteException {
        validateRequestId(requestId);
        validaAutenticacaoConta(id, token);
        return contas.get(id).getSaldo();
    }

    private void validateRequestId(String rqstId) {
        if (requestIdList.get(rqstId) > 1) {
            throw new RuntimeException("O request ja foi processado");
        }
        requestIdList.compute(rqstId, (k, v) -> v+1);
    }

    private void validaAutenticacaoConta(String id, String token) {
        if (!contas.get(id).getTokens().containsKey(token)) {
            throw new RuntimeException("Conta não está autenticada");
        }
    }
}
