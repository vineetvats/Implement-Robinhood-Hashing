package ypp170130;

import java.util.HashMap;
import java.util.Random;


/**
 *     Team No: 33
 *     @author Vineet Vats: vxv180008
 *     @author Yash Pradhan: ypp170130
 *     Short Project 7: Implementing Robin Hood hashing algorithm
 *
 *     Driver program for skip list implementation.
 */

public class RobinHoodDriver {


    public static void main(String args[]){
        RobinHood <MyInteger, Integer> map = new RobinHood<>();
        HashMap hashMap = new HashMap();

        System.out.println("This is driver for Robin Hood");

        System.out.println("\nPerformance of RobinHood");
        // checking performance of implemented RobinHood
        Timer timer = new Timer();

        for(int i = 0; i<5000000; i++){
            map.add(new MyInteger(i), i);
        }

        for(int i = 0; i<5000000; i++){
            map.get(new MyInteger(i));
        }

        for(int i = 0; i<5000000; i++){
            map.remove(new MyInteger(i));
        }

        for(int i = 0; i<5000000; i++){
            map.get(new MyInteger(i));
        }

        timer.end();
        System.out.println(timer);

        System.out.println("\nPerformance of java's hashmap");
        // checking performance against java's hashmap

        timer = new Timer();

        for(int i = 0; i<5000000; i++){
            hashMap.put(i,i);
        }

        for(int i = 0; i<5000000; i++){
            hashMap.containsKey(i);
        }

        for(int i = 0; i<5000000; i++){
            hashMap.remove(new MyInteger(i));
        }

        for(int i = 0; i<5000000; i++){
            hashMap.containsKey(i);

        }
        // End Time
        timer.end();
        System.out.println(timer);



        System.out.println("\nPerformance on random integers");
        // checking performance on random integers
        map = new RobinHood<>();
        hashMap = new HashMap();
        timer = new Timer();
        Random rand = new Random();
        int max = 1000000, min = 0;
        Integer[] arr = new Integer[1000000];


        for(int i = 0; i < 1000000; i++){
            int x = rand.nextInt((max - min) + 1) + min;
            map.add(new MyInteger(x), x);
            hashMap.put(x, x);
            arr[i] = x;
        }


        System.out.println("\nAll should match");
        System.out.println(map.size());
        System.out.println(hashMap.size());
        System.out.println("Distinct Elements:"+RobinHood.distinctElements(arr));


        System.out.println("\n\nRemoving Elements");
        for(int i = 0; i < 10000; i++){
            int x = rand.nextInt((max - min) + 1) + min;
            map.remove(new MyInteger(x));
            hashMap.remove(x);
        }

        System.out.println("\nBoth should match");
        System.out.println(map.size());
        System.out.println(hashMap.size());

        timer.end();
        System.out.println(timer);

    }
}