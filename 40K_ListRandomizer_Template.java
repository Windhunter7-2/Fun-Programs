import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class 40K_ListRandomizer_Template {
	
	//The Units Themselves
	
	/**
	 * Points Cost of Each Unit
	 */
	public class Costs
	{
		public int ExampleUnit = 240;
	}
	
	/**
	 * How Many of Each Unit
	 */
	public class Counts
	{
		//Total Counts
		public int ExampleUnit;
		
		public Counts()
		{
			ExampleUnit = 0;
		}
	}
	
	/**
	 * Adding Units; Each "add_" Method Below Adds the Respective Unit Type, if Allowed
	 */
	public class Unit
	{
		public int pointsSpent;	//How Many Points Spent So Far
		public String armyList;	//Army List So Far
		public int maxCount;	//How Many Units Allowed in List (i.e. 2 for 0-1000, 3 for 1001+)
		public boolean tanksAllowed;	//Whether Or Not Vehicles with High Toughness (e.g. Tanks) Are Allowed in the Battle
		public String faction;	//The Faction Being Used
		private Counts counts;	//How Many of Each Unit
		private Costs costs;	//Points Cost of Each Unit
		private Index index;	//The Index Used for Randomizing Recurring Types (e.g. Tanks)
		
		public Unit()
		{
			counts = new Counts();
			costs = new Costs();
			index = new Index();
			pointsSpent = 0;
			armyList = "";
			maxCount = 2;
			tanksAllowed = false;
			faction = "";
		}
		
		public void add_ExampleUnit()
		{
      //NOTE: Here Is What Each of These if Clauses Does:
      //(counts.UnitName<maxCount OR counts.UnitName<1) -> See if Max Number of This Unit Type Are Already in the List (e.g. 3 for 1500 Army Lists); 1 Instead,
                  //if Unique Characters
      //(pointsSpent <= (pointsAllotted-SomeValue)) -> The *Maximum* Value This Unit Can Be, if and Only if Its Maximum Value Is *Greater* Than 100; if the Unit's
                  //Cost Can Never Be Over 100, That Part Can Just Be Deleted
      //(tanksAllowed == true) -> Only Required for Tanks, Aircraft, and Other Units Not Allowed in 0-1000 Points Lists
      //((faction.equals(FactionA))||(faction.equals(FactionB))||(faction.equals(FactionC))) ) -> Add This Unit *Only* if One of These Particular Factions Is Chosen
      
			if ( (counts.ExampleUnit<maxCount) && (pointsSpent <= (pointsAllotted-300)) && (tanksAllowed == true)
					&& ((faction.equals(FactionA))||(faction.equals(FactionB))||(faction.equals(FactionC))) )
			{
				int exampleType = index.randomizeExample();
				int cost = (costs.ExampleUnit + index.example_costs[exampleType]);
				String name = ("The Name of the Unit: Items You Defaultly Give It" + index.example_types[exampleType]);
				int itemGet = roll(4);
				if (itemGet != 1)	//75% Chance of Getting That Upgrade You Like to Use a Lot but Don't Want to Always Use
				{
					cost = (cost + 5); //The Cost of That Upgrade for the Unit
					name = (name + ", the Upgrade for the Unit Listed Here");
				}
				counts.ExampleUnit++;
        
        //From Here...
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
        //To Here, You *Never* Need to Edit; These Particular Lines Are for the Concatenation Parts!
        
				slots.HQ++; //Replace "HQ" with Whichever Slot the Unit (Or Group of Units) This Takes Up
			}
		}
	}
	
	/**
	 * Index - The Variants of Special Weapons, Tanks, etc.
	 */
	public class Index
	{
		public int [] example_costs;
		public String [] example_types;
		private int [] example_used;
		
		public Index()
		{
			example_costs = new int[2];
			example_types = new String[2];
			example_used = new int[2];
			for (int i = 0; i < 2; i++)
				example_used[i] = (-1);
			example_costs[0] = 55;
			example_types[0] = "Example Upgrade A";
			example_costs[1] = 45;
			example_types[1] = "Example Upgrade B";
		}
		
		/**
		 * Randomize Which Example Tank to Use; "Use Up" the Tank for Each, Since They're Unique Tank Models Each
		 * @return The Index Mapping for Which Example Tank to Use
		 */
		public int randomizeExample()
		{
			int result = roll(2);
			if (result == 1)
			{
				if (example_used[0] == 1)
					return randomizeExample();
				else
				{
					example_used[0] = 1;
					return 0;
				}
			}
			if (result == 2)
			{
				if (example_used[1] == 1)
					return randomizeExample();
				else
				{
					example_used[1] = 1;
					return 1;
				}
			}
			else
				return -1;
		}
	}
	
	
	
	
	
	
	/**
	 * The Sub-Lists, AKA the Lists of Units to Roll for
	 */
	public class Lists
	{
		/**
		 * Selects an HQ Choice from Its List
		 * @param num The Choice
		 */
		public void getHQ(int num)
		{
			if ( (num >= 1) && (num <= 10) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects a T Choice from Its List
		 * @param num The Choice
		 */
		public void getT(int num)
		{
			if ( (num >= 1) && (num <= 10) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects an E Choice from Its List
		 * @param num The Choice
		 */
		public void getE(int num)
		{
			if ( (num >= 1) && (num <= 2) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects a FA Choice from Its List
		 * @param num The Choice
		 */
		public void getFA(int num)
		{
			if ( (num >= 3) && (num <= 4) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects an HS Choice from Its List
		 * @param num The Choice
		 */
		public void getHS(int num)
		{
			if ( (num >= 5) && (num <= 6) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects a Transport Choice from Its List
		 * @param num The Choice
		 */
		public void getTransport(int num)
		{
			if ( (num >= 7) && (num <= 8) )
				units.add_ExampleUnit();
		}
		
		/**
		 * Selects a Flyer Choice from Its List
		 * @param num The Choice
		 */
		public void getFlyer(int num)
		{
			if ( (num >= 9) && (num <= 10) )
				units.add_ExampleUnit();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	//The Functionality of the Randomizer
	
	/**
	 * How Many Slots of Each Detachment Have Been Used Up
	 */
	public class Slots
	{
		//The Counts Themselves
		public int HQ;
		public int T;
		public int E;
		public int FA;
		public int HS;
		public int Flyer;
		
		//The Max Numbers of Slots
		public int HQ_Min;
		public int HQ_Max;
		public int T_Min;
		public int T_Max;
		public int E_Max;
		public int FA_Max;
		public int HS_Max;
		public int Flyer_Max;
		
		public Slots()
		{
			//Current Counts
			HQ = 0;
			T = 0;
			E = 0;
			FA = 0;
			HS = 0;
			Flyer = 0;
			
			//Patrol Detachment Maxes
			HQ_Min = 1;
			HQ_Max = 2;
			T_Min = 1;
			T_Max = 3;
			E_Max = 2;
			FA_Max = 2;
			HS_Max = 2;
			Flyer_Max = 2;
		}
	}
	
	/**
	 * The Factions
	 */
	public String FactionA = "Example Faction 1";
	public String FactionB = "Example Faction 2";
	public String FactionC = "Example Faction 3";
	
	/**
	 * How Many Points Allowed for the Army List
	 */
	public int pointsAllotted;
	
	/**
	 * The Time Before Emergency Timeout Occurs; Currently 3 Seconds
	 */
	public long const_time = 3000;
	
	/**
	 * The Classes for All the References
	 */
	public Unit units;
	public Counts maxes;
	public Slots slots;
	
	public void createArmyList()
	{
		//Initialization
		units = new Unit();
		maxes = new Counts();
		slots = new Slots();
		
		//Set Requirements Based on Inputted Points
		if (pointsAllotted > 1000)
		{
			//ONLY 1001+ Points Lists... -> Battalion Detachment Maxes
			units.maxCount = 3;
			units.tanksAllowed = true;
			slots.HQ_Min = 2;
			slots.HQ_Max = 3;
			slots.T_Min = 3;
			slots.T_Max = 6;
			slots.E_Max = 6;
			slots.FA_Max = 3;
			slots.HS_Max = 3;
		}
		
		//Choose Faction
		int faction = roll(3);
		if (faction == 1)
			units.faction = FactionA;
		if (faction == 2)
			units.faction = FactionB;
		if (faction == 3)
			units.faction = FactionC;
		
		//Do Core Army Building
		Lists lists = new Lists();
		while (slots.HQ < slots.HQ_Min)
		{
			int choice = roll(10);
			lists.getHQ(choice);
		}
		while (slots.T < slots.T_Min)
		{
			int choice = roll(10);
			lists.getT(choice);
		}
		
		//Do Rest of Army Building
		long originalTime = System.currentTimeMillis();
		while ( units.pointsSpent < (pointsAllotted - 100) )
		{
			//Emergency Timer Exit
			long curTime = (System.currentTimeMillis() - originalTime);
			if (curTime > const_time)	//If Longer Than CONSTANT Time (See Top), Stop Looping
				break;
			
			//Army List Construction
			int choice = roll(30);
			if ( (choice >= 1) && (choice <= 10) && (slots.HQ < slots.HQ_Max) )
				lists.getHQ(choice);
			if ( (choice >= 11) && (choice <= 20) && (slots.T < slots.T_Max) )
				lists.getT(choice - 10);
			choice = (choice - 20);
			if ( (choice >= 1) && (choice <= 2) && (slots.E < slots.E_Max) )
				lists.getE(choice);
			if ( (choice >= 3) && (choice <= 4) && (slots.FA < slots.FA_Max) )
				lists.getFA(choice);
			if ( (choice >= 5) && (choice <= 6) && (slots.HS < slots.HS_Max) )
				lists.getHS(choice);
			if ( (choice >= 7) && (choice <= 8) )
				lists.getTransport(choice);
			if ( (choice >= 9) && (choice <= 10) && (slots.Flyer < slots.Flyer) )
				lists.getFlyer(choice);
		}
	}

	/**
	 * Start the Program
	 * @throws IOException
	 */
	public void startProgram() throws IOException
	{
		//The GUI JOptionPanes
		String gui_text_pts = ("How many points should this army list be?");
		String input_pts = JOptionPane.showInputDialog(gui_text_pts);
		pointsAllotted = Integer.parseInt(input_pts);
		String gui_text_lists = ("How many of these " + pointsAllotted + " points army lists are you creating?");
		String input_lists = JOptionPane.showInputDialog(gui_text_lists);
		int numLists = Integer.parseInt(input_lists);
		
		//File Outputs
		String location = "C:/Users/Public/Desktop/40KArmyList_";
		location = (location + pointsAllotted + "Pts_" + numLists + "Lists.txt");
		File f = new File(location);
		f.createNewFile(); //Creates a new file if necessary.
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));	
		for (int i = 0; i < numLists; i++)
		{
			//Army List Full Creation
			System.out.println( "Creating Army List #" + (i+1) );
			createArmyList();
			String output = ("Points:" + units.pointsSpent + " - List #" + (i+1) + "\n\nFaction: "
					+ units.faction + "\n" + units.armyList + "\n\n");
			
			//File Printing
			bw.append(output);
		}
		bw.close();
		System.out.println("\n\nAll Army Lists Created!");
	}

	/**
	 * Main Method; Run the Program
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		ListRandomizer randomizer = new ListRandomizer();
		randomizer.startProgram();
	}
	
	/**
	 * Dice Roller
	 * @param diceType The Type of Dice to Roll (e.g. 6 for D6, 10 for D10, etc.)
	 * @return The Number Rolled on the Dice
	 */
	public int roll(int diceType)
	{
		return (int) Math.floor(Math.random() * diceType) + 1;
	}
	
}
