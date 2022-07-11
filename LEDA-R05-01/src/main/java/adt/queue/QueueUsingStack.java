package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.stack1.isFull()) {
			throw new QueueOverflowException();
		}

		if (element != null) {
			try {
				this.stack1.push(element);
			} catch (StackOverflowException e) {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.stack1.isEmpty()) {
			throw new QueueUnderflowException();
		}

		T elemento = null;
		copyStack(this.stack1, this.stack2);
		
		try {
			elemento = this.stack2.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}

		copyStack(this.stack2, this.stack1);
		return elemento;
	}


	@Override
	public T head() {
		if (this.stack1.isEmpty()) {
			return null;
		}

		copyStack(this.stack1, this.stack2);
		T head = this.stack2.top();
		copyStack(this.stack2, this.stack1);

		return head;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack2.isFull();
	}

	private void copyStack(Stack<T> stack1, Stack<T> stack2) {
		while (!stack1.isEmpty()) {
			try {
				stack2.push(stack1.pop());
			} catch (Exception e) {
			}
		}
	}
}
