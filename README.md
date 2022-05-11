# Servidor RPC para sistema bancário

## Enunciado
Usar o código exemplo em Go para desenvolver um sistema distribuído baseado no modelo Cliente/Servidor utilizando RPC que simule a criação de contas de clientes e transações bancárias nestas contas (quem preferir pode usar outras linguagens, mas o protocolo tem que ser RPC/RMI). O sistema deve ter no mínimo 3 tipos de processos, administração, agência e caixa automático. Devem ser implementadas as seguintes funcionalidades:

Processo Administração: realiza abertura e fechamento de contas (para agências), autentica que contas já existem (tanto para agências e caixas automáticos) e também executa as operações de manipulação destas contas (saques e depósitos). Deve garantir semântica de execução exactely once para operações que sejam não-idempotentes;

Processo Agência: Solicita abertura, autenticação e fechamento de contas e também pode solicitar depósito, retirada e consulta de saldo em conta existente. A abertura de conta, o depósito e a retirada devem ser operações garantidamente não-idempotentes (semântica de execução exactely once);

Processo Caixa Automático: Solicita depósito, retirada e consulta de saldo em conta existente. As duas primeiras devem ser operações garantidamente não-idempotentes (semântica de execução exactely once) mesmo que ocorra algum erro na confirmação da operação (simular com injeção de falhas).

_A avaliação do trabalho será feita com base no acompanhamento do desenvolvimento do trabalho em laboratório/online e no envio de um relatório técnico no moodle._

Formato do relatório técnico:

- arquivo formato .pdf;
- cabeçalho reduzido com identificação do grupo e do trabalho;
- primeira página coluna dupla com margens reduzidas (2cm) e fonte 10;
- segunda página com dumps de tela mostrando interface e mensagens do programa em funcionamento;
- a partir da terceira página código fonte formatado em coluna simples (sem limite).

Dicas para formatar o código: copiar o código do VS Code e colar no Word, ele mantém toda a formatação.
Depois ajeitar o tamanho da fonte e o espaçamento entre as linhas pra economizar espaço. Outra opção é usar o overleaf que já importa o arquivo formatado. 

Foco do relatório:

- descrever como o problema foi mapeado no modelo cliente servidor
- descrever como foi implementado e testado o controle de idempotência
