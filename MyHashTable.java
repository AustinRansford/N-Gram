import java.util.Arrays;
/**
 * Write a description of class MyHashTable here.
 *
 * @author Austin Rasnford
 * @version Jan 22, 2024
 */
public class MyHashTable<K,V>
{
    private int size;
    private Node<K,V>[] hashTable;
    private double loadFactor = 0.7; 
    private int tableSize;

    /**
     * Constructor for objects of class MyHashTable
     */
    public MyHashTable() {
        size = 0;
        hashTable = (Node<K,V>[]) new Node[10];
        tableSize = 10;
    }
    
    /**
     * allocates a piece of data to a index that correlates to the int value of the hashed
     * key 
     * 
     * @param key the data that has a correlating value in the hash table
     * @param value the data that is stored in the hash table 
     */
    public void put(K key, V value) {
        int index = hashFunction(key);
        Node newNode = new Node(key, value);
        if (searchBucket(index, key) == null){
            addToBucket(index, newNode);
            size++;
            if (size > loadFactor * tableSize){
                this.expandHashTable();
            }
        } else {
            searchBucket(index, key).setValue(value); 
        }
        
    }
    
    /**
     * gets and returns the data that is at the location of the inputed key
     * 
     * @param key - is the data that corresponds to an index locaton the data from this 
     * index will be returned 
     * @returns the value at the location of the key in the hash table
     */
    public V get(K key) {
        int indexKey = hashFunction(key);
        if (searchBucket(indexKey, key) == null){
            return null;
        } else {
            return (V) searchBucket(indexKey, key).getValue();
        }
    }
     
    /**
     * this method removes the node that is in the corresponding index and with the inputed key
     * 
     * @param key - the key of the node that will be removed
     * @return the value of the corresponding data point to the key that was removed from the hashtable 
     */
    public V remove(K key) {
        int indexKey = hashFunction(key);
        Node oldNode = searchBucket(indexKey, key);
        if (oldNode == null){
            return null;
        } else {
            removeFromBucket(indexKey, oldNode);
            return (V) oldNode.getValue();
        }
       
    }
    
    /**
     * returns the number of the elements stored in the Hash Table
     * @returns size
     */
    public int size() {
        return size;
    }
    
    /**
     * returns if empty or not
     * @returns true if no elements have been added false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
     /**
      * returns HashTable in a string representation
      * @returns a representation of the hashTable that the HashTable is mapped onto an array
      */
    public String toString() {
        return Arrays.toString(hashTable);
    }
    
    private int hashFunction (K key){
        return Math.abs(key.hashCode()) % hashTable.length; 
    }
    
    private void expandHashTable() {
        Node<K,V>[] prevHashTable = hashTable;
        hashTable = (Node<K,V>[]) new Node[tableSize * 2];
        tableSize *= 2; 
        for (int i = 0; i < prevHashTable.length; i++){
            if (prevHashTable[i] != null){
                Node<K,V> currentNode = prevHashTable[i];
                Node<K, V> nextNode = currentNode.getNext();
                while (currentNode!= null){
                    nextNode = currentNode.getNext();
                    int hashedLocation = Math.abs(currentNode.getHashValue()) % tableSize;
                    if (hashTable[hashedLocation] == null){
                        hashTable[hashedLocation] = currentNode;
                        currentNode.setNext(null);
                    } else {
                        currentNode.setNext(hashTable[hashedLocation]);
                        hashTable[hashedLocation] = currentNode;
                    }
                    currentNode = nextNode;
                }
            }
        }
    }
    
    private void addToBucket(int hashValue, Node newNode){
        if (hashTable[hashValue] == null) {
            hashTable[hashValue] = newNode;
        } else {
            Node head = hashTable[hashValue];
            hashTable[hashValue] = newNode;
            hashTable[hashValue].setNext(head);
        }
    }
    
    private Node searchBucket(int hashValue, K key){
        Node currentNode = hashTable[hashValue];
        if (currentNode != null){
            while (!currentNode.getKey().equals(key)){
                if (currentNode.getNext() == null){
                    return null;
                } else {
                    currentNode = currentNode.getNext();
                }
            }
        }
            return currentNode;
    }
    
    private void removeFromBucket(int hashValue, Node oldNode){
         if (hashTable[hashValue] == oldNode){
            hashTable[hashValue] = hashTable[hashValue].getNext();
            size--;
        } else {
            Node currentNode = hashTable[hashValue];
            while (currentNode.getNext() != oldNode){
                currentNode = currentNode.getNext();
            }
            if (currentNode.getNext() == oldNode){
                 currentNode.setNext(oldNode.getNext()); 
                 size--;
            }
        }
    }
}


class Node<K, V> {
    private V value;
    private K key;
    private Node next;
    private int hashValue;
    
    public Node(K key, V data) {
        this.value = data; 
        this.key = key;
        this.hashValue = key.hashCode();
    }
    
    public V getValue(){
        return value;
    }
    
    public K getKey(){
        return key;
    }
    
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
    
    public void setValue(V value){
        this.value = value;
    }
    
    public int getHashValue(){
        return hashValue;
    }
    
    public String toString(){
        String returned = key + "->" + value;
        Node currentNode = this;
        while (currentNode.getNext() != null){
            currentNode = currentNode.getNext();
            returned += "|" + currentNode.getKey() + "->" + currentNode.getValue();
        }
        return returned;
    }
}
