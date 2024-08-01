import random # Biblioteca necessária para gerar números aleatórios
import statistics # Biblioteca utilizada para calcular as operações aritméticas utilizadas no código
import time # Biblioteca utilizada para monitorar o tempo de uso
import psutil # Biblioteca utilizada para monitorar o uso de processador, disco e memória

def listaAleatoria(tamanho): # Função que gera cinco listas aleatórias, com valores de 1 a 100

  lista_aleatoria = []
  for _ in range(1, 10):
    lista_aleatoria.append(random.randint(1, 100))
  return lista_aleatoria

def calcularEstatisticas(lista):

  Média = statistics.mean(lista) # Calcula a media da lista
  Moda = statistics.mode(lista) # Calcula a moda da lista
  Mediana = statistics.median(lista) # Calcula a mediana da lista
  DesvioPadrão = statistics.stdev(lista) # Calcula o desvio padrão da lista
  
  print("----------------------------------------------")
  print(f"\tEstátistica da lista {i}\t")
  print(f"Média da lista: {Média:.2f}")
  print(f"Moda da lista: {Moda}")
  print(f"Mediana da lista: {Mediana}") 
  print(f"Desvio Padrão da lista: {DesvioPadrão:.2f}")
  print("----------------------------------------------")

def Benchmark(funcao, tamanho_lista):

  tempoTotal = 0
  for _ in range(5):
        inicio = time.time()
        funcao(tamanho_lista)
        fim = time.time()
        total = fim - inicio
        tempoTotal += total
  TempoMedio = tempoTotal / 5
  
  UsoCPU = psutil.cpu_percent()
  UsoMemoria = psutil.virtual_memory()
  UsoDisco = psutil.disk_usage("/")
  print(f"Uso de disco: {UsoDisco.percent}%")
  print(f"Uso da CPU: {UsoCPU}%")
  print(f"Uso de memória: {UsoMemoria.percent}%") 
  print(f"Tempo médio de geração de listas: {TempoMedio:.5f} segundos")

# Gera 5 listas aleatórias
for i in range(1, 6):
  lista_aleatoria = listaAleatoria(10)
  print(f"Lista {i}: {lista_aleatoria}")
  estatisticas = calcularEstatisticas(lista_aleatoria)

# Mede tempo, CPU, memória e disco para geração de listas
estatisticas_tempo = Benchmark(listaAleatoria, 10)
