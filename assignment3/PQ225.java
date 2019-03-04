import java.util.Random;

    public class PQ225 {
	private int[] heapArray;
	private int currentsize;
	private int size;

	public PQ225(int max) {
		heapArray = new int[max *2];
		currentsize = 0;
	}

	public void ranGen(int n, int low, int high) {
		Random rand = new Random();
		int value = 0;
		int k = 1;

		do {
			value = rand.nextInt(high) + low;
			 heapArray[k] = value;
			k++;
		} while (n >= k);
                
		size = k -1;
	}

	public void insert(int x) {
		if (currentsize == 0) {
			heapArray[currentsize + 1] = x;
			currentsize = 2;
		} else {
			heapArray[currentsize++] = x;
			goUp();
		}
	}

	// bubble up
	private void goUp() {
		int current = currentsize - 1;
		while (current > 0 && heapArray[current / 2] > heapArray[current]) {
			int y = heapArray[current];
			heapArray[current] = heapArray[current / 2];
			heapArray[current / 2] = y;
			current = current / 2;
		}
	}

	public int deleteMin() {
		int min = heapArray[1];
		heapArray[1] = heapArray[currentsize - 1];
		heapArray[currentsize - 1] = 0;
		currentsize--;
		goDown(1);
		return min;
	}

	// bubble down
	private void goDown(int k) {
		int a = heapArray[k];
		int smallest = k;
		if (2 * k < currentsize && heapArray[smallest] > heapArray[2 * k]) {
			smallest = 2 * k;
		}
		if (2 * k + 1 < currentsize && heapArray[smallest] > heapArray[2 * k + 1]) {
			smallest = 2 * k + 1;
		}
		if (smallest != k) {
			swapElement(k, smallest);
			goDown(smallest);
		}

	}

	private void swapElement(int a, int b) {

		int temp = heapArray[a];
		heapArray[a] = heapArray[b];
		heapArray[b] = temp;
	}

	// insert into the heapArray and make a heap
	public void makeHeap() {
                int [] a = new int [heapArray.length];
                for (int i = 1; i <= size(); ++i) {
			a[i] = heapArray[i];
		}
		if (size() > 0) {
			for (int i = 1; i <= size(); ++i) {
				insert(a[i]);
			}
		}

	}

	/*
	 * delete the element with the highest priority (min) in the heap and print it 
	 *Keep doing until the heap is empty. The elements are going to be
	 * sorted in increasing order 
	 */
	public void heapSort() {
		int x;
		for (int i = 1; i <= size(); ++i) {
			System.out.print(deleteMin() + " ");
		}
	}

	// return the number of elements
	public int size() {
		return size;
	}

	public void display() {

		for (int i = 1; i <= size(); ++i) {
			System.out.print(heapArray[i] + " ");
		}
	}
    }