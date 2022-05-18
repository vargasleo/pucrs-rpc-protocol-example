import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;

public class AgenciaClient extends UnicastRemoteObject{

    private static String contaAutenticada;

    protected AgenciaClient() throws RemoteException {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");
            var caixaAutomatico = (CaixaAutomatico) Naming.lookup("rmi://localhost:1099/CaixaService");

            System.out.println("1 - Abre Conta");
            System.out.println("2 - Fecha Conta");
            System.out.println("3 - Depositar Saldo");
            System.out.println("4 - Sacar saldo");
            System.out.println("5 - Consultar saldo");
            System.out.println("0 - sair");
            boolean exec = true;
            double result;
            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 1:
                        try {
                            System.out.println("Favor, digite uma senha:");
                            in.nextLine();
                            var senha = in.nextLine();
                            var id = UUID.randomUUID().toString();
                            administrador.criarConta(UUID.randomUUID().toString(), senha);
                            contaAutenticada = id;
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 2:
                        try {
                            administrador.removerConta(contaAutenticada.toString());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Informe o quanto deseja depositar:");
                            var saldo = in.nextBigDecimal();
                            caixaAutomatico.deposito(saldo, contaAutenticada.toString());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Informe o quanto deseja sacar:");
                            var saldo = in.nextBigDecimal();
                            caixaAutomatico.saque(saldo, contaAutenticada.toString());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 5:
                        try {
                            caixaAutomatico.consulta(contaAutenticada.toString());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 0:
                        exec = false;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @Override
    public void abreConta(String id) {
        ofNullable(contas.get(id))
                .ifPresentOrElse(c -> {
                    throw new RuntimeException("Internal error: account with the requested id already exists");
                }, () -> {
                    contas.put(id, BigDecimal.ZERO);
                    System.out.println("Account open with success.");
                });
    }

    @Override
    public void fechaConta(String id) {
        ofNullable(contas.get(id))
                .ifPresentOrElse(c -> {
                    contas.remove(id);
                    System.out.println("Account removed with success.");
                }, () -> {
                    throw new RuntimeException("Internal error: account with the request id doesn't exists");
                });
    }

 */

    public void autenticar(String id) {
    }

}
