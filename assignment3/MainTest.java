public class MainTest {
      /* To compile and run this program successefully, you should do the two following steps on command line:
      1-  javac Test.java
      2-  java Test > pq_test.txt to put the output in a pq_test.txt text file
      To generate more random nuumbers, change the first parameter of ranGen() method. The second and
      third parameter set the range of the random numbers to generate
    */
 public static void main(String[] args) {
                
		PQ225 x = new PQ225(10000);
                // generate 100 random numbers in the range of 1 to 500 
		x.ranGen(100, 1, 500);
		System.out.print("Random numbers: ");
		x.display();
		System.out.println();
		System.out.print("Heap: ");
		x.makeHeap();
		x.display();
		System.out.println();
		System.out.print("Sorted: ");
		x.heapSort();
		System.out.println();
		System.out.print("Size: " + x.size());
                System.out.println();

	}
}