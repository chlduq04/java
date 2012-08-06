package chapter10;

public class chapter10 {
	public static void main(String[] args) {
		int a[] = { 71 ,14,31,7,10,50,30,22};
		
		
		System.out.printf("\n 정렬할 원소 :" );
		for(int i=0;i<a.length;i++){
			System.out.print(" "+a[i]);			
		}
		System.out.println();
		
//		BubbleSort bs = new BubbleSort();
//		bs.bubbleSort(a);
		
		MergeSort ms = new MergeSort(a);	
		ms.mergeSort(a, 0, a.length-1);
	}
}
