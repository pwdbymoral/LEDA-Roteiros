package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.getData() == null;
	}

	@Override
	public int size() {
		int size = 0;
		if (this.isEmpty()) {
			return size;
		} else {
			size++;
			return recursiveSize(this.getNext(), size);
		}
	}

	private int recursiveSize(RecursiveSingleLinkedListImpl<T> linkedList, int size) {
		if (this.getNext() == null) {
			return size;
		} else {
			return recursiveSize(this.getNext(), size) + 1;
		}
	}

	@Override
	public T search(T element) {
		T returnedElement = null;
		
		if (!isEmpty()) {
			if (this.getData() == element) {
				returnedElement = this.getData();
			} else {
				this.getNext().search(element);
			}
		}

		return returnedElement;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.isEmpty()) {
				this.setData(element);
				this.setNext(new RecursiveSingleLinkedListImpl<T>());
			} else {
				this.getNext().insert(element);
			}

		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.getData() == element) {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		if (!this.isEmpty()) {
			int index = 0;
			array[index] = this.getData();
			if (this.getNext().getData() != null) {
				array = recursiveToArray(this.next, array, index);
			}
		} 

		return array;
	}

	private T[] recursiveToArray(RecursiveSingleLinkedListImpl<T> linkedList, T[] array, int index) {
		if (!this.isEmpty()) {
			index++;
			array[index] = this.getData();
			array = recursiveToArray(this.next, array, index);
		}
		
		return array;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

	// private RecursiveSingleLinkedListImpl<T> createNewNode(T element, SingleLinkedListNode<T> next) {
	// 	return new SingleLinkedListNode<T>(element, next);
	// }
	// private SingleLinkedListNode<T> createNilNode() {
	// 	return createNewNode(null, null);
	// }

}
