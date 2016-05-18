<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Arrays;

public class Board
{

	// --------->
	// |
	// ---....---
	// |
	// ----------

	public static final char EMPTY = '.', RED = 'r', BLACK = 'b',
			RED_KING = 'R', BLACK_KING = 'B';

	// b[32] is the turn
	private char[] b = new char[33];

	public static int flatten(int x, int y)
	{
		return 4 * y + ((y % 2 == 0) ? (x / 2) : ((7 - x) / 2));
	}

	public static int fattenX(int n)
	{
		return ((n / 4) % 2 == 0) ? 2 * (n % 4) : 7 - (2 * (n % 4));
	}

	public static int fattenY(int n)
	{
		return n / 4;
	}

	// straight vector
	public char square(int n)
	{
		return b[n];
	}

	// x is horizontal, y is vertical
	public char square(int x, int y)
	{
		if ((x + y) % 2 != 0)
			return EMPTY;
		return b[flatten(x, y)];
	}

	public Board()
	{
		for (int i = 0; i < 8; ++i)
			b[i] = RED;
		for (int i = 8; i < 24; ++i)
			b[i] = EMPTY;
		for (int i = 24; i < 32; ++i)
			b[i] = BLACK;
		b[32] = RED;
	}

	public Board(char[] b)
	{
		this.b = b;
	}

	public static boolean wi(int i)
	{
		return i < 8 && i >= 0;
	}

	public static char toKing(char x)
	{
		if (x == RED)
			return RED_KING;
		else if (x == BLACK)
			return BLACK_KING;
		else
			return 0;
	}

	// TODO: implement double jumps
	public Board randomMove()
	{
		ArrayList<Board> branches = new ArrayList<Board>();

		char other = (b[32] == RED || b[32] == RED_KING) ? BLACK : RED;
		char otherKing = (b[32] == RED || b[32] == RED_KING) ? BLACK_KING
				: RED_KING;

		for (int i = 0; i < 32; ++i)
		{
			int x = fattenX(i), y = fattenY(i);
			if (square(x, y) == b[32])
			{
				int dy = 666;
				if (b[32] == RED)
					dy = 1;
				else if (b[32] == BLACK)
					dy = -1;
				for (int dx = -1; dx <= 1; dx += 2)
				{
					if (wi(x + dx) && wi(y + dy)
							&& b[flatten(x + dx, y + dy)] == EMPTY)
					{
						char[] b2 = Arrays.copyOf(b, 33);
						b2[flatten(x, y)] = EMPTY;
						b2[flatten(x + dx, y + dy)] = (!wi(y + 2 * dy))
								? toKing(b[32]) : b[32];
						b2[32] = other;
						branches.add(new Board(b2));
					}

					if (wi(x + 2 * dx) && wi(y + 2 * dy)
							&& (square(x + dx, y + dy) == other
									|| square(x + dx, y + dy) == otherKing)
							&& square(x + 2 * dx, y + dy) == EMPTY)
					{
						char[] b2 = Arrays.copyOf(b, 33);
						b2[flatten(x, y)] = b2[flatten(x + dx, y + dy)] = EMPTY;
						b2[flatten(x + 2 * dx, y + 2 * dy)] = (!wi(y + 3 * dy))
								? toKing(b[32]) : b[32];
						b2[32] = other;
						branches.add(new Board(b2));
					}
				}
			}
			else if (square(x, y) == toKing(b[32]))
			{
				for (int dx = -1; dx <= 1; dx += 2)
				{
					for (int dy = -1; dy <= 1; dy += 2)
					{
						if (wi(x + dx) && wi(y + dy)
								&& b[flatten(x + dx, y + dy)] == EMPTY)
						{
							char[] b2 = Arrays.copyOf(b, 33);
							b2[flatten(x, y)] = EMPTY;
							b2[flatten(x + dx, y + dy)] = toKing(b[32]);
							b2[32] = other;
							branches.add(new Board(b2));
						}

						if (wi(x + 2 * dx) && wi(y + 2 * dy)
								&& (square(x + dx, y + dy) == other
										|| square(x + dx, y + dy) == otherKing)
								&& square(x + 2 * dx, y + dy) == EMPTY)
						{
							char[] b2 = Arrays.copyOf(b, 33);
							b2[flatten(x,
									y)] = b2[flatten(x + dx, y + dy)] = EMPTY;
							b2[flatten(x + 2 * dx, y + 2 * dy)] = toKing(b[32]);
							b2[32] = other;
							branches.add(new Board(b2));
						}
					}
				}
			}

		}
		if (branches.isEmpty())
			return null;
		return branches.get((int) (branches.size() * Math.random()));
	}

	public String toString()
	{
		String r = "";
		for (int i = 7; i >= 0; --i)
		{
			for (int j = 0; j < 8; ++j)
				r += square(j, i);
			r += "\n";
		}
		return r;
	}

	public String toData()
	{
		String ret = "" + b[0];
		for (int i = 1; i < b.length; ++i)
			ret += " " + b[i];
		return ret;
	}
	
	public static void main(String[] args)
	{
		Board b = new Board();
		for (int i = 0; i < 50; ++i)
		{
			System.out.print(b);
			b = b.randomMove();
			System.out.print("===========\n");
		}
	}
=======
import java.util.ArrayList;
import java.util.Arrays;

public class Board
{

	// --------->
	// |
	// ---....---
	// |
	// ----------

	public static final char EMPTY = '1', RED = '2', BLACK = '3',
			RED_KING = '4', BLACK_KING = '5';

	// b[32] is the turn
	private char[] b = new char[33];
	private ArrayList<Board> branches;

	public static int flatten(int x, int y)
	{
		return 4 * y + ((y % 2 == 0) ? (x / 2) : ((7 - x) / 2));
	}

	public static int fattenX(int n)
	{
		return ((n / 4) % 2 == 0) ? 2 * (n % 4) : 7 - (2 * (n % 4));
	}

	public static int fattenY(int n)
	{
		return n / 4;
	}

	// straight vector
	public char square(int n)
	{
		return b[n];
	}

	// x is horizontal, y is vertical
	public char square(int x, int y)
	{
		if ((x + y) % 2 != 0)
			return EMPTY;
		return b[flatten(x, y)];
	}

	public Board()
	{
		for (int i = 0; i < 8; ++i)
			b[i] = RED;
		for (int i = 8; i < 24; ++i)
			b[i] = EMPTY;
		for (int i = 24; i < 32; ++i)
			b[i] = BLACK;
		b[32] = RED;
	}

	public Board(char[] b)
	{
		this.b = b;
	}

	public static boolean wi(int i)
	{
		return i < 8 && i >= 0;
	}

	public static char toKing(char x)
	{
		if (x == RED)
			return RED_KING;
		else if (x == BLACK)
			return BLACK_KING;
		else
			return 0;
	}

	// TODO: implement double jumps
	public Board randomMove()
	{
		branches = new ArrayList<Board>();

		char other = (b[32] == RED || b[32] == RED_KING) ? BLACK : RED;
		char otherKing = (b[32] == RED || b[32] == RED_KING) ? BLACK_KING
				: RED_KING;

		for (int i = 0; i < 32; ++i)
		{
			int x = fattenX(i), y = fattenY(i);
			if (square(x, y) == b[32])
			{
				int dy = 666;
				if (b[32] == RED)
					dy = 1;
				else if (b[32] == BLACK)
					dy = -1;
				for (int dx = -1; dx <= 1; dx += 2)
				{
					if (wi(x + dx) && wi(y + dy)
							&& b[flatten(x + dx, y + dy)] == EMPTY)
					{
						char[] b2 = Arrays.copyOf(b, 33);
						b2[flatten(x, y)] = EMPTY;
						b2[flatten(x + dx, y + dy)] = (!wi(y + 2 * dy))
								? toKing(b[32]) : b[32];
						b2[32] = other;
						branches.add(new Board(b2));
					}

					if (wi(x + 2 * dx) && wi(y + 2 * dy)
							&& (square(x + dx, y + dy) == other
									|| square(x + dx, y + dy) == otherKing)
							&& square(x + 2 * dx, y + dy) == EMPTY)
					{
						char[] b2 = Arrays.copyOf(b, 33);
						b2[flatten(x, y)] = b2[flatten(x + dx, y + dy)] = EMPTY;
						b2[flatten(x + 2 * dx, y + 2 * dy)] = (!wi(y + 3 * dy))
								? toKing(b[32]) : b[32];
						b2[32] = other;
						branches.add(new Board(b2));
					}
				}
			}
			else if (square(x, y) == toKing(b[32]))
			{
				for (int dx = -1; dx <= 1; dx += 2)
				{
					for (int dy = -1; dy <= 1; dy += 2)
					{
						if (wi(x + dx) && wi(y + dy)
								&& b[flatten(x + dx, y + dy)] == EMPTY)
						{
							char[] b2 = Arrays.copyOf(b, 33);
							b2[flatten(x, y)] = EMPTY;
							b2[flatten(x + dx, y + dy)] = toKing(b[32]);
							b2[32] = other;
							branches.add(new Board(b2));
						}

						if (wi(x + 2 * dx) && wi(y + 2 * dy)
								&& (square(x + dx, y + dy) == other
										|| square(x + dx, y + dy) == otherKing)
								&& square(x + 2 * dx, y + dy) == EMPTY)
						{
							char[] b2 = Arrays.copyOf(b, 33);
							b2[flatten(x,
									y)] = b2[flatten(x + dx, y + dy)] = EMPTY;
							b2[flatten(x + 2 * dx, y + 2 * dy)] = toKing(b[32]);
							b2[32] = other;
							branches.add(new Board(b2));
						}
					}
				}
			}

		}
		if (branches.isEmpty())
			return null;
		return branches.get((int) (branches.size() * Math.random()));
	}

	public boolean equals(Board b)
	{
		return toString().equals(b.toString());
	}
	
	public boolean contains(Board b)
	{
		for (Board B : branches)
			if (B.equals(b)) {
				return true;
			}
		return false;
	}
	
	@Override
	public String toString()
	{
		String r = "";
		for (int i = 7; i >= 0; --i)
		{
			for (int j = 0; j < 8; ++j)
				r += square(j, i);
			r += "\n";
		}
		return r;
	}

	public String toData()
	{
		String ret = "" + b[0];
		for (int i = 1; i < b.length; ++i)
			ret += " " + b[i];
		return ret;
	}

	public void switchColor()
	{
		switchColor(32);
	}

	public void switchColor(int n)
	{
		switch (b[n])
		{
			case RED:
				b[n] = BLACK;
				break;
			case RED_KING:
				b[n] = BLACK_KING;
				break;
			case BLACK:
				b[n] = RED;
				break;
			case BLACK_KING:
				b[n] = RED_KING;
				break;
			default: switchColor((int) Math.random() * 32);
		}
	}
	
	public void switchType(int n)
	{
		switch (b[n])
		{
			case RED:
				b[n] = RED_KING;
				break;
			case RED_KING:
				b[n] = RED;
				break;
			case BLACK:
				b[n] = BLACK_KING;
				break;
			case BLACK_KING:
				b[n] = BLACK;
				break;
			default: switchType((int) Math.random() * 32);
		}
	}
	
	public void randomChange()
	{
		int n = (int) (Math.random() * 33);
//		System.out.println("b[" + n + "] = " + b[n]);
		if (n == 32 && b[n] == RED)
			b[n] = BLACK;
		else if (n == 32 && b[n] == BLACK)
			b[n] = RED;
		else
		{
			switch ((int) (Math.random() * 5))
			{
				case 0:
					b[n] = RED;
					break;
				case 1:
					b[n] = RED_KING;
					break;
				case 2:
					b[n] = BLACK;
					break;
				case 3:
					b[n] = BLACK_KING;
					break;
				case 4:
					b[n] = EMPTY;
					break;
			}
		}
//		System.out.println("b[" + n + "] = " + b[n]);
	}
	
	@Override
	public Board clone()
	{
		return new Board(Arrays.copyOf(b, b.length));
	}
	
	public static void main(String[] args)
	{
		Board b = new Board();
		for (int i = 0; i < 50; ++i)
		{
			System.out.print(b);
			b = b.randomMove();
			System.out.print("===========\n");
		}
	}
>>>>>>> origin/master
}