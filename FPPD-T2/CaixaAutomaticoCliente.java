import java.rmi.Naming;
import java.util.Scanner;

public class CaixaAutomaticoCliente {

    private static String contaAutenticada;
    private static String token;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");

            System.out.println("1 - Autenticar");
            System.out.println("2 - Depositar Saldo");
            System.out.println("3 - Sacar saldo");
            System.out.println("4 - Consultar saldo");
            System.out.println("0 - sair");
            boolean exec = true;
            double result;

            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 1:
                        try {
                            in.nextLine();
                            System.out.println("Digite seu usuario:");
                            contaAutenticada = in.nextLine();
                            System.out.println("Digite sua senha:");
                            var senha = in.nextLine();
                            token = administrador.autenticar(contaAutenticada, senha, administrador.gerarRequestId());
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Informe o quanto deseja depositar:");
                            var saldo = in.nextBigDecimal();
                            administrador.deposito(saldo, contaAutenticada, administrador.gerarRequestId(), token);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Informe o quanto deseja sacar:");
                            var saldo = in.nextBigDecimal();
                            administrador.saque(saldo, contaAutenticada, administrador.gerarRequestId(), token);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Seu saldo atual é:" + administrador.consulta(contaAutenticada, administrador.gerarRequestId(), token));
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
