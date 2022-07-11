package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import sorting.divideAndConquer.MergeSort;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		int arraySize = rightIndex - leftIndex + 1;
		if (arraySize > SIZE_LIMIT) {
			mergeSort(array, leftIndex, rightIndex);
			MERGESORT_APPLICATIONS++;
		} else {
			insertionSort(array);
			INSERTIONSORT_APPLICATIONS++;
		}
	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1 || rightIndex > array.length - 1 || leftIndex < 0 || leftIndex >= rightIndex) return;

		else {
			int middleIndex = (leftIndex + rightIndex) / 2;
			this.sort(array, leftIndex, middleIndex);
			this.sort(array, middleIndex + 1, rightIndex);

			merge(array, leftIndex, middleIndex, rightIndex);
		}
	}
	
	private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
		T[] auxiliar = (T[]) new Comparable[array.length];
        for (int i = leftIndex; i <= rightIndex; i++) {
            auxiliar[i] = array[i];
		}

		int i = leftIndex;
		int j = middleIndex +1;
		int k = leftIndex;

		while (i <= middleIndex && j <= rightIndex) {
			if (auxiliar[i].compareTo(auxiliar[j]) <= 0) {
				array[k] = auxiliar[i];
				i++;
			} else {
				array[k] = auxiliar[j];
				j++;
			}
			k++;
		}

		while (i <= middleIndex) {
			array[k] = auxiliar[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = auxiliar[j];
			j++;
			k++;
		}
	}

	private void insertionSort(T[] array) {
		if (array == null || array.length == 0 || array.length == 1) return;

		for (int i = 1; i < array.length; i++) {
			int j = i;

			while (j > 0 && array[j].compareTo(array[j-1]) < 0) {
				Util.swap(array, j, j-1);
				j--;
			}
		}
	}
}
