from colorama import Fore, Style, init

# Inicializa o colorama para garantir que as cores sejam resetadas após cada impressão
init(autoreset=True)

class Processador:
    def __init__(self):
        # Inicializa a memória com 256 posições e os registradores com valores padrão
        self.memoria = [0] * 256
        self.registradores = {
            "PC": 0,  # Contador de programa
            "IR": None,  # Registro de instrução
            "MAR": 0,  # Registro de endereço de memória
            "MBR": 0  # Registro de buffer de memória
        }
        # Inicializa as flags de status (Zero e Negativo)
        self.flags = {
            "Z": False,
            "N": False
        }
        # Lista de instruções a ser carregada no processador
        self.instrucoes = []

    def carregarPrograma(self, programa):
        """Carrega o programa no processador."""
        self.instrucoes = programa

    def buscarInstrucao(self):
        """Busca a próxima instrução no programa e atualiza o PC e MAR."""
        if self.registradores["PC"] < len(self.instrucoes):
            self.registradores["MAR"] = self.registradores["PC"]
            self.registradores["IR"] = self.instrucoes[self.registradores["PC"]]
            self.registradores["PC"] += 1

    def decodificar(self):
        """Decodifica a instrução atual em um objeto de instrução correspondente."""
        if self.registradores["IR"]:
            opcode, operandos = self.registradores["IR"]
            classeInstrucao = mapa_opcodes.get(opcode)
            if classeInstrucao:
                return classeInstrucao(opcode, operandos)
        return None

    def executarInstrucao(self, instrucao):
        """Executa a instrução decodificada e atualiza as flags."""
        if instrucao:
            instrucao.executar(self)
            self.atualizarFlags()

    def atualizarFlags(self):
        """Atualiza as flags de status (Zero e Negativo) com base no valor do MBR."""
        self.flags["Z"] = (self.registradores["MBR"] == 0)
        self.flags["N"] = (self.registradores["MBR"] < 0)

    def exibirCiclo(self):
        """Exibe o ciclo atual de execução da instrução com detalhes."""
        if self.registradores["PC"] > 0 and self.registradores["PC"] <= len(self.instrucoes):
            instrucao = self.instrucoes[self.registradores["PC"] - 1]
            opcode = f"{instrucao[0]:06b}"
            operandos = instrucao[1]
            op1 = operandos[0] if len(operandos) > 0 else ""
            op2 = operandos[1] if len(operandos) > 1 else ""

            print(Fore.CYAN + "="*82 + Style.RESET_ALL)
            print(Fore.YELLOW + "CÁLCULO DO ENDEREÇO DA INSTRUÇÃO:" + Style.RESET_ALL)
            print(f"PC: {Fore.GREEN}{self.registradores['PC']:06d}{Style.RESET_ALL}")
            print("\n" + Fore.YELLOW + "BUSCANDO A INSTRUÇÃO:" + Style.RESET_ALL)
            print(f"IR <OPCODE>: {Fore.GREEN}{opcode}{Style.RESET_ALL}")
            print(f"IR <OP1>: {Fore.GREEN}{op1}{Style.RESET_ALL}")
            print(f"IR <OP2>: {Fore.GREEN}{op2}{Style.RESET_ALL}")
            print("\n" + Fore.YELLOW + "DECODIFICANDO A INSTRUÇÃO:" + Style.RESET_ALL)

            # Exibe a execução detalhada baseada no opcode
            if opcode == "000001":
                print(Fore.CYAN + "MBR <- #POS" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{self.registradores['MBR']} <- {op1}{Style.RESET_ALL}")
            elif opcode == "000010":
                print(Fore.CYAN + "#POS <- #DADO" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{op1} <- {op2}{Style.RESET_ALL}")
            elif opcode == "000011":
                print(Fore.CYAN + "MBR <- MBR + #POS" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{self.registradores['MBR']} <- {self.registradores['MBR']} + {op1}{Style.RESET_ALL}")
            elif opcode == "000100":
                print(Fore.CYAN + "MBR <- MBR - #POS" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{self.registradores['MBR']} <- {self.registradores['MBR']} - {op1}{Style.RESET_ALL}")
            elif opcode == "000101":
                print(Fore.CYAN + "MBR <- MBR * #POS" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{self.registradores['MBR']} <- {self.registradores['MBR']} * {op1}{Style.RESET_ALL}")
            elif opcode == "000110":
                print(Fore.CYAN + "MBR <- MBR / #POS" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{self.registradores['MBR']} <- {self.registradores['MBR']} / {op1}{Style.RESET_ALL}")
            elif opcode == "000111":
                print(Fore.CYAN + "JUMP to #LIN" + Style.RESET_ALL)
                print(f"{Fore.GREEN}JUMP to {op1}{Style.RESET_ALL}")
            elif opcode == "001000":
                print(Fore.CYAN + "JUMP IF Z to #LIN" + Style.RESET_ALL)
                print(f"{Fore.GREEN}JUMP IF Z to {op1}{Style.RESET_ALL}")
            elif opcode == "001001":
                print(Fore.CYAN + "JUMP IF N to #LIN" + Style.RESET_ALL)
                print(f"{Fore.GREEN}JUMP IF N to {op1}{Style.RESET_ALL}")
            elif opcode == "001010":
                print(Fore.CYAN + "MBR <- sqrt(MBR)" + Style.RESET_ALL)
                print(f"{Fore.GREEN}MBR <- sqrt({self.registradores['MBR']}){Style.RESET_ALL}")
            elif opcode == "001011":
                print(Fore.CYAN + "MBR <- -MBR" + Style.RESET_ALL)
                print(f"{Fore.GREEN}MBR <- -{self.registradores['MBR']}{Style.RESET_ALL}")
            elif opcode == "001111":
                print(Fore.CYAN + "#POS <- MBR" + Style.RESET_ALL)
                print(f"{Fore.GREEN}{op1} <- {self.registradores['MBR']}{Style.RESET_ALL}")
            elif opcode == "001100":
                print(Fore.CYAN + "NOP" + Style.RESET_ALL)
                print(Fore.GREEN + "ENCERRANDO OPERAÇÃO DE DADOS" + Style.RESET_ALL)
                print(Fore.GREEN + "OPERAÇÃO FINALIZADA!" + Style.RESET_ALL)
                return
            else:
                print(Fore.RED + "OPCODE não reconhecido" + Style.RESET_ALL)

            print("\n" + Fore.YELLOW + "CÁLCULO DO ENDEREÇO DO OPERANDO:" + Style.RESET_ALL)
            print(f"Endereço: {Fore.GREEN}{op1}{Style.RESET_ALL}")
            print("\n" + Fore.YELLOW + "BUSCANDO O OPERANDO NA POSIÇÃO:" + Style.RESET_ALL)
            print(f"MAR: {Fore.GREEN}{op1}{Style.RESET_ALL}")

            if op2:
                print("\n" + Fore.YELLOW + "CÁLCULO DO ENDEREÇO DO SEGUNDO OPERANDO:" + Style.RESET_ALL)
                print(f"Endereço: {Fore.GREEN}{op2}{Style.RESET_ALL}")
                print("\n" + Fore.YELLOW + "BUSCANDO O SEGUNDO OPERANDO NA POSIÇÃO:" + Style.RESET_ALL)
                print(f"MAR: {Fore.GREEN}{op2}{Style.RESET_ALL}")

            print("\n" + Fore.YELLOW + "OPERAÇÃO DE DADOS:" + Style.RESET_ALL)

            # Exibe os resultados da operação de dados baseada no opcode
            if opcode == "000001":
                print(f"VALOR DO MBR: {Fore.GREEN}{self.registradores['MBR']}{Style.RESET_ALL}")
                print(f"VALOR NA MEMÓRIA: {Fore.GREEN}{self.memoria[int(op1)]}{Style.RESET_ALL}")
                print(f"VALOR DO MBR APÓS A OPERAÇÃO: {Fore.GREEN}{self.registradores['MBR']} + {self.memoria[int(op1)]} = {self.registradores['MBR'] + self.memoria[int(op1)]}{Style.RESET_ALL}")
                print(Fore.GREEN + "O VALOR FOI ARMAZENADO!" + Style.RESET_ALL)
            elif opcode == "000010":
                print(f"VALOR DO MBR: {Fore.GREEN}{self.registradores['MBR']}{Style.RESET_ALL}")
                print(f"VALOR NA POSIÇÃO: {Fore.GREEN}{self.memoria[int(op1)]}{Style.RESET_ALL}")
                print(f"VALOR DO MBR APÓS A OPERAÇÃO: {Fore.GREEN}{self.registradores['MBR']} + {self.memoria[int(op1)]} = {self.registradores['MBR'] + self.memoria[int(op1)]}{Style.RESET_ALL}")
                print(Fore.GREEN + "O VALOR FOI ARMAZENADO!" + Style.RESET_ALL)
            elif opcode in ["000011", "000100", "000101", "000110", "000111", "001000", "001001", "001010", "001011"]:
                print(f"ARMAZENANDO: {Fore.GREEN}{op2}{Style.RESET_ALL}")
                print(f"NA POSIÇÃO: {Fore.GREEN}{op1}{Style.RESET_ALL}")
                print("\n" + Fore.YELLOW + "CALCULANDO ENDEREÇO DO OPERANDO:" + Style.RESET_ALL)
                print(f"ENDEREÇO: {Fore.GREEN}{op1}{Style.RESET_ALL}")
                print("\n" + Fore.YELLOW + "ARMAZENANDO O OPERANDO:" + Style.RESET_ALL)
                print(f"MAR: {Fore.GREEN}{op1}{Style.RESET_ALL}")
                print(Fore.GREEN + "O VALOR FOI ARMAZENADO!" + Style.RESET_ALL)

            print(Fore.CYAN + "="*82 + Style.RESET_ALL)
        else:
            print(Fore.RED + "Instrução inválida ou fora do intervalo." + Style.RESET_ALL)

    def exibirRegistradores(self):
        """Exibe os valores atuais dos registradores e das flags."""
        print(f"PC: {Fore.GREEN}{self.registradores['PC']}{Style.RESET_ALL}")
        print(f"IR: {Fore.GREEN}{self.registradores['IR']}{Style.RESET_ALL}")
        print(f"MAR: {Fore.GREEN}{self.registradores['MAR']}{Style.RESET_ALL}")
        print(f"MBR: {Fore.GREEN}{self.registradores['MBR']}{Style.RESET_ALL}")
        print(f"Flags: {Fore.GREEN}{self.flags}{Style.RESET_ALL}")

class Instrução:
    def __init__(self, opcode, operandos):
        """Inicializa uma instrução com opcode e operandos."""
        self.opcode = opcode
        self.operandos = operandos

    def executar(self, processador):
        """Método a ser implementado pelas subclasses para executar a instrução."""
        pass

class InstruçãoInserir(Instrução):
    def executar(self, processador):
        """Executa a instrução de inserção, atualizando o MBR com o primeiro operando."""
        processador.registradores["MBR"] = self.operandos[0]

class InstruçãoVerInstrucao(Instrução):
    def executar(self, processador):
        """Executa a instrução de visualização dos registradores."""
        processador.exibirRegistradores()

class InstruçãoExecutar(Instrução):
    def executar(self, processador):
        """Executa a instrução de execução, buscando, decodificando e executando a próxima instrução."""
        processador.buscarInstrucao()
        instrucao = processador.decodificar()
        processador.exibirCiclo()
        processador.executarInstrucao(instrucao)

# Mapeia os códigos de operação (opcodes) para as classes de instrução correspondentes
mapa_opcodes = {
    1: InstruçãoInserir,
    2: InstruçãoVerInstrucao,
    3: InstruçãoExecutar
}

class ControlaCicloInstrucao:
    def __init__(self):
        """Inicializa o controlador do ciclo de instrução com um processador e uma lista de instruções vazia."""
        self.processador = Processador()
        self.programa = []

    def entradaUsuario(self):
        """Permite ao usuário inserir instruções e operandos no programa."""
        print(Fore.YELLOW + "Digite as instruções do programa (ou '4' para sair da inserção de dados):" + Style.RESET_ALL)
        while True:
            instrucao = input("\nDigite o código da instrução (em binário): ")
            if instrucao == '4':
                break

            op1 = ""
            op2 = ""

            if instrucao in ["000001", "001010", "001011", "001100"]:
                op1 = input("Digite o primeiro operando: ")
            else:
                op1 = input("Digite o primeiro operando: ")
                if instrucao not in ["000011", "000100", "000101", "000110", "000111", "001000", "001001", "001111"]:
                    op2 = input("Digite o segundo operando: ")

            # Verifica se os operandos são válidos e adiciona-os ao programa
            try:
                operandos = [int(op1)] if op1 else []
                if op2:
                    operandos.append(int(op2))
                opcode = int(instrucao, 2)
                self.programa.append((opcode, operandos))
            except ValueError:
                print(Fore.RED + "Operando inválido! Por favor, insira um número inteiro." + Style.RESET_ALL)

        self.processador.carregarPrograma(self.programa)

    def verInstrucoes(self):
        """Exibe as instruções carregadas no programa."""
        print(Fore.YELLOW + "Instruções carregadas:" + Style.RESET_ALL)
        for idx, instrucao in enumerate(self.processador.instrucoes):
            opcode, operandos = instrucao
            print(f"Instrução {idx + 1}: Opcode {Fore.GREEN}{opcode:06b}{Style.RESET_ALL}, Operandos {Fore.GREEN}{operandos}{Style.RESET_ALL}")

    def executarTodasInstrucoes(self):
        """Executa todas as instruções no programa até que o PC atinja o final das instruções."""
        while self.processador.registradores["PC"] < len(self.processador.instrucoes):
            self.processador.buscarInstrucao()
            instrucao = self.processador.decodificar()
            if instrucao:
                self.processador.exibirCiclo()
                self.processador.executarInstrucao(instrucao)
            else:
                print(Fore.RED + "Erro ao decodificar a instrução." + Style.RESET_ALL)
                break
        print(Fore.GREEN + "Execução completa." + Style.RESET_ALL)
        self.processador.exibirRegistradores()

def usarCicloInstrucao():
    """Função principal para controlar o ciclo de instrução com opções para o usuário."""
    ci = ControlaCicloInstrucao()
    while True:
        print(Fore.CYAN + "="*20 + Style.RESET_ALL)
        print(Fore.YELLOW + "OPÇÕES:" + Style.RESET_ALL)
        print(Fore.CYAN + "="*20 + Style.RESET_ALL)
        print("1. INSERIR")
        print("2. VER INSTRUÇÕES")
        print("3. EXECUTAR")
        print("4. SAIR DO PROGRAMA")
        print(Fore.CYAN + "="*20 + Style.RESET_ALL)
        try:
            opcao = int(input("Escolha uma opção: "))
        except ValueError:
            print(Fore.RED + "Opção inválida! Por favor, insira um número inteiro." + Style.RESET_ALL)
            continue

        if opcao == 1:
            ci.entradaUsuario()
        elif opcao == 2:
            ci.verInstrucoes()
        elif opcao == 3:
            ci.executarTodasInstrucoes()
        elif opcao == 4:
            print(Fore.RED + "Encerrando!" + Style.RESET_ALL)
            break
        else:
            print(Fore.RED + "Opção inválida!" + Style.RESET_ALL)

if __name__ == "__main__":
    usarCicloInstrucao()
