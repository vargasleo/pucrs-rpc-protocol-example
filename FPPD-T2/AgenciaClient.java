import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.UUID;

public class AgenciaClient {

    private static String contaAutenticada;
    private static String token;

    protected AgenciaClient() throws RemoteException {
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");

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
                            in.nextLine();
                            System.out.println("Favor, digite um usuario:");
                            var usuario = in.nextLine();
                            System.out.println("Favor, digite uma senha:");
                            var senha = in.nextLine();
                            administrador.criarConta(usuario, senha, administrador.gerarRequestId());
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta: usuario ja existe");
                        }
                        break;
                    case 2:
                        try {
                            administrador.removerConta(contaAutenticada, administrador.gerarRequestId(), token);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 3:
                        try {
                            in.nextLine();
                            System.out.println("Digite seu usuario:");
                            var usuario = in.nextLine();
                            System.out.println("Digite sua senha:");
                            var senha = in.nextLine();
                            token = administrador.autenticar(usuario, senha, administrador.gerarRequestId());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Informe o quanto deseja depositar:");
                            var saldo = in.nextBigDecimal();
                            administrador.deposito(saldo, contaAutenticada, administrador.gerarRequestId(), token);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Informe o quanto deseja sacar:");
                            var saldo = in.nextBigDecimal();
                            administrador.saque(saldo, contaAutenticada, administrador.gerarRequestId(), token);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 6:
                        try {
                            administrador.consulta(contaAutenticada, administrador.gerarRequestId(), token);
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
}
