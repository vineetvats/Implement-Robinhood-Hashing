
---------------------------- READ ME --------------------------

This is a readme file containing instructions for executing code for 
Short Project 7: Implementing Robin Hood hashing algorithm

Team No: 33

@Authors:
Vineet Vats: vxv180008
Yash Pradhan: ypp170130

Short Project 7: Implementing Robin Hood hashing algorithm


Instructions to execute code:

The uploaded folder with name as my net id: ypp170130 contains java files "MyInteger.java", "RobinHood.java", "Timer.java"  and "RobinHoodDriver.java"


NOTE: while executing from command prompt, the pwd should be the directory containing the directory ypp170130

Steps for running code from the cmd prompt

1. Compile the MyInteger.java by executing the following command
> javac ypp170130/MyInteger.java

2. Compile the "RobinHood.java" by executing the following command
> javac ypp170130/RobinHood.java

3. Compile the "Timer.java" by executing the following command
> javac ypp170130/Timer.java

Now, the driver code can be compiled and executed.

4. Compile the "RobinHoodDriver.java" by executing the following command
> javac ypp170130/RobinHoodDriver.java

5. Run Driver
> java ypp170130/RobinHoodDriver

Methods:

boolean add(key, val)
T get(key)
boolean contains(key)
T remove(key)
int size()
static<T> int distinctElements(T[ ] arr)


Sample Output:

This is driver for Robin Hood

Performance of RobinHood
Time: 16041 msec.
Memory: 496 MB / 895 MB.

Performance of java's hashmap
Time: 3903 msec.
Memory: 396 MB / 745 MB.

Performance on random integers

All should match
631946
631946
Distinct Elements:631946


Removing Elements

Both should match
625662
625662
Time: 2045 msec.
Memory: 160 MB / 701 MB.