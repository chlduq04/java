package chapter10;

public class MergeSort {
	private int sorted[];

	public MergeSort(int a[]) {
		sorted = new int[a.length];
	}

	public void merge(int a[], int m, int middle, int n) {
		int size = a.length;
		int i, j, k, t;
		i = m;
		j = middle + 1;
		k = m;
		while (i <= middle && j <= n) {
			if (a[i] <= a[j])
				sorted[k] = a[i++];
			else
				sorted[k] = a[j++];
			k++;

		}
		if (i > middle) {
			for (t = j; t <= n; t++, k++)
				sorted[k] = a[t];
		} else {
			for (t = i; t <= middle; t++, k++)
				sorted[k] = a[t];

		}

		for (t = m; t <= n; t++)
			a[t] = sorted[t];
		System.out.printf("\n 병합 정렬 >> ");
		for (t = 0; t < size; t++)
			System.out.print(" " + a[t]);
	}

	public void mergeSort(int a[], int m, int n) {
		int middle;
		if (m < n) {
			middle=(m+n)/2;
			mergeSort(a,m, middle);
			mergeSort(a, middle+1, n);
			merge(a,m,middle,n);

		}
	}

}
