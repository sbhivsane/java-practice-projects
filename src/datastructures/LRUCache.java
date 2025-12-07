package datastructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LRUCache {

    Map<Integer,Integer> dataMap =null;
    LinkedList<Integer> dll = null;
    Integer capacity = null;
    static void main() {

        LRUCache lruc= new LRUCache(2);
        lruc.set(5,10);
        lruc.set(1,12);
        System.out.println(lruc.get(5));
        lruc.set(6,13);
        lruc.set(1,22);
        System.out.println(lruc.get(5));
        System.out.println(lruc.get(1));

    }

    public LRUCache(int cap) {
        dataMap = new HashMap(cap);
        dll = new LinkedList();
        this.capacity = cap;
    }

    public int get(int key) {
        if(this.dataMap.containsKey(key)){
            return  this.dataMap.get(key);
        }
        return -1;
    }

    public void set(int key, int value) {
        // if key is presnet
        // delete the key
        if(dataMap.size()<capacity){
            dll.addLast(key);
            dataMap.put(key,value);
        }else{
            if(dataMap.containsKey(key)){
                dll.remove(Integer.valueOf(key));
                dataMap.remove(key);
                dll.addLast(key);
                dataMap.put(key,value);

            }else{
                Integer val = dll.removeFirst();
                dataMap.remove(val);
                dll.addLast(key);
                dataMap.put(key,value);
            }
        }
    }

}



