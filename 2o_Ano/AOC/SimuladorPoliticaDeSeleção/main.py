from EncaixeMemoria import EncaixeMemoria

blocoMemorias = []
tamanhoProcessos = []

print("Informe os blocos disponiveis (Separados por espaço.): ", end="")
blocoMemorias = input().split() 

print("Informe tamanho dos processos (Separados por espaço.): ", end="")
tamanhoProcessos = input().split() 

def prPurple(skk): print("\033[95m {}\033[00m" .format(skk))
prPurple("First Fit!")
Processo = EncaixeMemoria(blocoMemorias, tamanhoProcessos)
Processo.Firstfit()
Processo.mostrarResultados()
