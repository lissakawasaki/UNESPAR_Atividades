class EncaixeMemoria():
    def __init__(self, blocosMemorias, tamanhoProcessos):
        self.blocoMemorias = list(map(int, blocosMemorias))  # Converte os elementos para inteiros
        self.tamanhoProcessos = list(map(int, tamanhoProcessos)) 
        self.alocacao = [-1] * len(tamanhoProcessos) # Cria uma lista com o mesmo número de elementos que tamanhoProcessos, e inicia cada elemento com -1 para indicar que nenhum processo foi alocado 
        pass

    def Firstfit(self):
        for i in range(len(self.tamanhoProcessos)):
            for j in range (len(self.blocoMemorias)):
                if self.blocoMemorias[j] >= self.tamanhoProcessos[i]: # Aloca o bloco de memória ao processo
                    self.alocacao[i] = j
                    self.blocoMemorias[j] -= self.tamanhoProcessos[i] # Reduz o tamanho disponível do bloco de memória
                    break

    def mostrarResultados(self):
        print("Processo No. \tTamanho Processo \tBloco Alocado")
        for i in range(len(self.tamanhoProcessos)):
            if self.alocacao[i] != -1:
                print(f"{i + 1} \t\t{self.tamanhoProcessos[i]} \t\t\t{self.alocacao[i] + 1}")
            else:
                print(f"{i + 1} \t\t{self.tamanhoProcessos[i]} \t\t\tNão Alocado")

