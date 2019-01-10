package ypp170130;

/**
 *     Team No: 33
 *     @author Vineet Vats: vxv180008
 *     @author Yash Pradhan: ypp170130
 *     Short Project 7: Implementing Robin Hood hashing algorithm
 *
 *     MyInteger class: Overides equals and hashCode of Integer class
 */

public class MyInteger {
    Integer value;

    public MyInteger(int value){
        this.value = new Integer(value);
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object == null || getClass() != object.getClass()){
            return false;
        }
        return value.equals(((MyInteger) object).value);
    }

    @Override
    public int hashCode(){
        return value;
    }
}
