package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		}

		if (element != null) {
			if (this.isEmpty()) {
				head++;
			}

			tail++;
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T element = this.array[head];
		
		if (head == tail) {
			head = -1;
			tail = -1;
		} else {
			head++;
		}
		elements--;
		return element;
	}

	@Override
	public T head() {
		if (this.isEmpty()) {
			return null;
		}

		return this.array[head];
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == this.array.length;
	}

}
