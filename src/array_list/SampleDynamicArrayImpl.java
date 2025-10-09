package array_list;

public class SampleDynamicArrayImpl {

    // initial capacity array

    // if  load factor > 0.7 create the new array the double the size of current array and copy the element and delet old array


    Integer INITIAL_CAPACITY = 10;
    Double MAX_THRESHOLD=0.7;
    Integer[] internalArray = new Integer[INITIAL_CAPACITY];
    int currentIdx =0;

    SampleDynamicArrayImpl(Integer initialCapacity){
        INITIAL_CAPACITY = initialCapacity;
        internalArray = new Integer[INITIAL_CAPACITY];
    }


    // method to add the element to the array

    public void add(Integer element){
        if(currentIdx<INITIAL_CAPACITY){
            if(isMaxThresholdReached())
                reCreateNewArray(internalArray);
            internalArray[currentIdx]=element;
            currentIdx++;
        }
    }

    public  void printTheArray(){
        for(int i =0;i<internalArray.length;i++){
            System.out.print(internalArray[i]+" , ");
        }
    }
    public Integer getSizeOfArray(){
        return currentIdx;
    }
    public  Integer getArrayCapacity(){
        return  INITIAL_CAPACITY;
    }

    private boolean isMaxThresholdReached(){
        return ((double) (currentIdx+1) / INITIAL_CAPACITY) >= MAX_THRESHOLD;
    }

    private void reCreateNewArray(Integer[] oldArray){
        INITIAL_CAPACITY = INITIAL_CAPACITY*2;
        Integer[] newArray = new Integer[INITIAL_CAPACITY];
        for(int i=0;i<oldArray.length;i++)
            newArray[i]=oldArray[i];
        internalArray = newArray;

    }


}
