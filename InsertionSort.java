
public class InsertionSort
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
				
				for (int i = 1; i < aList.length; i++)
					for (int j = i; j > 0; j--)
						if (aList[j] < aList[j - 1])
						{
							if (!VisualPanel.keepRun)
								return;
							try
							{
								sleep(10);
							} catch (InterruptedException e)
							{
								e.printStackTrace();
							}
							beChanged = i;
							changing = j - 1;
							exchange(aList, j, j - 1);
						}
			};
		}.start();
	}

	
	private static void exchange(int[] aList, int i, int j)
	{
		int tmp = aList[i];
		aList[i] = aList[j];
		aList[j] = tmp;
	}



}
