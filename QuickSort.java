

public class QuickSort
{
	public volatile static int changing;
	public volatile static int beChanged;

	
	public static void sort(final int[] aList)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				
				sort(aList, 0, aList.length - 1);
			}
		}.start();
	}

	
	private static void sort(int[] a, int lo, int hi)
	{
		if (lo >= hi)
			return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}

	
	private static int partition(int[] aList, int lo, int hi)
	{
		int i = lo, j = hi + 1;
		int v = aList[lo];
		while (true)
		{
			while (aList[++i] < v)
				if (i == hi)
					break;
			while (v< aList[--j])
				if (j == lo)
					break;
			if (i >= j)
				break;
			try
			{
				beChanged = i;
				changing = j;
				Thread.sleep(10);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exchange(aList, i, j);
		}
		try
		{
			beChanged = lo;
			changing = j;
			Thread.sleep(10);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exchange(aList, lo, j);
		return j;
	}


	private static void exchange(int[] aList, int i, int j)
	{
		int tmp = aList[i];
		aList[i] = aList[j];
		aList[j] = tmp;
	}

}
