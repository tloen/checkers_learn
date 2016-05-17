import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TrainingSet
{

	private static final int SETS = 3;
	private static final int SAMPLES = 50;

	public static void main(String[] args)
	{
		try
		{
			for (int j = 0; j < SETS; ++j)
			{
				PrintStream p = new PrintStream(new File(nextFile()));
				p.println((SAMPLES) + " " + 66 + " " + 1);
				Board b = new Board();
				for (int i = 0; i < SAMPLES; ++i)
				{
					p.print(b.toData() + " ");
					b = b.randomMove();
					p.println(b.toData());
					p.println("1");
				}
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("There was an error: " + ex.getMessage());
		}
	}

	private static String nextFile()
	{
		String fileName = "TrainingSet";
		int num = 0;
		while (true)
		{
			if ((new File(fileName + num++ + ".txt")).exists())
				continue;
			return fileName + --num + ".txt";
		}
	}
}
