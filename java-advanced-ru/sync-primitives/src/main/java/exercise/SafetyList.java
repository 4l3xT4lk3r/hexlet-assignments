package exercise;

import java.util.ArrayList;
import java.util.List;

class SafetyList {
    // BEGIN
    private final List<Integer> list = new ArrayList<>();
    public synchronized void add(Integer num){
        list.add(num);
    }

    public Integer get(int index){
        return  list.get(index);
    }

    public int getSize(){
        return list.size();
    }
    // END
}
