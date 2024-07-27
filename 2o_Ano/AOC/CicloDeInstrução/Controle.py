class Processador:
    def __init__(self):
        self.memoria = [0] * 256
        self.registradores = {
            "PC": 0,
            "IR": None,
            "MAR": 0,
            "MBR": 0
        }
        self.flags = {
            "Z": False,
            "N": False
        }
        self.instrucoes = []

    def carregarPrograma(self, programa):
        self.instrucoes = programa

    def buscarInstrucao(self):
        if self.registradores["PC"] < len(self.instrucoes):
            self.registradores["MAR"] = self.registradores["PC"]
            self.registradores["IR"] = self.instrucoes[self.registradores["PC"]]
            self.registradores["PC"] += 1

    def decodificar(self):
        if self.registradores["IR"]:
            opcode, operandos = self.registradores["IR"]
            classeInstrucao = mapa_opcodes.get(opcode)
            if classeInstrucao:
                return classeInstrucao(*operandos)
        return None

    def executarPrograma(self):
        instrucao = self.decodificar()
        if instrucao:
            instrucao.executar(self)
            self.atualizarFlags()
            self.exibirRegistradores()

    def atualizarFlags(self):
        self.flags["Z"] = (self.registradores["MBR"] == 0)
        self.flags["N"] = (self.registradores["MBR"] < 0)

    def exibirCiclo(self):
        if self.registradores["PC"] > 0 and self.registradores["PC"] <= len(self.instrucoes):
            instrucao = self.instrucoes[self.registradores["PC"] - 1]
            opcode = f"{instrucao[0]:06b}"
            operandos = instrucao[1]
            op1 = operandos[0] if len(operandos) > 0 else ""
            op2 = operandos[1] if len(operandos) > 1 else ""

            print("="*82)
            print("CÁLCULO DO ENDEREÇO DA INSTRUÇÃO:")
            print(f"PC: {self.registradores['PC']:06d}")
            print("\nBUSCANDO A INSTRUÇÃO:")
            print(f"IR <OPCODE>: {opcode}")
            print(f"IR <OP1>: {op1}")
            print(f"IR <OP2>: {op2}")
            print("\nDECODIFICANDO A INSTRUÇÃO:")

            if opcode == "000001":
                print("MBR <- #POS")
                print(f"{self.registradores['MBR']} <- {op1}")
            elif opcode == "000010":
                print("#POS <- #DADO")
                print(f"{op1} <- {op2}")
            elif opcode == "000011":
                print("MBR <- MBR + #POS")
                print(f"{self.registradores['MBR']} <- {self.registradores['MBR']} + {op1}")
            elif opcode == "000100":
                print("MBR <- MBR - #POS")
                print(f"{self.registradores['MBR']} <- {self.registradores['MBR']} - {op1}")
            elif opcode == "000101":
                print("MBR <- MBR * #POS")
                print(f"{self.registradores['MBR']} <- {self.registradores['MBR']} * {op1}")
            elif opcode == "000110":
                print("MBR <- MBR / #POS")
                print(f"{self.registradores['MBR']} <- {self.registradores['MBR']} / {op1}")
            elif opcode == "000111":
                print("JUMP to #LIN")
                print(f"JUMP to {op1}")
            elif opcode == "001000":
                print("JUMP IF Z to #LIN")
                print(f"JUMP IF Z to {op1}")
            elif opcode == "001001":
                print("JUMP IF N to #LIN")
                print(f"JUMP IF N to {op1}")
            elif opcode == "001010":
                print("MBR <- sqrt(MBR)")
                print(f"MBR <- sqrt({self.registradores['MBR']})")
            elif opcode == "001011":
                print("MBR <- -MBR")
                print(f"MBR <- -{self.registradores['MBR']}")
            elif opcode == "001111":
                print("#POS <- MBR")
                print(f"{op1} <- {self.registradores['MBR']}")
            elif opcode == "001100":
                print("NOP")
                print("ENCERRANDO OPERAÇÃO DE DADOS")
                print("OPERAÇÃO FINALIZADA!")
                return
            else:
                print("OPCODE não reconhecido")

            print("\nCÁLCULO DO ENDEREÇO DO OPERANDO:")
            print(f"Endereço: {op1}")
            print("\nBUSCANDO O OPERANDO NA POSIÇÃO:")
            print(f"MAR: {op1}")

            if op2:
                print("\nCÁLCULO DO ENDEREÇO DO SEGUNDO OPERANDO:")
                print(f"Endereço: {op2}")
                print("\nBUSCANDO O SEGUNDO OPERANDO NA POSIÇÃO:")
                print(f"MAR: {op2}")

            print("\nOPERAÇÃO DE DADOS:")

            if opcode == "000001":
                print(f"VALOR DO MBR: {self.registradores['MBR']}")
                print(f"VALOR NA MEMÓRIA: {self.memoria[int(op1)]}")
                print(f"VALOR DO MBR APÓS A OPERAÇÃO: {self.registradores['MBR']} + {self.memoria[int(op1)]} = {self.registradores['MBR'] + self.memoria[int(op1)]}")
                print("O VALOR FOI ARMAZENADO!")
            elif opcode == "001111":
                print(f"VALOR DO MBR: {self.registradores['MBR']}")
                print(f"VALOR DO ENDEREÇO APÓS A OPERAÇÃO: {op1}")
                print("O VALOR FOI ARMAZENADO!")
            elif opcode == "000011":
                print(f"VALOR DO MBR: {self.registradores['MBR']}")
                print(f"VALOR DO CONTEÚDO NA POSIÇÃO: {self.memoria[int(op1)]}")
                print(f"VALOR DO MBR APÓS A OPERAÇÃO: {self.registradores['MBR']} + {self.memoria[int(op1)]} = {self.registradores['MBR'] + self.memoria[int(op1)]}")
                print("O VALOR FOI ARMAZENADO!")
            elif opcode in ["000010", "000100", "000101", "000110", "000111", "001000", "001001", "001010", "001011"]:
                print(f"ARMAZENANDO: {op2}")
                print(f"NA POSIÇÃO: {op1}")
                print("\nCALCULANDO ENDEREÇO DO OPERANDO:")
                print(f"ENDEREÇO: {op1}")
                print("\nARMAZENANDO O OPERANDO:")
                print(f"MAR: {op1}")
                print("O VALOR FOI ARMAZENADO!")

            print("="*82)
        else:
            print("Instrução inválida ou fora do intervalo.")

    def exibirRegistradores(self):
        print(f"PC: {self.registradores['PC']}")
        print(f"IR: {self.registradores['IR']}")
        print(f"MAR: {self.registradores['MAR']}")
        print(f"MBR: {self.registradores['MBR']}")
        print(f"Flags: {self.flags}")

class Instrução:
    def __init__(self, opcode, operandos):
        self.opcode = opcode
        self.operandos = operandos

    def executar(self, processador):
        pass

class InstruçãoInserir(Instrução):
    def executar(self, processador):
        processador.registradores["MBR"] = self.operandos[0]

class InstruçãoVerInstrucao(Instrução):
    def executar(self, processador):
        processador.exibirRegistradores()

class InstruçãoExecutar(Instrução):
    def executar(self, processador):
        processador.buscarInstrucao()
        processador.executarPrograma()

mapa_opcodes = {
    1: InstruçãoInserir,
    2: InstruçãoVerInstrucao,
    3: InstruçãoExecutar
}

class ControlaCicloInstrucao:
    def __init__(self):
        self.processador = Processador()
        self.programa = []

    def entradaUsuario(self):
        print("Digite as instruções do programa (ou '4' para sair da inserção de dados):")
        while True:
            instrucao = input("\nDigite o código da instrução (em binário): ")
            if instrucao == '4':
                break

            op1 = ""
            op2 = ""

            if instrucao not in ["000001", "001010", "001011", "001100"]:
                op1 = input("Digite o primeiro operando: ")
                if instrucao in ["000011", "000100", "000101", "000110", "000111", "001000", "001001"]:
                    op2 = input("Digite o segundo operando: ")

            operandos = [int(op1)] if op1 else []
            if op2:
                operandos.append(int(op2))
            opcode = int(instrucao, 2)
            self.programa.append((opcode, operandos))

        self.processador.carregarPrograma(self.programa)

    def verInstrucoes(self):
        print("Instruções carregadas:")
        for idx, instrucao in enumerate(self.processador.instrucoes):
            opcode, operandos = instrucao
            print(f"Instrução {idx + 1}: Opcode {opcode:06b}, Operandos {operandos}")

    def executarTodasInstrucoes(self):
        while self.processador.registradores["PC"] < len(self.processador.instrucoes):
            self.processador.exibirCiclo()
            self.processador.buscarInstrucao()
            self.processador.executarPrograma()
        print("Execução completa.")
        self.processador.exibirRegistradores()

def usarCicloInstrucao():
    ci = ControlaCicloInstrucao()
    while True:
        print("="*82)
        print("OPÇÕES:")
        print("="*82)
        print("1. INSERIR")
        print("2. VER INSTRUÇÕES")
        print("3. EXECUTAR")
        print("4. SAIR DO PROGRAMA")
        print("="*82)
        opcao = int(input("Escolha uma opção: "))

        if opcao == 1:
            ci.entradaUsuario()
        elif opcao == 2:
            ci.verInstrucoes()
        elif opcao == 3:
            ci.executarTodasInstrucoes()
        elif opcao == 4:
            print("Encerrando!")
            break
        else:
            print("Opção inválida!")

if __name__ == "__main__":
    usarCicloInstrucao()
