#include <stdio.h> // Lê os dados e armazena eles
#include <stdlib.h> // For, while, do

int multiplicar(int x, int y){
	int resultado = x * y;
	printf("%d \n", resultado); 
}

int main(){
	multiplicar(10, 5);
	multiplicar(20, 5);
	multiplicar(5, 5);
}

