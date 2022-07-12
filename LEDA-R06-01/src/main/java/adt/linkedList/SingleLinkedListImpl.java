package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;

		if (!head.isNIL()) {
			SingleLinkedListNode<T> node = this.head;
			while (!node.isNIL()) {
				size++;
				node = node.getNext();
			}
		}

		return size;
	}

	@Override
	public T search(T element) {
		T returnedElement = null;
		
		if (!head.isNIL()) {
			SingleLinkedListNode<T> node = this.head;
			while (!node.isNIL()) {
				if (node.getData() == element) {
					returnedElement = node.getData();
				}
				node = node.getNext();
			}
		}

		return returnedElement;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (this.head.isNIL()) {
				this.setHead(createNewNode(element, createNilNode()));
			} else {
				SingleLinkedListNode<T> node = this.head;
				while (!node.isNIL()) {
					node = node.getNext();
				}
				node.setData(element);
				node.setNext(createNilNode());
			}
		}
	}

	@Override
	public void remove(T element) {
		 if (search(element) != null) {
			if (this.head.getData() == element) {
				this.setHead(createNilNode());
			} else {
				SingleLinkedListNode<T> node = this.head;
				while (!(node.getNext().getData() == element)) {
					node = node.getNext();
				}
				SingleLinkedListNode<T> nextNode = node.getNext().getNext();
				node.setNext(nextNode);
			}
		 }
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		if (!this.head.isNIL()) {
			int i = 0;
			SingleLinkedListNode<T> node = this.head;
			while (!node.isNIL()) {
				array[i] = node.getData();
				i++;
				node = node.getNext();
			}
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

	private SingleLinkedListNode<T> createNewNode(T element, SingleLinkedListNode<T> next) {
		return new SingleLinkedListNode<T>(element, next);
	}
	private SingleLinkedListNode<T> createNilNode() {
		return createNewNode(null, null);
	}
}
