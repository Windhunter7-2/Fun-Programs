import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ListRandomizer {
	
	//The Units Themselves
	
	/**
	 * Points Cost of Each Unit
	 */
	public class Costs
	{
		public int Creed = 60;
		public int CompanyCommander = 40;
		public int TankCommander = 175;
		public int Pask = 200;
		public int Yarrick = 105;
		public int LordCommissar = 45;
		public int Straken = 80;
		public int TempestorPrime = 45;
		public int PrimarisPsyker = 50;
		public int Infantry = 60;
		public int Conscripts = 5;	//Each Individual!!!
		public int Kasrkins_Half = 45;
		public int Kasrkins_Full = 90;
		public int Bombardier = 35;
		public int PlatoonCommander = 25;
		public int CommandSquad = 30;
		public int SpecialWpnsSquad = 49;
		public int Veterans = 85;
		public int Harker = 55;
		public int KasrkinCmdSquad = 45;
		public int Techpriest = 35;
		public int Commissar = 25;
		public int FleetOfficer = 25;
		public int OgrBodyguard = 60;
		public int NorkDeddog = 60;
		public int Sentinels = 50;	//Each Individual!!!
		public int LemanRuss = 140;	//Each Individual!!!
		public int Wyvern = 135;	//Each Individual!!!
		public int HvyWpnsTeam = 65;
		public int Chimera = 70;
		public int Valkyrie = 165;	//Each Individual!!!
	}
	
	/**
	 * How Many of Each Unit
	 */
	public class Counts
	{
		//MAX Number (Because I Don't Have Enough Models)
		public int tank_LemanRuss = 7;
		public int tank_Chimera = 3;
		public int tank_Wyvern = 1;
		public int aircraft_Valkyrie = 1;
		
		//Total Counts
		public int Creed;
		public int CompanyCommander;
		public int TankCommander;
		public int Pask;
		public int Yarrick;
		public int LordCommissar;
		public int Straken;
		public int TempestorPrime;
		public int PrimarisPsyker;
		public int Infantry;
		public int Conscripts;
		public int Kasrkins_Unit;
		public int Bombardier;
		public int PlatoonCommander;
		public int CommandSquad;
		public int SpecialWpnsSquad;
		public int Veterans;
		public int Harker;
		public int KasrkinCmdSquad;
		public int Techpriest;
		public int Commissar;
		public int FleetOfficer;
		public int OgrBodyguard;
		public int NorkDeddog;
		public int Sentinels;
		public int LemanRuss;
		public int Wyvern;
		public int HvyWpnsTeam;
		public int Chimera;
		public int Valkyrie;
		
		public Counts()
		{
			Creed = 0;
			CompanyCommander = 0;
			TankCommander = 0;
			Pask = 0;
			Yarrick = 0;
			LordCommissar = 0;
			Straken = 0;
			TempestorPrime = 0;
			PrimarisPsyker = 0;
			Infantry = 0;
			Conscripts = 0;
			Kasrkins_Unit = 0;
			Bombardier = 0;
			PlatoonCommander = 0;
			CommandSquad = 0;
			SpecialWpnsSquad = 0;
			Veterans = 0;
			Harker = 0;
			KasrkinCmdSquad = 0;
			Techpriest = 0;
			Commissar = 0;
			FleetOfficer = 0;
			OgrBodyguard = 0;
			NorkDeddog = 0;
			Sentinels = 0;
			LemanRuss = 0;
			Wyvern = 0;
			HvyWpnsTeam = 0;
			Chimera = 0;
			Valkyrie = 0;
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
		public String regiment;	//The Regiment Being Used
		private Counts counts;	//How Many of Each Unit
		private Costs costs;	//Points Cost of Each Unit
		private Index index;	//The Index Used for Randomizing Recurring Types (e.g. Leman Russes)
		
		public Unit()
		{
			counts = new Counts();
			costs = new Costs();
			index = new Index();
			pointsSpent = 0;
			armyList = "";
			maxCount = 2;
			tanksAllowed = false;
			regiment = "";
		}
		
		public void addHQ_Creed()
		{
			if ( (counts.Creed<1) && (regiment.equals(Cadian)) )
			{
				int cost = costs.Creed;
				String name = "Lord Castellan Creed";
				counts.Creed++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_CompanyCommander()
		{
			if ( (counts.CompanyCommander<maxCount) && ((regiment.equals(Custom))||(regiment.equals(Cadian))
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn))) )
			{
				int cost = costs.CompanyCommander;
				String name = "Company Commander: Plasma Pistol";
				counts.CompanyCommander++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_TankCommander()
		{
			if ( (counts.TankCommander<maxCount) && (pointsSpent <= (pointsAllotted-240)) && (tanksAllowed == true)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int lemanRussType = index.randomizeLemanRuss();
				int cost = (costs.TankCommander + index.lemanRuss_costs[lemanRussType]);
				String name = ("Tank Commander: " + index.lemanRuss_types[lemanRussType]);
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ", Hunter-Killer Missile, Augur Array");
				}
				counts.TankCommander++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.LemanRusses_HQ++;
				slots.HQ++;
			}
		}
		
		public void addHQ_Pask()
		{
			if ( (counts.Pask<1) && (pointsSpent <= (pointsAllotted-265)) && (tanksAllowed == true)
					&& (regiment.equals(Cadian)) )
			{
				int lemanRussType = index.randomizeLemanRuss();
				int cost = (costs.Pask + index.lemanRuss_costs[lemanRussType]);
				String name = ("Knight Commander Pask: " + index.lemanRuss_types[lemanRussType]);
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ", Hunter-Killer Missile, Augur Array");
				}
				counts.Pask++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.LemanRusses_HQ++;
				slots.HQ++;
			}
		}
		
		public void addHQ_Yarrick()
		{
			if ( (counts.Yarrick<1) && (pointsSpent <= (pointsAllotted-105)) )
			{
				int cost = costs.Yarrick;
				String name = "Commissar Yarrick";
				counts.Yarrick++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_LordCommissar()
		{
			if (counts.LordCommissar<maxCount)
			{
				int cost = costs.LordCommissar;
				String name = "Lord Commissar: Plasma Pistol, Power Weapon";
				counts.LordCommissar++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_Straken()
		{
			if ( (counts.Straken<1) && (regiment.equals(Catchan)) )
			{
				int cost = costs.Straken;
				String name = "Colonel Iron Hand Straken";
				counts.Straken++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_TempestorPrime()
		{
			if ( (counts.TempestorPrime<maxCount) && (regiment.equals(Kasrkins)) )
			{
				int cost = costs.TempestorPrime;
				String name = "Tempestor Prime: ";
				counts.TempestorPrime++;
				int weapon = roll(6);
				if (weapon < 3)	//1-2
					name = (name + "Plasma Pistol");
				else	//3-6
					name = (name + "Tempestus Command Rod");
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addHQ_PrimarisPsyker()
		{
			if (counts.PrimarisPsyker<maxCount)
			{
				int cost = costs.PrimarisPsyker;
				String name = "Primaris Psyker";
				counts.PrimarisPsyker++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
			}
		}
		
		public void addT_Infantry()
		{
			if ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn)))
			{
				int cost = costs.Infantry;
				String name = "Infantry Squad: Vox-Caster";
				int specialWeapon = roll(2);
				if (specialWeapon == 2)	//50% for Special Weapon
				{
					int specialWeaponType = index.randomizeSpecialWeapon(0);
					cost = (cost + index.specialWeapons_costs[specialWeaponType]);
					name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
					if ( (specialWeaponType == 3) || (specialWeaponType == 4) )
						cost = (cost - 5);
				}
				counts.Infantry++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.T++;
			}
		}
		
		public void addT_Conscripts()
		{
			if ( (pointsSpent <= (pointsAllotted-150))
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int extraConscripts = (roll(11) - 1);
				int numConscripts = (20 + extraConscripts);
				int cost = (costs.Conscripts * numConscripts);
				String name = ("Conscripts: ") + numConscripts + " Guys";
				counts.Conscripts++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.T++;
			}
		}
		
		public void addT_Kasrkins_Half()
		{
			int cost = costs.Kasrkins_Half;
			String name = "Militarum Tempestus Scions: 1/2 Squad";
			int voxCaster = roll(2);
			if (voxCaster == 2)	//50% for Vox-Caster
			{
				cost = (cost + 5);
				name = (name + ", Vox-Caster");
			}
			for (int i = 0; i < 2; i++)
			{
				int specialWeapon = roll(2);
				if (specialWeapon == 2)	//50% for Special Weapon
				{
					int specialWeaponType = index.randomizeSpecialWeapon(1);
					cost = (cost + index.specialWeapons_costs[specialWeaponType]);
					name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
				}
			}
			counts.Kasrkins_Unit++;
			pointsSpent = (pointsSpent + cost);
			name = (name + " -> " + cost + " Points");
			armyList = (armyList + name + "\n");
			System.out.println("Added: " + name);
			slots.T++;
		}
		
		public void addT_Kasrkins_Full()
		{
			if ( (pointsSpent <= (pointsAllotted-135)) && (regiment.equals(Kasrkins)) )
			{
				int cost = costs.Kasrkins_Full;
				String name = "Militarum Tempestus Scions: Full Squad";
				int voxCaster = roll(2);
				if (voxCaster == 2)	//50% for Vox-Caster
				{
					cost = (cost + 5);
					name = (name + ", Vox-Caster");
				}
				for (int i = 0; i < 4; i++)
				{
					int specialWeapon = roll(2);
					if (specialWeapon == 2)	//50% for Special Weapon
					{
						int specialWeaponType = index.randomizeSpecialWeapon(1);
						cost = (cost + index.specialWeapons_costs[specialWeaponType]);
						name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
					}
				}
				counts.Kasrkins_Unit++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.T++;
			}
		}
		
		public void add_Bombardier()
		{
			if ( (counts.Bombardier<maxCount) && ((regiment.equals(Custom))||(regiment.equals(Cadian))
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn))) )
			{
				int cost = costs.Bombardier;
				String name = "Master of Ordnance";
				counts.Bombardier++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_PlatoonCommander()
		{
			if ( (counts.PlatoonCommander<maxCount) && ((regiment.equals(Custom))||(regiment.equals(Cadian))
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn)))
					&& ((counts.PlatoonCommander+(counts.CompanyCommander*2)+(counts.Creed*3)) <
					(counts.HvyWpnsTeam+counts.Bombardier+counts.Infantry+counts.Veterans)) )
			{
				int cost = costs.PlatoonCommander;
				String name = "Platoon Commander";
				counts.PlatoonCommander++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_CommandSquad()
		{
			if ( (counts.CommandSquad<maxCount)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int cost = costs.CommandSquad;
				String name = "Command Squad: Medi-Pack";
				int voxCaster = roll(4);
				int heavyFlamer = roll(2);
				int numWeapons = 4;
				if (voxCaster != 1)	//75% for Vox-Caster
				{
					cost = (cost + 5);
					name = (name + ", Vox-Caster");
					numWeapons--;
				}
				if (heavyFlamer == 2)	//50% for Heavy Flamer
				{
					cost = (cost + 10);
					name = (name + ", Heavy Flamer");
					numWeapons--;
				}
				for (int i = 0; i < numWeapons; i++)
				{
					int specialWeapon = roll(4);
					if (specialWeapon != 1)	//75% for Special Weapon
					{
						int specialWeaponType = index.randomizeSpecialWeapon(0);
						cost = (cost + index.specialWeapons_costs[specialWeaponType]);
						name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
					}
				}
				counts.CommandSquad++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_SpecialWpnsSquad()
		{
			if ( (counts.SpecialWpnsSquad<maxCount) && ((regiment.equals(Custom))||(regiment.equals(Cadian))
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn))) )
			{
				int cost = costs.SpecialWpnsSquad;
				String name = "Special Weapons Squad: 3 ";
				counts.SpecialWpnsSquad++;
				int weapon = roll(8);
				if ( (weapon == 1) || (weapon == 2) )
					name = (name + "Flamers");
				if (weapon == 3)
					name = (name + "Grenade Launchers");
				if ( (weapon == 4) || (weapon == 5) )
					name = (name + "Melta Guns");
				if ( (weapon >= 6) && (weapon <= 8) )
					name = (name + "Plasma Guns");
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Veterans()
		{
			if ( (counts.Veterans<maxCount) && (pointsSpent <= (pointsAllotted-115))
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int cost = costs.Veterans;
				String name = "Veterans: Vox-Caster, Heavy Flamer, Plasma Pistol";
				for (int i = 0; i < 3; i++)
				{
					int specialWeapon = roll(4);
					if (specialWeapon != 1)	//75% for Special Weapon
					{
						int specialWeaponType = index.randomizeSpecialWeapon(0);
						cost = (cost + index.specialWeapons_costs[specialWeaponType]);
						name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
					}
				}
				counts.Veterans++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Harker()
		{
			if ( (counts.Harker<1) && (regiment.equals(Catchan)) )
			{
				int cost = costs.Harker;
				String name = "Gunnery Sergeant Harker";
				counts.Harker++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_KasrkinCmdSquad()
		{
			if ( (counts.KasrkinCmdSquad<maxCount) && (regiment.equals(Kasrkins)) )
			{
				int cost = costs.KasrkinCmdSquad;
				String name = "Militarum Tempestus Command Squad: Medi-Pack";
				int voxCaster = roll(4);
				int numWeapons = 4;
				if (voxCaster != 1)	//75% for Vox-Caster
				{
					cost = (cost + 5);
					name = (name + ", Vox-Caster");
					numWeapons--;
				}
				for (int i = 0; i < numWeapons; i++)
				{
					int specialWeapon = roll(4);
					if (specialWeapon != 1)	//75% for Special Weapon
					{
						int specialWeaponType = index.randomizeSpecialWeapon(1);
						cost = (cost + index.specialWeapons_costs[specialWeaponType]);
						name = (name + ", " + index.specialWeapons_types[specialWeaponType]);
					}
				}
				counts.KasrkinCmdSquad++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Techpriest()
		{
			if (counts.Techpriest<maxCount)
			{
				int cost = costs.Techpriest;
				String name = "Techpriest Enginseer";
				counts.Techpriest++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Commissar()
		{
			if (counts.Commissar<maxCount)
			{
				int cost = costs.Commissar;
				String name = "Commissar";
				counts.Commissar++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_FleetOfficer()
		{
			if (counts.FleetOfficer<maxCount)
			{
				int cost = costs.FleetOfficer;
				String name = "Officer of the Fleet";
				counts.FleetOfficer++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_OgrBodyguard()
		{
			if (counts.OgrBodyguard<maxCount)
			{
				int cost = costs.OgrBodyguard;
				String name = "Ogryn Bodyguard: Bullgryn Plate, Bullgryn Maul, Brute Shield";
				counts.OgrBodyguard++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_NorkDeddog()
		{
			if (counts.NorkDeddog<1)
			{
				int cost = costs.NorkDeddog;
				String name = "Nork Deddog";
				counts.NorkDeddog++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Sentinels()
		{
			if ( (counts.Sentinels<maxCount) && (pointsSpent <= (pointsAllotted-165))
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int numSentinels = roll(3);
				String name = ("Armored Sentinels Squadron: " + numSentinels + " Sentinels, Each with ");
				int whichWeapon = roll(2);
				if (whichWeapon == 1)
					name = (name + "Missile Launchers");
				else
					name = (name + "Lascannons");
				int cost = (costs.Sentinels * numSentinels);
				int HKM = roll(2);
				if (HKM == 2)	//50% Chance of Getting HKM
				{
					cost = ( cost + (5 * numSentinels) );
					name = (name + ", Hunter-Killer Missile");
				}
				counts.Sentinels++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.FA++;
			}
		}
		
		public void add_LemanRuss()
		{
			if ( (counts.LemanRuss<(maxCount*3)) && (pointsSpent <= (pointsAllotted-205)) && (tanksAllowed == true)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int lemanRussType = index.randomizeLemanRuss();
				int cost = (costs.LemanRuss + index.lemanRuss_costs[lemanRussType]);
				String name = index.lemanRuss_types[lemanRussType];
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ", Hunter-Killer Missile, Augur Array");
				}
				counts.LemanRuss++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				if (slots.LemanRusses == 0)
					slots.HS++;
				slots.LemanRusses++;
				if (slots.LemanRusses >= 3)
				{
					slots.LemanRusses = 0;
				}
			}
		}
		
		public void add_Wyvern()
		{
			if ( (counts.Wyvern<(maxCount*3)) && (pointsSpent <= (pointsAllotted-145)) && (tanksAllowed == true)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int cost = costs.Wyvern;
				String name = "Wyvern";
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ": Hunter-Killer Missile, Augur Array");
				}
				counts.Wyvern++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				if (slots.Wyverns == 0)
					slots.HS++;
				slots.Wyverns++;
				if (slots.Wyverns >= 3)
				{
					slots.Wyverns = 0;
				}
			}
		}
		
		public void add_HvyWpnsTeam()
		{
			if ( (counts.HvyWpnsTeam<maxCount) && ((regiment.equals(Custom))||(regiment.equals(Cadian))
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn))) )
			{
				int cost = costs.HvyWpnsTeam;
				String name = "Heavy Weapons Team: ";
				counts.HvyWpnsTeam++;
				for (int i = 0; i < 3; i++)
				{
					int whichWeapon = roll(2);
					if (whichWeapon == 1)
						name = (name + "(" + i + ") Missile Launcher");
					else
						name = (name + "(" + i + ") Lascannon");
					if (i < 2)
						name = (name + ", ");
				}
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HS++;
			}
		}
		
		public void add_Chimera()
		{
			if ( (counts.Chimera< (counts.CommandSquad+counts.HvyWpnsTeam+counts.Infantry+counts.SpecialWpnsSquad+counts.Veterans
					+counts.Bombardier+counts.Commissar+counts.CompanyCommander+counts.Creed+counts.FleetOfficer+counts.Harker
					+counts.LordCommissar+counts.NorkDeddog+counts.OgrBodyguard+counts.PlatoonCommander+counts.PrimarisPsyker
					+counts.Straken+counts.Techpriest+counts.Yarrick) ) && (tanksAllowed == true)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int chimeraType = index.randomizeChimera();
				int cost = (costs.Chimera + index.chimera_costs[chimeraType]);
				String name = index.chimera_types[chimeraType];
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ", Hunter-Killer Missile, Augur Array");
				}
				counts.Chimera++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.Chimeras++;
			}
		}
		
		public void add_Valkyrie()
		{
			if ( (counts.Valkyrie<(maxCount*3)) && (pointsSpent <= (pointsAllotted-165)) && (tanksAllowed == true) )
			{
				int cost = costs.Valkyrie;
				String name = "Valkyrie: Lascannon, Heavy Bolters, Multiple Rocket Pods";
				counts.Valkyrie++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				if (slots.Valkyries == 0)
					slots.Flyer++;
				slots.Valkyries++;
				if (slots.Valkyries >= 3)
				{
					slots.Valkyries = 0;
				}
			}
		}
		
	}
	
	/**
	 * Index - The Variants of Special Weapons, Chimeras, and Leman Russes
	 */
	public class Index
	{
		public int [] lemanRuss_costs;
		public String [] lemanRuss_types;
		private int [] lemanRuss_used;
		public int [] chimera_costs;
		public String [] chimera_types;
		private int [] chimera_used;
		public int [] specialWeapons_costs;
		public String [] specialWeapons_types;
		
		public Index()
		{
			lemanRuss_costs = new int[7];
			lemanRuss_types = new String[7];
			lemanRuss_used = new int[7];
			for (int i = 0; i < 7; i++)
				lemanRuss_used[i] = (-1);
			chimera_costs = new int[3];
			chimera_types = new String[3];
			chimera_used = new int[3];
			for (int i = 0; i < 3; i++)
				chimera_used[i] = (-1);
			specialWeapons_costs = new int[6];
			specialWeapons_types = new String[6];
			lemanRuss_costs[0] = 55;
			lemanRuss_types[0] = "*Main* Leman Russ";
			lemanRuss_costs[1] = 45;
			lemanRuss_types[1] = "Main Plasma Leman Russ";
			lemanRuss_costs[2] = 15;
			lemanRuss_types[2] = "Secondary Plasma Leman Russ";
			lemanRuss_costs[3] = 60;
			lemanRuss_types[3] = "Demolisher Leman Russ";
			lemanRuss_costs[4] = 20;
			lemanRuss_types[4] = "Battle Cannon/Heavy Bolter Leman Russ";
			lemanRuss_costs[5] = 20;
			lemanRuss_types[5] = "Battle Cannon/Heavy Bolter Leman Russ";
			lemanRuss_costs[6] = 50;
			lemanRuss_types[6] = "Battle Cannon/Heavy Flamer/Heavy Bolters Leman Russ";
			chimera_costs[0] = 15;
			chimera_types[0] = "*Main* Chimera: Track Guards";
			chimera_costs[1] = 20;
			chimera_types[1] = "Main Dozer Blade Chimera: Track Guards";
			chimera_costs[2] = 10;
			chimera_types[2] = "Chimera, Heavy Bolter Only: Track Guards";
			specialWeapons_costs[0] = 2;
			specialWeapons_types[0] = "Sniper Rifle";
			specialWeapons_costs[1] = 5;
			specialWeapons_types[1] = "Flamer";
			specialWeapons_costs[2] = 5;
			specialWeapons_types[2] = "Grenade Launcher";
			specialWeapons_costs[3] = 10;
			specialWeapons_types[3] = "Melta Gun";
			specialWeapons_costs[4] = 10;
			specialWeapons_types[4] = "Plasma Gun";
			specialWeapons_costs[5] = 5;
			specialWeapons_types[5] = "Hot-Shot Volley Gun";
		}
		
		/**
		 * Randomize Which Leman Russ to Use; "Use Up" the Leman Russ for Each, Since They're Unique Tank Models Each
		 * @return The Index Mapping for Which Leman Russ to Use
		 */
		public int randomizeLemanRuss()
		{
			int result = roll(11);
			if ( (result == 1) || (result == 2) )
			{
				if (lemanRuss_used[0] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[0] = 1;
					return 0;
				}
			}
			if ( (result == 3) || (result == 4) )
			{
				if (lemanRuss_used[1] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[1] = 1;
					return 1;
				}
			}
			if (result == 5)
			{
				if (lemanRuss_used[2] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[2] = 1;
					return 2;
				}
			}
			if ( (result == 6) || (result == 7) )
			{
				if (lemanRuss_used[3] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[3] = 1;
					return 3;
				}
			}
			if (result == 8)
			{
				if (lemanRuss_used[4] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[4] = 1;
					return 4;
				}
			}
			if (result == 9)
			{
				if (lemanRuss_used[5] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[5] = 1;
					return 5;
				}
			}
			if ( (result == 10) || (result == 11) )
			{
				if (lemanRuss_used[6] == 1)
					return randomizeLemanRuss();
				else
				{
					lemanRuss_used[6] = 1;
					return 6;
				}
			}
			else
				return -1;
		}
		
		/**
		 * Randomize Which Chimera to Use; "Use Up" the Chimera for Each, Since They're Unique Tank Models Each
		 * @return The Index Mapping for Which Chimera to Use
		 */
		public int randomizeChimera()
		{
			int result = roll(3);
			if (result == 1)
			{
				if (chimera_used[0] == 1)
					return randomizeChimera();
				else
				{
					chimera_used[0] = 1;
					return 0;
				}
			}
			if (result == 2)
			{
				if (chimera_used[1] == 1)
					return randomizeChimera();
				else
				{
					chimera_used[1] = 1;
					return 1;
				}
			}
			if (result == 3)
			{
				if (chimera_used[2] == 1)
					return randomizeChimera();
				else
				{
					chimera_used[2] = 1;
					return 2;
				}
			}
			else
				return -1;
		}
		
		/**
		 * Randomize Which Special Weapon to Use; unitType Is 0 for Guardsmen Or 1 for Militarum Tempestus
		 * @return The Index Mapping for Which Special Weapon to Use
		 */
		public int randomizeSpecialWeapon(int unitType)
		{
			int result = roll(20);
			if (result == 1)
			{
				if (unitType == 1)
					return randomizeSpecialWeapon(unitType);
				else
					return 0;
			}
			if ( (result >= 2) && (result <= 4) )
			{
				return 1;
			}
			if (result == 5)
			{
				return 2;
			}
			if ( (result >= 6) && (result <= 8) )
			{
				return 3;
			}
			if ( (result >= 9) && (result <= 17) )
			{
				return 4;
			}
			if ( (result >= 18) && (result <= 20) )
			{
				if (unitType == 0)
					return randomizeSpecialWeapon(unitType);
				else
					return 5;
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
			if ( (num >= 1) && (num <= 2) )
				units.addHQ_Creed();
			if (num == 3)
				units.addHQ_CompanyCommander();
			if ( (num >= 4) && (num <= 5) )
			{
				if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
					units.addHQ_TankCommander();
			}
			if (num == 6)
			{
				if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
					units.addHQ_Pask();
			}
			if ( (num >= 7) && (num <= 10) )
				units.addHQ_Yarrick();
			if ( (num >= 11) && (num <= 12) )
				units.addHQ_LordCommissar();
			if ( (num >= 13) && (num <= 15) )
				units.addHQ_Straken();
			if ( (num >= 16) && (num <= 18) )
				units.addHQ_TempestorPrime();
			if ( (num >= 19) && (num <= 20) )
				units.addHQ_PrimarisPsyker();
		}
		
		/**
		 * Selects a T Choice from Its List
		 * @param num The Choice
		 */
		public void getT(int num)
		{
			if ( (num >= 1) && (num <= 3) )
				units.addT_Infantry();
			if (num == 4)
				units.addT_Conscripts();
			if ( (num >= 5) && (num <= 6) )
				units.addT_Kasrkins_Half();
			if ( (num >= 7) && (num <= 10) )
				units.addT_Kasrkins_Full();
		}
		
		/**
		 * Selects an E Choice from Its List
		 * @param num The Choice
		 */
		public void getE(int num)
		{
			if ( (num >= 1) && (num <= 7) )
				units.add_Bombardier();
			if (num == 8)
				units.add_PlatoonCommander();
			if ( (num >= 9) && (num <= 11) )
				units.add_CommandSquad();
			if ( (num >= 12) && (num <= 13) )
				units.add_SpecialWpnsSquad();
			if ( (num >= 14) && (num <= 16) )
				units.add_Veterans();
			if ( (num >= 17) && (num <= 32) )
				units.add_Harker();
			if ( (num >= 33) && (num <= 35) )
				units.add_KasrkinCmdSquad();
			if ( (num >= 36) && (num <= 39) )
				units.add_Techpriest();
			if (num == 40)
				units.add_Commissar();
			if ( (num >= 41) && (num <= 44) )
				units.add_FleetOfficer();
			if ( (num >= 45) && (num <= 51) )
				units.add_OgrBodyguard();
			if ( (num >= 52) && (num <= 53) )
				units.add_NorkDeddog();
		}
		
		/**
		 * Selects a FA Choice from Its List
		 * @param num The Choice
		 */
		public void getFA(int num)
		{
			if ( (num >= 54) && (num <= 60) )
				units.add_Sentinels();
		}
		
		/**
		 * Selects an HS Choice from Its List
		 * @param num The Choice
		 */
		public void getHS(int num)
		{
			if ( (num >= 61) && (num <= 67) )
			{
				if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
					units.add_LemanRuss();
			}
			if ( (num >= 68) && (num <= 77) )
			{
				if (slots.Wyverns < maxes.tank_Wyvern)
					units.add_Wyvern();
			}
			if ( (num >= 78) && (num <= 81) )
				units.add_HvyWpnsTeam();
		}
		
		/**
		 * Selects a Transport Choice from Its List
		 * @param num The Choice
		 */
		public void getTransport(int num)
		{
			if ( (num >= 82) && (num <= 84) )
			{
				if (slots.Chimeras < maxes.tank_Chimera)
					units.add_Chimera();
			}
		}
		
		/**
		 * Selects a Flyer Choice from Its List
		 * @param num The Choice
		 */
		public void getFlyer(int num)
		{
			if ( (num >= 85) && (num <= 96) )
			{
				if (slots.Valkyries < maxes.aircraft_Valkyrie)
					units.add_Valkyrie();
			}
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
		public int LemanRusses_HQ;
		public int LemanRusses;
		public int Chimeras;
		public int Wyverns;
		public int Valkyries;
		
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
			LemanRusses_HQ = 0;
			LemanRusses = 0;
			Chimeras = 0;
			Wyverns = 0;
			Valkyries = 0;
			
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
	 * The Regiments
	 */
	public String Custom = "CUSTOM: -> <PREFER> DOUBLE RAPID FIRE AS MAIN!!!";
	public String Cadian = "Cadian";
	public String Catchan = "Catchan";
	public String Tallarn = "Tallarn";
	public String Kasrkins = "Militarum Tempestus: -> <PREFER> the Longer-Ranged Detachment!!!";
	
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
		
		//Choose Regiment
		int regiment = roll(6);
		if ( (regiment == 1) || (regiment == 2) )
			units.regiment = Custom;
		if (regiment == 3)
			units.regiment = Cadian;
		if (regiment == 4)
			units.regiment = Catchan;
		if (regiment == 5)
			units.regiment = Tallarn;
		if (regiment == 6)
			units.regiment = Kasrkins;
		
		//Do Core Army Building
		Lists lists = new Lists();
		while (slots.HQ < slots.HQ_Min)
		{
			int choice = roll(20);
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
			int choice = roll(126);
			if ( (choice >= 1) && (choice <= 20) && (slots.HQ < slots.HQ_Max) )
				lists.getHQ(choice);
			if ( (choice >= 21) && (choice <= 30) && (slots.T < slots.T_Max) )
				lists.getT(choice - 20);
			choice = (choice - 30);
			if ( (choice >= 1) && (choice <= 53) && (slots.E < slots.E_Max) )
				lists.getE(choice);
			if ( (choice >= 54) && (choice <= 60) && (slots.FA < slots.FA_Max) )
				lists.getFA(choice);
			if ( (choice >= 61) && (choice <= 81) && (slots.HS < slots.HS_Max) )
				lists.getHS(choice);
			if ( (choice >= 82) && (choice <= 84) )
				lists.getTransport(choice);
			if ( (choice >= 85) && (choice <= 96) && (slots.Flyer < slots.Flyer) )
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
		String location = "C:/Users/Windhunter/Desktop/40KArmyList_";
		location = (location + pointsAllotted + "Pts_" + numLists + "Lists.txt");
		File f = new File(location);
		f.createNewFile(); //Creates a new file if necessary.
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));	
		for (int i = 0; i < numLists; i++)
		{
			//Army List Full Creation
			System.out.println( "Creating Army List #" + (i+1) );
			createArmyList();
			String output = ("Points:" + units.pointsSpent + " - List #" + (i+1) + "\n\nRegiment: "
					+ units.regiment + "\n" + units.armyList + "\n\n");
			
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
