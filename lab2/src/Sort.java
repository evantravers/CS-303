public class Sort {
	private static final int CUTOFF = 10;
	
	// CHANGED fix bubblesort
	public static void bubbleSort(int[] list) {
		for (int i=1;i<list.length;i++) {
			int sortednum=1;
			if (!(sortednum==list.length)) {
				for (int j=list.length-1;j>0;j--) {
					// System.out.println("checking " + list[i] + " and " + list[i+1]);
					if (list[j]<list[j-1]) {
						// System.out.println("swapping!");
						// swap the two values
						int temp = list[j];
						list[j] = list[j-1];
						list[j-1] = temp;
					}
					else
					{
						sortednum++;
					}
				}
			}
			else {
				// System.out.print("array is sorted!");
				return;
			}
		}
	}	
	
	public static void mergeSort(int[] list) {
		int [] tmplist = new int[list.length];
        mergeSort( list, tmplist, 0, list.length - 1 );
	}
	
	private static void mergeSort(int[] list, int[] tmplist, int left, int right) {
		if (left<right) {
			int center=(left+right)/2;
			mergeSort(list,tmplist,left,center);
			mergeSort(list,tmplist,center+1,right);
			merge(list,tmplist,left,center+1,right);
		}
	}
	
	private static void merge(int[] list, int[] tmplist, int leftPos, int rightPos, int rightEnd) {
		int leftEnd=rightPos-1;
		int tmpPos=leftPos;
		int numElements=rightEnd-leftPos+1;
		
		// main
		while (leftPos<=leftEnd&&rightPos<=rightEnd) {
			// CHANGED check this comparator
			if(list[leftPos]<list[rightPos]) {
				tmplist[tmpPos++]=list[leftPos++];
			}
			else {
				tmplist[tmpPos++]=list[rightPos++];
			}
		}
		while (leftPos<=leftEnd) {
			tmplist[tmpPos++] = list[leftPos++];
		}
		while (rightPos<=rightEnd) {
			tmplist[tmpPos++]=list[rightPos++];
		}
		for (int i=0;i<numElements ;i++, rightEnd--) {
			list[rightEnd]=tmplist[rightEnd];
		}
	}
	
	public static void quickSort(int[] list) {
		// CHANGED implement quickSort
		quickSort( list, 0, list.length - 1 );
	}
	
	private static void quickSort(int[] list, int low, int high) {
		if(low+CUTOFF>high) {
			insertionSort(list,low,high);
		}
		else {
			int middle=(low+high)/2;
			if (list[middle]<list[low]) {
				swapReferences(list,low,middle);
			}
			if (list[high]<list[low]) {
				swapReferences(list,low,high);
			}
			if (list[high]<list[middle]) {
				swapReferences(list,middle,high);
			}

			swapReferences(list,middle,high-1);
			int pivot = list[low+1];

			int i,j;
			for (i=low,j=high-1;;) {
				while (list[++i]<pivot) {
					;
				}
				while (pivot<list[--j]) {
					;
				}
				if (i>=j) {
					break;
				}
				swapReferences(list,i,j);
			}
			swapReferences(list,i,high-1);
			quickSort(list,low,i-1);
			quickSort(list,i+1,high);
		}
	}
	
	public static void swapReferences( int [ ] list, int index1, int index2 )
    {
        int tmp = list[ index1 ];
        list[ index1 ] = list[ index2 ];
        list[ index2 ] = tmp;
    }

	private static void insertionSort( int [ ] list, int low, int high )
    {
        for( int p = low + 1; p <= high; p++ )
        {
            int tmp = list[ p ];
            int j;

            for( j = p; j > low && tmp<list[ j - 1 ]; j-- )
                list[ j ] = list[ j - 1 ];
            list[ j ] = tmp;
        }
    }
}