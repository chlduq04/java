package chapter10;

public class BubbleSort {
	public void bubbleSort(int a[]){
		int i , j, temp, size;
		size = a.length;
		for(i=(size-1);i>0 ;i--){
			System.out.print("\n ���� ���� "+(size-i)+"�ܰ� :" );
			System.out.println("");

			for(j=0;j<i;j++)
			{
				if(a[j]>a[j+1])
				{
					temp = a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}

				for(int k=0;k<a.length;k++)
				{
					System.out.print(a[k]+" ");
				}
				System.out.println("");
			}
		}
	}
}
