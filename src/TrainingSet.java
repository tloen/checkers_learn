import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TrainingSet
{

	private static final int SETS = 3;
	private static final int CORRECT_SAMPLES = 40;
	private static final int NEAR_SAMPLES = 30;
	private static final int FAR_SAMPLES = 30;
	private static final int SAMPLES = CORRECT_SAMPLES + NEAR_SAMPLES
			/*+ FAR_SAMPLES*/;

	public static void main(String[] args)
	{
		try
		{
			for (int i = 0; i < SETS; ++i)
			{
				PrintStream p = new PrintStream(new File(nextFile()));
				p.println((SAMPLES) + " " + 66 + " " + 1);
				validSamples(p);
				nearSamples(p);
			}
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("There was an error: " + ex.getMessage());
		}
	}
	
	public static void nearSamples(PrintStream p)
	{
		Board b = new Board();
		for (int i = 0; i < NEAR_SAMPLES; ++i)
		{
			Board b2 = b.randomMove().clone();
			for (int j = (int) Math.random() * 3 + 1; j >= 0; --j)
				b2.randomChange();
			if (b.contains(b2)) {
				--i;
			}
			else
			{
				p.print(b.toData() + " ");
				b = b2;
				p.println(b.toData());
				p.println("-1");
			}
		}
	}
	
	/*public static void nearSamples(PrintStream p)
	{
		Board b = new Board();
		for (int i = 0; i < NEAR_SAMPLES / 5; ++i) // one move by each player in one "move"
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			b = b.randomMove();
			b.switchColor();
			p.println(b.toData());
			p.println("-1");
		}
		b = new Board();
		for (int i = NEAR_SAMPLES / 5; i < 2 * NEAR_SAMPLES / 5; ++i) // two moves by one player in one "move"
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			b.switchColor();
			b = b.randomMove();
			p.println(b.toData());
			p.println("-1");
		}
		b = new Board();
		for (int i = 2 * NEAR_SAMPLES / 5; i < 3 * NEAR_SAMPLES / 5; ++i) // no change in player
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			b.switchColor();
			p.println(b.toData());
			p.println("-1");
			b.switchColor();
		}
		b = new Board();
		for (int i = 3 * NEAR_SAMPLES / 5; i < 4 * NEAR_SAMPLES / 5; ++i) // color switch
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			b.switchColor((int) Math.random() * 32);
			p.println(b.toData());
			p.println("-1");
		}
		b = new Board();
		for (int i = 4 * NEAR_SAMPLES / 5; i < NEAR_SAMPLES; ++i) // type switch
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			b.switchColor((int) Math.random() * 32);
			p.println(b.toData());
			p.println("-1");
		}
				
	}*/

	public static void validSamples(PrintStream p)
	{
		Board b = new Board();
		for (int i = 0; i < CORRECT_SAMPLES; ++i)
		{
			p.print(b.toData() + " ");
			b = b.randomMove();
			p.println(b.toData());
			p.println("1");
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
