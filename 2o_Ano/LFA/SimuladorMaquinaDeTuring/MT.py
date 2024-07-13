# Estruturas
estados = []  # Criando uma lista de estados
simbolos = []  # Criando uma lista de símbolos
simbolos_fita = []  # Criando uma lista de símbolos da fita (incluindo o símbolo branco)
funcao_Transicao = {} # Criando um dicionário

estado_inicial = ""
estados_finais = []

def executar_MT(estados, simbolos, simbolos_fita, funcao_Transicao, estado_inicial, estados_finais):
    while True:
        print("Insira a palavra a ser verificada: ", end="")
        entrada_usuario = list(input())  # A palavra é convertida em uma lista de caracteres

        # Validação de cada caractere da palavra
        if not all(char in simbolos for char in entrada_usuario):
            print("Palavra não aceita! Símbolos inválidos.")
            continue

        # Inicialização da fita e da cabeça de leitura/escrita
        fita = entrada_usuario + ["_"] * 100  # Adicionando espaço branco na fita
        posicao_cabeca = 0
        estado_atual = estado_inicial
        palavra_aceita = True  # Flag para verificar se a palavra foi aceita

        while True:
            simbolo_atual = fita[posicao_cabeca]
            transicao = funcao_Transicao.get((estado_atual, simbolo_atual))

            if transicao is None:  # Transição inválida
                print("Palavra não aceita! Transição inválida.")
                palavra_aceita = False
                break

            proximo_estado, simbolo_para_escrever, direcao = transicao

            # Escrevendo na fita e movendo a cabeça
            fita[posicao_cabeca] = simbolo_para_escrever
            if direcao == "R":
                posicao_cabeca += 1
            elif direcao == "L":
                posicao_cabeca -= 1

            estado_atual = proximo_estado

            if estado_atual in estados_finais:
                print("Palavra aceita!")
                break

        if palavra_aceita and estado_atual not in estados_finais:
            print("Palavra não aceita!")

# Recebendo os dados da Máquina de Turing

print("Informe o estado inicial: ", end="")
estado_inicial = input().strip()

print("Informe os estados finais (separados por espaço): ", end="")
estados_finais = input().split()  # A função split separa strings em substrings, retornando listas

print("Informe o conjunto de estados (separados por espaço): ", end="")
estados = input().split()
qtdEstados = len(estados)  # Registra a quantidade de estados listados

print("Informe os símbolos de entrada (separados por espaço): ", end="")
simbolos = input().split()
qtdSimbolos = len(simbolos)  # Registra a quantidade de símbolos listados

print("Informe os símbolos da fita (separados por espaço, incluindo o símbolo branco '_'): ", end="")
simbolos_fita = input().split()
qtdSimbolosFita = len(simbolos_fita)  # Registra a quantidade de símbolos da fita listados

print("Preencha as transições:")  # Preenche as transições
for estado in estados:
    for simbolo in simbolos_fita:
        print(f" δ ({estado},{simbolo}): ", end="")
        transicao = input().strip()  # A transição no formato: proximo_estado,símbolo_para_escrever,direcao

        if transicao.upper() == "X":
            funcao_Transicao[(estado, simbolo)] = None
        else:
            proximo_estado, simbolo_para_escrever, direcao = transicao.split(",")
            funcao_Transicao[(estado, simbolo)] = (proximo_estado, simbolo_para_escrever, direcao)

executar_MT(estados, simbolos, simbolos_fita, funcao_Transicao, estado_inicial, estados_finais)
