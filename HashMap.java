// Author: Narender Surabhi
// Implemented a basic HashMap datastructure

import java.util.Objects;

public class HashMap2<K, V> {
	
	private class Node<K, V> {
		
		K key;
		V value;		
		Node<K, V> next;
		
		public Node(K key, V val, Node<K, V> next) {			
			this.key = key;
			this.value = val;
			this.next = next;
		}
		
		public boolean hasNext() {
			return this.next != null;
		}
		
		public Node<K, V> getNext() {
			return ( this.hasNext() ? this.next : null);			
		}
		
		public void setValue(V val) {
			this.value = val;
		}
		
		public V getValue() {
			return this.value;
		}
		
		public K getKey() {
			return this.key;
		}
		
	}
	
	private Node<K, V>[] items;
	private int size = 1000; 
	
	public HashMap2() {
		this.size = 1000;
		this.items = (Node<K, V>[]) new Node[this.size];
	}
	
	private int hash(Object key) {
		
		int hashCode = Objects.hashCode(key);
		
		return hashCode%size;
	}
	
	private Node<K, V> getItemFromLinkedList(Object key, Node<K, V> list) {
		// TODO implement		
		
		if (list != null) {
			if (key.equals(list.key)) {
				return list;
			}
		}
		
		Node<K, V> cur = list;
		
		while (cur.hasNext()) {
			cur = cur.getNext();
			if (key.equals(cur.getKey())) {
				return cur;
			}			
		}
			
		return null;
	}
	
	private V removeItem(Object key) {
		// TODO implement
		if (this.containsKey(key)) {
			int bin = hash(key)/this.size;
			Node<K, V> node = items[bin];
			while(node != null) {
				if(node.key == key) {
					V value = node.value;
					items[bin] = (node.hasNext()? node.next : null);
					return value;
				}
				node = (node.hasNext()? node.next : null);
			}
		} 
		return null; 				
	}
	
	public void put(Object key, V value) {
		if (this.containsKey(key)) {
			Node<K, V> item = this.getItem(key);
			item.setValue(value);					
		} else {
			int bin = hash(key)/this.size;
			if (items[bin] == null) {
				items[bin] = new Node<K, V>((K) key, value, null);
			} else {
				items[bin].next = new Node<K, V>((K) key, value, null);
			}
		}
	}
	
	public V removeKey(Object key) {		
		return (this.containsKey(key) ? this.removeItem(key) : null);		
	}
	
	public V get(Object key) {
		
		int bin = hash(key)/this.size;
		Node<K, V> list = items[bin];
		
		Node<K, V> item = getItemFromLinkedList(key, list);
		
		return item.getValue();
	}
	
	public Node<K, V> getItem(Object key) {
		int bin = hash(key)/this.size;
		Node<K, V> list = items[bin];
		
		Node<K, V> item = getItemFromLinkedList(key, list);
		
		return item;
	}
		
	public boolean containsKey(Object key) {
		int bin = hash(key)%this.size;
//		System.out.println(hash + " is hash and size is " + items.length);
		
		if (items[bin] != null) {
			Node<K, V> node = items[bin];		
			
			do
			{
				if (node != null) {
					if (key.equals(node.key)) {
						return true;
					}
				}				
			}
			while ((node = (node.hasNext()? node.next: null)) != null);
			
			return false;
			
		}
		
		return false;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap2<Integer, String> map = new HashMap2<Integer, String>();
		
		for (int i = 1; i <= 10; i++) {
			map.put(i, "Hello - " + Integer.toString(i));
		}
		
//		for (in)
//		map.put(900, "Hello - " + Integer.toString(10));
		
		System.out.println(map.get(9));

	}

}
