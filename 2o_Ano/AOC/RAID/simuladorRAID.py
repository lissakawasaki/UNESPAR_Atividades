def main():
    # Função para calcular o XOR de dois ou três caracteres
    def xor_chars(a, b, c = None):
        if c is not None:  # Se c for fornecido, calcula o XOR de a, b e c
            return chr(ord(a) ^ ord(b) ^ ord(c))
        return chr(ord(a) ^ ord(b))  # Caso contrário, calcula o XOR de a e b

    # Solicita ao usuário que digite uma frase
    frase = input("Digite a frase: ")

    # RAID 0
    print("\nRAID 0: ")
    disco1 = []  # Lista para armazenar caracteres do disco 1
    disco2 = []  # Lista para armazenar caracteres do disco 2

    # Distribui os caracteres da frase entre disco1 e disco2 alternadamente
    for i in range(len(frase)):
        if i % 2 == 0:  # Índice par vai para o disco1
            disco1.append(frase[i])
        else:  # Índice ímpar vai para o disco2
            disco2.append(frase[i])
    
    # Exibe os conteúdos dos discos 1 e 2
    print("Disco 1:", ''.join(disco1))
    print("Disco 2:", ''.join(disco2))

    # RAID 1
    print("\nRAID 1: ")
    # RAID 1 é uma cópia idêntica da frase em dois discos
    print("Disco 1:", frase)
    print("Disco 2:", frase)

    # RAID 4
    disco1 = []  # Limpa a lista do disco 1
    disco2 = []  # Limpa a lista do disco 2
    disco3 = []  # Lista para armazenar caracteres do disco 3
    paridade = []  # Lista para armazenar os valores de paridade

    print("\nRAID 4: ")
    # Distribui os caracteres da frase entre disco1, disco2 e disco3 alternadamente
    for i in range(len(frase)):
        if i % 3 == 0:  # Índice múltiplo de 3 vai para o disco1
            disco1.append(frase[i])
        elif i % 3 == 1:  # Índice que deixa resto 1 na divisão por 3 vai para o disco2
            disco2.append(frase[i])
        elif i % 3 == 2:  # Índice que deixa resto 2 na divisão por 3 vai para o disco3
            disco3.append(frase[i])
            if i >= 2:  # Calcula a paridade a cada três caracteres
                paridade.append(xor_chars(frase[i-2], frase[i-1], frase[i]))
    
    # Adiciona paridade para os últimos caracteres se não forem múltiplos de 3
    if len(frase) % 3 != 0:
        paridade.append(xor_chars(frase[-1], frase[-2]))

    # Exibe os conteúdos dos discos 1, 2, 3 e a paridade
    print("Disco 1:", ''.join(disco1))
    print("Disco 2:", ''.join(disco2))
    print("Disco 3:", ''.join(disco3))
    print("Paridade:", ''.join(paridade))

    # Reconstrução do disco usando paridade
    disco_reconstruido = []
    # Reconstrói o disco combinando disco1, disco2 e a paridade
    for i in range(len(paridade)):
        valor_reconstruido = xor_chars(disco1[i], disco2[i], paridade[i])
        disco_reconstruido.append(valor_reconstruido)

    # Exibe o conteúdo dos discos 1, 2, paridade e o disco reconstruído
    print("\nRECONSTRUÇÃO: ")
    print("Disco 1:", ''.join(disco1))
    print("Disco 2:", ''.join(disco2))
    print("Paridade:", ''.join(paridade))
    print("Disco Reconstruído:", ''.join(disco_reconstruido))

# Executa a função principal
if __name__ == "__main__":
    main()
