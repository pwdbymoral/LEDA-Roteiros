package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length <= 1 || rightIndex > array.length - 1 || leftIndex < 0 || leftIndex >= rightIndex) return;

		else {
			int middleIndex = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middleIndex);
			sort(array, middleIndex + 1, rightIndex);

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
}
