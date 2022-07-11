package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1) return;

		int bigger = array[leftIndex];
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > bigger) {
				bigger = array[i];
			}
		}

		Integer[] cumulativeArray = new Integer[bigger + 1];
		for (int i = leftIndex; i <= rightIndex; i++) {
			int element = ((int)(array[i]));
			cumulativeArray[element] = 0;
			cumulativeArray[element] += 1;
		}
		
		for (int i = 1; i <= cumulativeArray.length; i++) {
			cumulativeArray[i] = cumulativeArray[i - 1];
		}

		Integer[] finalArray = new Integer[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
            finalArray[cumulativeArray[array[i] - 1] -1] = array[i];
            cumulativeArray[array[i] - 1] -= 1;
        }

		for (int i = 0; i < array.length; i++) {
			array[i] = finalArray[i];
		}
		
	}

}
