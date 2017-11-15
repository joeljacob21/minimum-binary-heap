package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		size = 0;								 //of child/parent computations...
		//the book/animation page both do this.
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		if(size == 0) {
			array[1] = entry;
			size++;
		} else {
			int place = size + 1; //where element goes
			while(place/2 > 0 && array[place/2].getPriority() > entry.getPriority()) {
				array[place] = array[place/2];
				place = place/2;
			}
			array[place] = entry;
			size++;
		}
	}

	@Override
	public void delMin() {
		array[1] = array[size];
		array[size] = null;
		size--;
		bubbleDown(1);
	}

	public void bubbleDown(int place) {
		while(getLeftChild(place) != null) {
			int smolChild = place*2;
			if(getRightChild(place) != null && getRightChild(place).getPriority() < getLeftChild(place).getPriority()) {
				smolChild = (place*2)+1;
			} if(array[smolChild].getPriority() < array[place].getPriority()) {
				EntryPair temp = array[place];
				array[place] = array[smolChild];
				array[smolChild] = temp;
			} else {
				break;
			} place = smolChild;
		}
	}

	public EntryPair getLeftChild(int place) {
		return this.array[place*2];
	}

	public EntryPair getRightChild(int place) {
		return this.array[(place*2) + 1];
	}

	@Override
	public EntryPair getMin() {
		return this.array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		for(int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
			size++;
		} 
		
		int place = size/2;
		while(array[place].getValue() != null) {
			bubbleDown(place);
			place--;
		}
	}
}