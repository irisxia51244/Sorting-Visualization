
public class SelectionSort
{
	public static volatile int changing;
	public static volatile int beChanged;

	// O(N^2)
	
	public static void sort(final int[] aList)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				
				for (int i = 0; i < aList.length - 1; i++)
				{
					int min = i;
					for (int j = i + 1; j < aList.length; j++)
						if (aList[j] < aList[min]){
							
							min = j;
							beChanged=i;
							changing=j;
							try{
							sleep(10);
							}
							catch(InterruptedException e){
								
							}
						}
					try
					{
						sleep(30);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					beChanged=i;
					changing=min;
					exchange(aList, i, min);
					
				}
			}
		}.start();
	}

	
	private static void exchange(int[] aList, int i, int j)
	{
		int tmp = aList[i];
		aList[i] = aList[j];
		aList[j] = tmp;
	}


}
