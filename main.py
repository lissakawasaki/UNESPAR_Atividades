def menu():
    print("="*30)
    print(
"""
1 - Inserir
2 - Ver Instruções
3 - Executar
4 - Sair
""")
    print("="*30)

class CicloInstrucao:
    def __init__(self):
        self.PC = 0
        self.MBR = 0
        self.flagZero = False
        self.flagNegativo = False
        self.memoria = [0]*256
        self.instrucoes = []

    def entrada_usuario(self):
        print("Digite as instruções do programa (4 para sair da inserção)")
        while True:
            instrucao = input("Digite o código da instrução: ")
            if instrucao == '4':
                break
            op1 = op2 = ""
            if instrucao in ["000001", "001010", "001011", "001100"]:
                # Para essas instruções, não pedimos o segundo operando
                op1 = input("Digite o primeiro operando: ")
            else:
                op1 = input("Digite o primeiro operando: ")
                if instrucao not in ["000011", "000100", "000101", "000110", "000111", "001000", "001001", "001111"]:
                    op2 = input("Digite o segundo operando: ")

            # Adiciona a instrução com os operandos apropriados
            if instrucao in ["000001", "001010", "001011", "001100"]:
                self.instrucoes.append(f"{instrucao} {op1}")
            else:
                self.instrucoes.append(f"{instrucao} {op1} {op2}")

    def ver_instrucoes(self):
        print("="*30)
        print("= INSTRUÇÕES: =")
        print("="*30)
        print(f"{'COD':<10} {'OP1':<10} {'OP2':<10} {'RESULTADOS':<25}")
        instrucoes_desc = {
            "000001": "MBR <- #POS",
            "000010": "POS <- #DADO",
            "000011": "MBR <- MBR + #POS",
            "000100": "MBR <- MBR - #POS",
            "000101": "MBR <- MBR * #POS",
            "000110": "MBR <- MBR / #POS",
            "000111": "JUMP to #LIN",
            "001000": "JUMP IF Z to #LIN",
            "001001": "JUMP IF N to #LIN",
            "001010": "MBR <- raiz_quadrada(MBR)",
            "001011": "MBR <- -MBR",
            "001111": "#POS <- MBR",
            "001100": "NOP"
        }
        for cod, desc in instrucoes_desc.items():
            print(f"{cod:<10} {'-':<10} {'-':<10} {desc:<25}")  # Formatação para uma tabela
        for instrucao in self.instrucoes:
            print(instrucao)

    def rodar_instrucoes(self):
        print("="*30)
        print("EXECUTANDO")
        print("="*30)
        while self.PC < len(self.instrucoes):
            instrucao = self.instrucoes[self.PC]
            self.executa_instrucoes(instrucao)
            self.exibe_ciclo()
            print()

    def executa_instrucoes(self, instrucao):
        componentes = instrucao.split()
        opcode = componentes[0]
        op1 = int(componentes[1]) if len(componentes) > 1 else None
        op2 = int(componentes[2]) if len(componentes) > 2 else None

        if opcode == "000001":
            self.inst000001(op1)
        elif opcode == "000010":
            self.inst000010(op1, op2)
        elif opcode == "000011":
            self.inst000011(op1)
        elif opcode == "000100":
            self.inst000100(op1)
        elif opcode == "000101":
            self.inst000101(op1)
        elif opcode == "000110":
            self.inst000110(op1)
        elif opcode == "000111":
            self.inst000111(op1)
        elif opcode == "001000":
            self.inst001000(op1)
        elif opcode == "001001":
            self.inst001001(op1)
        elif opcode == "001010":
            self.inst001010()
        elif opcode == "001011":
            self.inst001011()
        elif opcode == "001111":
            self.inst001111(op1)
        elif opcode == "001100":
            self.inst001100()
        else:
            print("Instrução inválida")

        self.PC += 1

    def exibe_ciclo(self):
        if self.PC <= 0:
            return
        
        componentes = self.instrucoes[self.PC - 1].split()
        opcode = componentes[0]
        op1 = componentes[1] if len(componentes) > 1 else ''
        op2 = componentes[2] if len(componentes) > 2 else ''
        
        print("="*30)
        print("CÁLCULO DO ENDEREÇO DA INSTRUÇÃO:")
        print(f"PC: {self.PC:06d}")  # Formatação para deixar 0's à esquerda
        print("\nBUSCANDO A INSTRUÇÃO:")
        print(f"<OPCODE>: {opcode}")
        print(f"<OP1>: {op1}")
        print(f"<OP2>: {op2}")
        print("\nDECODIFICANDO A INSTRUÇÃO:")
        
        print("OPERAÇÃO DE DADOS:")
        if opcode == "000001":
            print(f"MBR <- {op1}")
        elif opcode == "000010":
            print(f"{op1} <- {op2}")
        elif opcode == "000011":
            print(f"MBR <- MBR + {op1}")
        elif opcode == "000100":
            print(f"MBR <- MBR - {op1}")
        elif opcode == "000101":
            print(f"MBR <- MBR * {op1}")
        elif opcode == "000110":
            print(f"MBR <- MBR / {op1}")
        elif opcode == "000111":
            print(f"JUMP to {op1}")
        elif opcode == "001000":
            print(f"JUMP IF Z to {op1}")
        elif opcode == "001001":
            print(f"JUMP IF N to {op1}")
        elif opcode == "001010":
            print("MBR <- sqrt(MBR)")
        elif opcode == "001011":
            print("MBR <- -MBR")
        elif opcode == "001111":
            print(f"{op1} <- MBR")
        elif opcode == "001100":
            print("NOP")
            print("ENCERRANDO OPERAÇÃO DE DADOS")
            print("OPERAÇÃO FINALIZADA!")
            exit

        print("="*30)

    def inst000001(self, pos):
        if 0 <= pos < len(self.memoria):
            self.MBR = self.memoria[pos]
        else:
            print("Endereço fora dos limites da memória")
        self.atualiza_flags()

    def inst000010(self, pos, dado):
        if 0 <= pos < len(self.memoria):
            self.memoria[pos] = dado
        else:
            print("Endereço fora dos limites da memória")

    def inst000011(self, pos):
        if 0 <= pos < len(self.memoria):
            self.MBR += self.memoria[pos]
        else:
            print("Endereço fora dos limites da memória")
        self.atualiza_flags()

    def inst000100(self, pos):
        self.MBR -= pos
        self.atualiza_flags()

    def inst000101(self, pos):
        self.MBR *= pos
        self.atualiza_flags()

    def inst000110(self, pos):
        if pos != 0:
            self.MBR /= pos
            
        self.atualiza_flags()

    def inst000111(self, lin):
        if 0 <= lin < len(self.instrucoes):
            self.PC = lin
        else:
            print("Linha fora dos limites")

    def inst001000(self, lin):
        if self.flagZero:
            if 0 <= lin < len(self.instrucoes):
                self.PC = lin
            else:
                print("Linha fora dos limites")

    def inst001001(self, lin):
        if self.flagNegativo:
            if 0 <= lin < len(self.instrucoes):
                self.PC = lin
            else:
                print("Linha fora dos limites")

    def inst001010(self):
        self.MBR = int(self.MBR ** 0.5)
        self.atualiza_flags()

    def inst001011(self):
        self.MBR = -self.MBR
        self.atualiza_flags()

    def inst001111(self, pos):
        if 0 <= pos < len(self.memoria):
            self.memoria[pos] = self.MBR
        else:
            print("Endereço fora dos limites da memória")

    def inst001100(self):
        pass

    def atualiza_flags(self):
        self.flagZero = (self.MBR == 0)
        self.flagNegativo = (self.MBR < 0)

ci = CicloInstrucao()
while True:
    menu()
    opcao = int(input("Digite a opção: "))
    if opcao == 1:
        ci.entrada_usuario()
    elif opcao == 2:
        ci.ver_instrucoes()
    elif opcao == 3:
        ci.rodar_instrucoes()
    elif opcao == 4:
        print("Encerrando")
        break
    else:
        print("Opção inválida")
