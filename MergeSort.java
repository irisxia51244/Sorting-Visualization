
public class MergeSort
{
	public volatile static int changing = 0;
	public volatile static int beChanged;

	
	private static int[] aList;

	
	public static void sort(final int[] a)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				
				aList = new int[a.length];
				sort(a, 0, a.length - 1);
			}
		}.start();
	}

	
	private static void sort(int[] a, int lo, int hi)
	{

		if (hi <= lo)	
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	
	private static void merge(int[] a, int lo, int mid, int hi)
	{
		int i = lo;
		int j = mid + 1;

		for (int k = lo; k <= hi; k++)
			aList[k] = a[k];

		for (int k = lo; k <= hi; k++)
		{
			try
			{
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			beChanged = k;
			if (i > mid)
				a[k] = aList[j++];
			else if (j > hi)
				a[k] = aList[i++];
			else if (aList[j]<aList[i])
				a[k] = aList[j++];
			else
				a[k] = aList[i++];
		}
	}




}

