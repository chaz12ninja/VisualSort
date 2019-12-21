import java.util.ArrayList;

public class SortAlgo {
	static ArrayList<Integer> startI = new ArrayList<Integer>();
	static ArrayList<Integer> endI = new ArrayList<Integer>();
	
	public SortAlgo() {
		if(!startI.isEmpty()) {
			for (int i = 0; i < startI.size(); i++) {
				startI.remove(i);
			}
			System.out.println("startI cleared...");
		}
		if(!endI.isEmpty()) {
			for (int i = 0; i < endI.size(); i++) {
				endI.remove(i);
			}
			System.out.println("endI cleared...");
		}
	}
	public int[] selectionSort (int[] arrayS) {
		int[] array = arrayS;
    	int minI;
    	for (int i = 0; i < array.length; i++) {
			minI = minimum(array, i, array.length);
			startI.add(minI);
			endI.add(i);
			array = swap(array,i,minI);
		}
    	System.out.println("Sorted and returned");
    	System.out.println("startI size is "+startI.size());
    	System.out.println("endI size is "+endI.size());
    	return array;
    }
    
	public int[] bubbleSort(int[] array) {
    	for (int end = array.length-1; end > 0; end--) {
			for (int i = 0; i < end; i++) {
				if(array[i]>array[i+1]) {
					startI.add(i);
					endI.add(i+1);
					swap(array, i, i+1);
				}
			}
		}
    	return array;
	}
    
    public int[] insertionSort(int[] array) {
    	for (int i = 1; i < array.length; i++) {
    		int value = array[i];
			for (int j = i-1; j >= 0; j--) {
				if(value<array[j]) swap(array, j, j+1);
				else break;
			}
		}
    	return array;
    }
    
    public int[] mergeSort(int[] array,int ls, int re) {
    	if (ls < re) { 
    		int m = ls+(re-ls)/2; 
          
    		// Sort first and second halves 
    		array = mergeSort(array, ls, m); 
    		array = mergeSort(array, m+1, re); 
      
    		array = merge(array, ls, re); 
        }
    	return array;
    }
    
   
    public int[] merge (int[] array, int ls, int re) {
    	
    	int le = (ls+re)/2;
    	int rs = le+1;
    	int i=ls;
    	int j=rs;
    	int k=ls;
    	
    	int[] result = new int[array.length];
    	for (int l = 0; l < result.length; l++) result[l] = array[l];
    	
    	while(i<=le && j<=re) {
    		if(array[i]<=array[j]) {
    			result[k] = array[i];
    			i++;
    		}else {
    			result[k] = array[j];
    			j++;
    		}
    		k++;
    	}
    	
    	while(i<=le) {
    		result[k] = array[i];
    		i++;
    		k++;
    	}
    	while(j<=re) {
    		result[k] = array[j];
    		j++;
    		k++;
    	}
    	return result;
    }
    private int minimum(int[] minArray,int start, int end) {
    	int min = 2147483647;
    	int index=0;
    	for (int i = start; i < end; i++) {
			if (minArray[i]<=min) {
				min = minArray[i];
				index = i;
			}
		}
    	
    	return index;
    }
    
    private int[] swap(int[] array, int x, int y) {
    	int temp = array[x];
    	array[x] = array[y];
    	array[y] = temp;
    	return array;
    }
}
