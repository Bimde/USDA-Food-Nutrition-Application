package database;

public interface KeyCompare<E> extends Comparable<E>{
	int key = 0;
	public int compareTo(int key);
	public int getKey();
}
