import java.util.*;

public class ArrayStack<T> implements StackADT<T> {
	private final static int DEFAULT_CAPACITY = 100;
	private int top;
	private T[] stack;
	
	public ArrayStack() {
	   top = 0;
       stack = (T[])(new Object[DEFAULT_CAPACITY]);
	}
	public ArrayStack(int initialCapacity) {
	
		top = 0;
		@SuppressWarnings("unchecked")
		T[] stack = (T[]) (new Object[initialCapacity]);
		
	}
	
	public void push(T element) {
		if (size() == stack.length) {
			expandCapacity();
		}
		stack[top] = element;
		top++;
	}
	
	public T pop() {
		top--;
		T result = stack[top];
		stack[top] = null;
		
		return result;
	}
	
	public T peek() {
		return stack[top-1];
	}
	
	public boolean isEmpty() {
		return (top == 0);
	}
	
	public int size() {
		return top;
	}
	
	private void expandCapacity() {
	  T[] larger = (T[])(new Object[stack.length*2]);
      for (int index=0; index < stack.length; index++) {
         larger[index] = stack[index];
	  }
      stack = larger;
	}
	
	
	public String toString() {
		String guh = "";
		for (int i = 0; i < stack.length; i++) {
			guh += stack[i];
		}
		return guh;
	}
	
}