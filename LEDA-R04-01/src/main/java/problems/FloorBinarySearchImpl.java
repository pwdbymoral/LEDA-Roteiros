package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if(array.length > 0 && array != null) {
			quickSortM3(array, 0, array.length - 1);
			return recursiveFloor(array, x, null, 0, array.length - 1);
		}
			return null;
	}
	
	private Integer recursiveFloor(Integer[] array, Integer x,Integer floor, int leftIndex, int rightIndex) {
		if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex < array.length) {
			int middleIndex = (rightIndex + leftIndex)/2;
			if (array[middleIndex].compareTo(x) == 0) {
				return array[middleIndex];
		}
			if (array[middleIndex].compareTo(x) > 0) {
				return recursiveFloor(array, x, floor, leftIndex,middleIndex -1 );
		}
			if (array[middleIndex].compareTo(x) < 0) {
				return recursiveFloor(array, x, array[middleIndex], middleIndex + 1, rightIndex );
		}
		}
		return floor;
	}
private void quickSortM3(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0 || leftIndex >= array.length || rightIndex >= array.length || rightIndex < 0) {
			return;
			
		}
			if (leftIndex < rightIndex) {
				int pivotPosition = partition(array, leftIndex, rightIndex);
				quickSortM3(array,leftIndex, pivotPosition - 1);
				quickSortM3(array, pivotPosition + 1, rightIndex);
			}
	}
private int partition (Integer [] array, int leftIndex, int rightIndex) {
		
		int middleIndex = (leftIndex + rightIndex) / 2;
		medianOfThree(array, leftIndex, middleIndex, rightIndex);
		Integer pivot = array[middleIndex];
		Util.swap(array,middleIndex,rightIndex-1);
		int pivotIndex = rightIndex - 1;
		
		for (int j = pivotIndex - 1; j > leftIndex; j--) {
			if (pivot.compareTo(array[j]) < 0) {
				pivotIndex--;
				Util.swap(array, pivotIndex,j);
			}
		}
		Util.swap(array, rightIndex-1, pivotIndex);
		
		return pivotIndex;
	}
	
	private void medianOfThree(Integer[] array, int leftIndex, int middleIndex, int rightIndex) {
		if (array[leftIndex].compareTo(array[middleIndex]) > 0) {
			Util.swap(array, leftIndex, middleIndex);
		}
		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		if (array[middleIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, middleIndex, rightIndex );
		}
	}
}