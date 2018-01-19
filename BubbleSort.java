
public class BubbleSort {
	public static volatile int changing;
	public static volatile int beChanged;
	
	public static void sort(final int[] aList)
	{
		new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < aList.length - 1; i++)
					for(int j = 1; j < aList.length; j++)
						if(aList[j] < aList [j-1])
						{
							if(!VisualPanel.keepRun)
								return;
							try
							{
								sleep(10);
							} catch(InterruptedException e)
							{
								e.printStackTrace();
							}
							beChanged = j;
							changing = j-1;
							exchange(aList, j-1, j);
						}
						
					};
				}.start();
			}
	
	       
			private static void exchange(int[] aList, int i, int j )
			{
				int temp = aList[i];
				aList[i] = aList[j];
				aList[j] = temp;
				}




		
	
}

