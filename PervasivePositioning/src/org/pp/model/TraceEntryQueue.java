package org.pp.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TraceEntryQueue<T> {

	private Node<T> startNode;
	private int size = 0;
	private int limit;
	
	public TraceEntryQueue(int limit){
		this.limit = limit;
	}
	
	public void enqueue(T item, double priority){
		System.out.println("Priority: " + priority);
		Node<T> node = null;
		if(getSize()<limit){
		if(isEmpty()){
			node =  new Node<T>(item, null, priority);
			startNode = node;
			
		}
		else{
			node =  new Node<T>(item, null, priority);
			if(startNode.priority > priority){
				node.next = startNode;
				startNode = node;
			}
			else{
				Node middleNode = findNodeFromPriority(startNode, priority);
				insertNodeInTheMiddle(middleNode, node);
			}
		}
		size++;
		}
	}
	
    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.data;
            current = current.next; 
            return item;
        }
    }

    public Iterator<T> getIterator(){
    	return new ListIterator<T>(startNode); 
    }
	
	private void insertNodeInTheMiddle(Node<T> beforeNode, Node<T> node){
		if(beforeNode.next != null){
			node.next = beforeNode.next;
		}
			beforeNode.next = node;

	}
	
	private Node<T> findNodeFromPriority(Node<T> node, double priority){
		if(node.next == null) return node;
		else if(node.next.priority < priority){
			return findNodeFromPriority(node.next, priority);
		}
		else return node;
	}
	
	public int getSize(){
		return size;
	}
	
	public boolean isEmpty(){
		return getSize() == 0;
	}
	
	private class Node<T>{
		
		public Node(T data, Node<T> next, double priority){
			this.data = data;
			this.next = next;
			this.priority = priority;
		}
		  protected T  data;
		  protected Node<T> next; 
		  protected double priority;
	}

	
}
