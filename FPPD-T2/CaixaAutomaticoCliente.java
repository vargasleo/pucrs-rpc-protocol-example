import java.rmi.Naming;
import java.util.Scanner;

public class CaixaAutomaticoCliente {

    private static String contaAutenticada;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            //Procura pelo servico da calculadora no IP e porta definidos
            var administrador = (Administrador) Naming.lookup("rmi://localhost:1099/AdmService");
            var caixaAutomatico = (CaixaAutomatico) Naming.lookup("rmi://localhost:1099/CaixaService");

            System.out.println("1 - Depositar Saldo");
            System.out.println("2 - Sacar saldo");
            System.out.println("3 - Consultar saldo");
            System.out.println("0 - sair");
            boolean exec = true;
            double result;

            while (contaAutenticada == null) {
                try {
                    System.out.println("Log in:");
                    contaAutenticada = in.nextLine();
                    if (administrador.buscaConta(contaAutenticada) == null) {
                        contaAutenticada = null;
                    };
                } catch (Exception e) {
                    System.out.println("Erro ao buscar conta.");
                }
            }
            while (exec) {

                int key = in.nextInt();
                switch (key) {
                    case 3:
                        try {
                            System.out.println("Informe o quanto deseja depositar:");
                            var saldo = in.nextBigDecimal();
                            caixaAutomatico.deposito(saldo, contaAutenticada);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Informe o quanto deseja sacar:");
                            var saldo = in.nextBigDecimal();
                            caixaAutomatico.saque(saldo, contaAutenticada);
                            System.out.println("Criado com sucesso");
                        } catch (Exception e) {
                            System.out.println("Erro ao criar conta");
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Seu saldo atual é:" + caixaAutomatico.consulta(contaAutenticada));
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
