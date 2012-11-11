package chapter13;

import java.util.Iterator;
import java.lang.UnsupportedOperationException;

public class ScottStack<T> implements Iterable<T> {

	private Item<?> top = null;
	private Item<?> bottom = null;
	private int length = 0;
	
	ScottStack() {
	}
	
	@Override
	public Iterator<T> iterator() {
		return new StackIterator<T>();
	}

	public void push(T value) {
		Item<T> tmpItem = new Item<T>(value, top);
		if (top != null) {
			top.setNext(tmpItem);			
		}
		top = tmpItem;
		
		if (bottom == null) {
			bottom = tmpItem;
		}
		
		length++;
	}
	
	public T pop() {
		T ret_val;
		
		if (top == null) {
			ret_val = null;
		} else {
			ret_val = (T) top.getValue();
			
			// Disconnect the current top from the stack and make prev the new top
			Item<T> tmpTop = (Item<T>) top.getPrev();
			top.setPrev(null);
			top = tmpTop;
			
			if (top != null) {
				top.setNext(null);
			} else {
				bottom = null;
			}
			
			length--;
		}
		
		return ret_val;
	}
	
	public void listAll() {
		StackIterator<T> stackIterator = new StackIterator<T>();
		
		while (stackIterator.hasNext()) {
			System.out.println(stackIterator.next());
		}
		
		System.out.println();
	}
	
	// Private classes
	private class Item<T> {
		private T value = null;
		private Item<?> next = null;
		private Item<?> prev = null;
		
		Item(T value) {
			this.value = value;
		}
		
		Item(T value, Item<?> prev) {
			this(value);
			this.prev = prev;
		}
		
		Item(T value, Item<?> prev, Item<?> next) {
			this(value, prev);
			this.next = next;
		}
		
		// Getter and setters
		public T getValue() {
			return this.value;
		}
		
		public Item<?> getPrev() {
			return this.prev;
		}
		
		public Item<?> getNext() {
			return this.next;
		}
			
		public void setValue(T value) {
			this.value = value;
		}
		
		public void setPrev(Item<?> prev) {
			this.prev = prev;
		}
		
		public void setNext(Item<?> next) {
			this.next = next;
		}
	}

	private class StackIterator<T> implements Iterator<T> {
		
		private Item<?> nextItem = null;
		
		StackIterator() {
			nextItem = bottom;
		}
		
		@Override
		public boolean hasNext() {
			boolean ret_val = false;
			
			if (nextItem != null) {
				ret_val = true;
			}
			
			return ret_val;
		}
	
		@Override
		public T next() {
			T ret_val = (T)nextItem.getValue();
			nextItem = nextItem.getNext();
			
			return ret_val;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
