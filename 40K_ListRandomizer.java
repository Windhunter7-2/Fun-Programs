package Warhammer_40K;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class 40K_ListRandomizer {
	
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
		public int Kell = 45;
		public int Veterans = 85;
		public int Harker = 55;
		public int KasrkinCmdSquad = 45;
		public int Techpriest = 35;
		public int Commissar = 25;
		public int FleetOfficer = 25;
		public int Priest = 40;
		public int OgrBodyguard = 60;
		public int NorkDeddog = 60;
		public int Ratlings = 10;	//Each Individual!!!
		public int Sentinels = 50;	//Each Individual!!!
		public int LemanRuss = 140;	//Each Individual!!!
		public int Wyvern = 135;	//Each Individual!!!
		public int Manticore = 145;
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
		public int tank_Manticore = 1;
		public int aircraft_Valkyrie = 1;
		
		//Categorical Counts
		public int seniorOfficers;
		public int mainTroops;
		public int lemanRusses;
		public int rerollBuffChars;
		public int commissars;
		public int kasrkinsUnits;
		public int combatUnits;
		public int vehicles;
		public int bodyguards;
		
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
		public int Kell;
		public int Veterans;
		public int Harker;
		public int KasrkinCmdSquad;
		public int Techpriest;
		public int Commissar;
		public int FleetOfficer;
		public int Priest;
		public int OgrBodyguard;
		public int NorkDeddog;
		public int Ratlings;
		public int Sentinels;
		public int LemanRuss;
		public int Wyvern;
		public int Manticore;
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
			Kell = 0;
			Veterans = 0;
			Harker = 0;
			KasrkinCmdSquad = 0;
			Techpriest = 0;
			Commissar = 0;
			FleetOfficer = 0;
			Priest = 0;
			OgrBodyguard = 0;
			NorkDeddog = 0;
			Ratlings = 0;
			Sentinels = 0;
			LemanRuss = 0;
			Wyvern = 0;
			Manticore = 0;
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
				counts.seniorOfficers++;
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
				counts.seniorOfficers++;
			}
		}
		
		public void addHQ_TankCommander()
		{
			if ( (counts.TankCommander<maxCount) && (pointsSpent <= (pointsAllotted-(costs.TankCommander+65)))
					&& (tanksAllowed == true)
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
				counts.lemanRusses++;
				counts.vehicles++;
			}
		}
		
		public void addHQ_Pask()
		{
			if ( (counts.Pask<1) && (pointsSpent <= (pointsAllotted-(costs.Pask+65))) && (tanksAllowed == true)
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
				counts.lemanRusses++;
				counts.vehicles++;
			}
		}
		
		public void addHQ_Yarrick()
		{
			if ( (counts.Yarrick<1) && (pointsSpent <= (pointsAllotted-costs.Yarrick)) )
			{
				int cost = costs.Yarrick;
				String name = "Commissar Yarrick";
				counts.Yarrick++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.HQ++;
				counts.commissars++;
				counts.rerollBuffChars++;
				counts.combatUnits++;
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
				counts.commissars++;
				counts.combatUnits++;
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
				counts.combatUnits++;
				counts.seniorOfficers++;
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
				String name = "Primaris Psyker: Psychic Barrier, Nightshroud";
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
				counts.mainTroops++;
			}
		}
		
		public void addT_Conscripts()
		{
			if ( (pointsSpent <= (pointsAllotted-(costs.Conscripts*30)))
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
				counts.mainTroops++;
			}
		}
		
		public void addT_Kasrkins_Half()
		{
			//If *Not* Playing As Kasrkins, No More Than 1 Squad Per 500 Points!
			if (regiment.equals(Kasrkins) || (counts.Kasrkins_Unit<=(pointsAllotted/500.0)-1))
			{
				int cost = costs.Kasrkins_Half;
				String name = "Militarum Tempestus Scions: 1/2 Squad";
				if ( regiment.equals(Kasrkins) || (counts.Yarrick>0) || (counts.LordCommissar>0) )
				{
					int voxCaster = roll(2);
					if (voxCaster == 2)	//50% for Vox-Caster
					{
						cost = (cost + 5);
						name = (name + ", Vox-Caster");
					}
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
				counts.kasrkinsUnits++;
			}
			
		}
		
		public void addT_Kasrkins_Full()
		{
			if ( (pointsSpent <= (pointsAllotted-(costs.Kasrkins_Full+45))) && (regiment.equals(Kasrkins)) )
			{
				int cost = costs.Kasrkins_Full;
				String name = "Militarum Tempestus Scions: Full Squad";
				if ( regiment.equals(Kasrkins) || (counts.Yarrick>0) || (counts.LordCommissar>0) )
				{
					int voxCaster = roll(2);
					if (voxCaster == 2)	//50% for Vox-Caster
					{
						cost = (cost + 5);
						name = (name + ", Vox-Caster");
					}
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
				counts.kasrkinsUnits++;
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
					||(regiment.equals(Catchan))||(regiment.equals(Tallarn))) )
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
			if ( (counts.CommandSquad<maxCount) && (counts.CommandSquad<(counts.Bombardier+counts.CompanyCommander
					+counts.Creed+counts.Pask+counts.PlatoonCommander+counts.Straken+counts.TankCommander))
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int cost = costs.CommandSquad;
				String name = "Command Squad: Medi-Pack";
				int voxCaster = roll(4);
				int heavyFlamer = roll(2);
				int numWeapons = 3;
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
		
		public void add_Kell()
		{
			if ( (counts.Kell<1) && (regiment.equals(Cadian)) )
			{
				int cost = costs.Kell;
				String name = "Colour Sergeant Kell";
				counts.Kell++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
				counts.bodyguards++;
				counts.seniorOfficers++;
			}
		}
		
		public void add_Veterans()
		{
			if ( (counts.Veterans<maxCount) && (pointsSpent <= (pointsAllotted-(costs.Veterans+30)))
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
				counts.mainTroops++;
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
				counts.rerollBuffChars++;
			}
		}
		
		public void add_KasrkinCmdSquad()
		{
			if ( (counts.KasrkinCmdSquad<maxCount) && (counts.KasrkinCmdSquad<counts.TempestorPrime) && (regiment.equals(Kasrkins)) )
			{
				int cost = costs.KasrkinCmdSquad;
				String name = "Militarum Tempestus Command Squad: Medi-Pack";
				int numWeapons = 3;
				if ( regiment.equals(Kasrkins) || (counts.Yarrick>0) || (counts.LordCommissar>0) )
				{
					int voxCaster = roll(4);
					if (voxCaster != 1)	//75% for Vox-Caster
					{
						cost = (cost + 5);
						name = (name + ", Vox-Caster");
						numWeapons--;
					}
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
				counts.kasrkinsUnits++;
			}
		}
		
		public void add_Techpriest()
		{
			if ( counts.Techpriest<maxCount && (tanksAllowed == true) )
			{
				int cost = costs.Techpriest;
				String name = "Techpriest Enginseer";
				counts.Techpriest++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
				counts.combatUnits++;
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
				counts.commissars++;
			}
		}
		
		public void add_FleetOfficer()
		{
			if ( counts.FleetOfficer<maxCount && ((counts.FleetOfficer < (counts.Valkyrie))||(regiment.equals(Kasrkins))) )
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
		
		public void add_Priest()
		{
			if ( (counts.Priest<maxCount) && ((counts.Priest<( (counts.combatUnits) / 4 ))||(
					(regiment.equals(Catchan))&&(counts.mainTroops > 3))) )
			{
				int cost = costs.Priest;
				String name = "Ministorum Priest";
				counts.Priest++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
				counts.combatUnits++;
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
				counts.combatUnits++;
				counts.bodyguards++;
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
				counts.combatUnits++;
				counts.bodyguards++;
			}
		}
		
		public void add_Ratlings()
		{
			if (counts.Ratlings<1)
			{
				int numRatlings = (roll(6) + 4);
				int cost = (costs.Ratlings * numRatlings);	//Cost of Each
				String name = ("Ratlings: " + numRatlings + " Guys");
				counts.Ratlings++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.E++;
			}
		}
		
		public void add_Sentinels()
		{
			if ( (counts.Sentinels<maxCount) && (pointsSpent <= (pointsAllotted-((costs.Sentinels*3)+15)))
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				//Max of 1 for 500, 2 for 1000, 3 for 1500+
				int numSentinels = roll(pointsAllotted / 500);
				String name;
				String weapon = "Missile Launchers";
				if (pointsAllotted <= 1000)
					name = "Scout ";	//Scout Sentinels for 500 - 1000 Points
				else
				{
					name = "Armored ";	//Armored Sentinels Otherwise
					//If and Only if 1001+ Points, Randomize Weapon; Otherwise, Only Missile Launchers!
					int whichWeapon = roll(2);
					if (whichWeapon == 2)	//Lascannon; Missile Launcher Otherwise
						weapon = "Lascannons";
				}
				name = (name + "Sentinels Squadron: " + numSentinels + " Sentinels, Each with " + weapon);
				int cost = (costs.Sentinels * numSentinels);
				int HKM = roll(2);
				if (HKM == 2)	//50% Chance of Getting HKM
				{
					cost = ( cost + (5 * numSentinels) );
					name = (name + ", Hunter-Killer Missiles");
				}
				counts.Sentinels++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.FA++;
				//Sentinels Don't Count As Full-on "Vehicles", for the Purposes of Techpriests!
//				counts.vehicles++;
			}
		}
		
		public void add_LemanRuss()
		{
			if ( (counts.LemanRuss<(maxCount*3)) && (pointsSpent <= (pointsAllotted-(costs.LemanRuss+65)))
					&& (tanksAllowed == true)
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
				counts.lemanRusses++;
				counts.vehicles++;
			}
		}
		
		public void add_Wyvern()
		{
			if ( (counts.Wyvern<(maxCount*3)) && (pointsSpent <= (pointsAllotted-(costs.Wyvern+10))) && (tanksAllowed == true)
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
				counts.vehicles++;
			}
		}
		
		public void add_Manticore()
		{
			if ( (counts.Manticore<maxCount) && (pointsSpent <= (pointsAllotted-(costs.Manticore+10))) && (tanksAllowed == true)
					&& ((regiment.equals(Custom))||(regiment.equals(Cadian))||(regiment.equals(Catchan))
					||(regiment.equals(Tallarn))) )
			{
				int cost = costs.Manticore;
				String name = "Manticore";
				int HKM = roll(4);
				if (HKM != 1)	//75% Chance of Getting HKM
				{
					cost = (cost + 10);
					name = (name + ": Hunter-Killer Missile, Augur Array");
				}
				counts.Manticore++;
				pointsSpent = (pointsSpent + cost);
				name = (name + " -> " + cost + " Points");
				armyList = (armyList + name + "\n");
				System.out.println("Added: " + name);
				slots.Manticores++;
				slots.HS++;
				counts.vehicles++;
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
					int whichWeapon = roll(4);
					if (pointsAllotted <= 1000)
					{
						//If 500-1000 Points, Make Lascannons Impossible!
						if (whichWeapon == 2)
							whichWeapon = roll(3);
						whichWeapon++;	//Range of 2-4; 3 for Heavy Bolter, 4 for Autocannon
						if (whichWeapon == 2)
							whichWeapon = 1;	//Missile Launcher
					}
					if (whichWeapon == 1)
						name = (name + "(" + i + ") Missile Launcher");
					else if (whichWeapon == 2)
						name = (name + "(" + i + ") Lascannon");
					else if (whichWeapon == 3)
					{
						name = (name + "(" + i + ") Heavy Bolter");
						cost -= 5;
					}
					else
					{
						name = (name + "(" + i + ") Autocannon");
						cost -= 5;
					}
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
			if ( (counts.Chimera< (counts.combatUnits+counts.mainTroops+counts.seniorOfficers) ) && (tanksAllowed == true)
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
				counts.vehicles++;
			}
		}
		
		public void add_Valkyrie()
		{
			if ( (counts.Valkyrie<(maxCount*3)) && (pointsSpent <= (pointsAllotted-costs.Valkyrie)) && (tanksAllowed == true) )
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
				counts.vehicles++;
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
			lemanRuss_costs[6] = 45;
			lemanRuss_types[6] = "Vanquisher Battle Cannon/Heavy Flamer/Heavy Bolters Leman Russ";
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
			int result = roll(30);
			if ( (result >= 1) && (result <= 8) )
			{
				if (unitType == 1)
					return 5;	//Since Kasrkins Don't Have Sniper Rifles, Just Give Hotshot Volley Gun
				else
					return 0;
			}
			if ( (result >= 9) && (result <= 11) )
			{
				return 1;
			}
			if ( (result >= 12) && (result <= 14) )
			{
				return 2;
			}
			if ( (result >= 15) && (result <= 24) )
			{
				return 3;
			}
			if ( (result >= 25) && (result <= 39) )
			{
				return 4;
			}
			if ( (result >= 40) && (result <= 60) )
			{
				if (unitType == 0)
					return 4;	//Since Guardsmen Don't Have Hotshots, Just Give Plasma
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
		public String regiment;	//A Copy of the Current Regiment
		private int firstOfficer;	//Whether the First Officer Has Been Picked
		private int firstTroops;	//Whether the First Troops Has Been Picked
		
		/**
		 * Selects All *Main* HQs and Troops.
		 */
		public void getBase()
		{
			//Randomize
			int baseNum = 1;	//If Not Allowed Vehicles
			if (pointsAllotted > 1000)
				baseNum = 2;	//If Allowed Vehicles
			int randomBase1 = roll(baseNum);
			int randomBase2 = roll(baseNum + 1);
			
			//Make Selection Based on Rolls
			if (randomBase1 == 1)
				getBaseA_OfficerAndTroops();
			if (randomBase1 == 2)
				getBaseB_tankCmmndrAndTroops();
			if (randomBase2 == 2)
				getBaseA_OfficerAndTroops();
			if (randomBase2 == 3)
				getBaseB_tankCmmndrAndTroops();
		}
		
		/**
		 * Selects an HQ Senior Officer (Infantry), As Well As 1-3 Troops
		 */
		private void getBaseA_OfficerAndTroops()
		{
			//Select Officer
			if (firstOfficer == 0)
			{
				if (regiment.equals(Cadian))
					units.addHQ_Creed();
				else if (regiment.equals(Catchan))
					units.addHQ_Straken();
				else if (regiment.equals(Kasrkins))
					units.addHQ_TempestorPrime();
				else
					units.addHQ_CompanyCommander();
				firstOfficer++;
			}
			else
			{
				if (regiment.equals(Kasrkins))
					units.addHQ_TempestorPrime();
				else
					units.addHQ_CompanyCommander();
			}
			
			//Select Troops
			getBaseAll_troops();
		}
		
		/**
		 * Selects a Tank Commander, As Well As 1-3 Troops
		 */
		private void getBaseB_tankCmmndrAndTroops()
		{
			//Select Officer
			if (firstOfficer == 0)
			{
				if (regiment.equals(Cadian))
				{
					if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
						units.addHQ_Pask();
				}
				else
				{
					if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
						units.addHQ_TankCommander();
				}
				firstOfficer++;
			}
			else
			{
				if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
					units.addHQ_TankCommander();
			}
			
			//Select Troops
			getBaseAll_troops();
		}
		
		/**
		 * Selects 1-3 Troops (Depending on Army Size)
		 */
		private void getBaseAll_troops()
		{
			//If Second Iteration of Troops, Calculate How Many
			int numTroops = slots.T_Min;
			if (firstTroops > 0)
			{
				int diceTreeA = roll(3);
				int diceTreeB = roll(2);
				if (diceTreeA == 1)
					numTroops = 0;
				if (diceTreeA == 2)
					numTroops = 1;
				if (diceTreeA == 3)
				{
					if (diceTreeB == 1)
						numTroops = 2;
					if (diceTreeB == 2)
						numTroops = 3;
				}
			}
			
			//The Troops Themselves
			for (int i = 0; i < numTroops; i++)
			{
				if (slots.T >= slots.T_Max)
					break;
				if (regiment.equals(Kasrkins))
					units.addT_Kasrkins_Full();
				else
				{
					int num = roll(5);
					if ( (num >= 1) && (num <= 3) )
						units.addT_Infantry();
					if ( (num >= 4) && (num <= 5) )
						units.addT_Conscripts();
				}
				firstTroops++;
			}
		}
		
		/**
		 * Selects (Only One) of Other Units
		 */
		public void getOtherUnits()
		{
			int choice = roll(9);
			if (pointsAllotted > 1000)
				choice += 3;
			if ( (choice >= 1) && (choice <= 5) )
				getMainUnits();
			if ( (choice >= 6) && (choice <= 7) )
				getSupportChars();
			if ( (choice >= 8) && (choice <= 15) )
			{
				int num = roll(13);
				boolean sentinelsOnly = false;
				if (pointsAllotted <= 1000)
					sentinelsOnly = true;
				getVehicles(num, sentinelsOnly);
			}
		}
		
		/**
		 * Selects a Vehicle
		 * @param num The Choice
		 * @param sentinelsOnly Whether Or Not It's a Smaller Battle and Only Sentinels Would Be Allowed
		 */
		private void getVehicles(int num, boolean sentinelsOnly)
		{
			//Heavy Supports
			if (( (num >= 1) && (num <= 7) ) && (slots.HS < slots.HS_Max) && (!sentinelsOnly))
			{
				if ( (num >= 1) && (num <= 2) )
				{
					if ( (slots.LemanRusses+slots.LemanRusses_HQ) < maxes.tank_LemanRuss )
						units.add_LemanRuss();
				}
				if ( (num >= 3) && (num <= 4) )
				{
					if (slots.Wyverns < maxes.tank_Wyvern)
						units.add_Wyvern();
				}
				if ( (num >= 5) && (num <= 7) )
				{
					if (slots.Manticores < maxes.tank_Manticore)
						units.add_Manticore();
				}
			}
			
			//Flyers
			else if (( (num >= 8) && (num <= 9) ) && (slots.Flyer < slots.Flyer_Max) && (!sentinelsOnly))
			{
				if (slots.Valkyries < maxes.aircraft_Valkyrie)
					units.add_Valkyrie();
			}
			
			//Fast Attack
			else if ( (( (num >= 10) && (num <= 11) ) && (slots.FA < slots.FA_Max)) || (sentinelsOnly) )
			{
				if (slots.FA < slots.FA_Max)
					units.add_Sentinels();
			}
			
			//Transports
			else if (( (num >= 12) && (num <= 13) ) && (slots.Flyer < slots.Flyer_Max) && (!sentinelsOnly))
			{
				if (slots.Chimeras < maxes.tank_Chimera)
					units.add_Chimera();
			}
		}
		
		/**
		 * Gets a (Main) Common Squad
		 */
		private void getMain_commonSquads()
		{
			int choice = roll(6);
			if ( (choice >= 1) && (choice <= 3) && (slots.E < slots.E_Max) )
				units.add_Veterans();
			if ( (choice >= 4) && (choice <= 5) && (slots.E < slots.E_Max) )
				units.add_Ratlings();
			if ( (choice == 6) && (slots.HS < slots.HS_Max) )
				units.add_HvyWpnsTeam();
		}
		
		/**
		 * Gets a (Main) Uncommon Squad
		 */
		private void getMain_uncommonSquads()
		{
			//Troops (Kasrkin Basics)
			int choice = roll(10);
			if ( (choice >= 1) && (choice <= 2) && (slots.T < slots.T_Max) )
				units.addT_Kasrkins_Half();
			
			//Elites
			if ( (choice >= 3) && (choice <= 10) && (slots.E < slots.E_Max) )
			{
				if ( (choice >= 3) && (choice <= 5) )
					units.add_CommandSquad();
				if ( (choice >= 6) && (choice <= 7) )
					units.add_SpecialWpnsSquad();
				if ( (choice >= 8) && (choice <= 10) )
					units.add_KasrkinCmdSquad();
			}
		}
		
		/**
		 * Gets a (Main) Character
		 */
		private void getMain_chars()
		{
			//HQs (Primaris Psykers)
			int choice = roll(9);
			if ( (choice >= 1) && (choice <= 2) && (slots.HQ < slots.HQ_Max) )
				units.addHQ_PrimarisPsyker();
			
			//Elites
			if ( (choice >= 3) && (choice <= 9) && (slots.E < slots.E_Max) )
			{
				if ( (choice >= 3) && (choice <= 5) )
					units.add_Bombardier();
				if ( (choice >= 6) && (choice <= 7) )
					units.add_FleetOfficer();
				if ( (choice >= 8) && (choice <= 9) )
					units.add_Priest();	//*Technically* a Support Character, but Already Has Stuff in Req. for It!
			}
		}
		
		/**
		 * Get a Couple Main Units
		 */
		private void getMainUnits()
		{
			getMain_commonSquads();
			int getUncommon = roll(2);
			if (getUncommon == 2)
				getMain_uncommonSquads();
			int getChar = roll(3);
			if (getChar == 3)
				getMain_chars();
		}
		
		private boolean rerollerThere = false;
		
		/**
		 * Gets a Support Character That Gives Rerolls
		 */
		private void getSupport_rerollers()
		{
			//If Already a Reroll Support Character, Return
			if (rerollerThere)
				return;
			
			//Elite: Harker
			if ( (slots.E < slots.E_Max) && regiment.equals(Catchan) )
			{
				units.add_Harker();
				rerollerThere = true;
			}
			
			//HQ: Yarrick
			else if ( (slots.HQ < slots.HQ_Max) && (units.counts.Yarrick<1) )
			{
				units.addHQ_Yarrick();
				rerollerThere = true;
			}
		}
		
		/**
		 * Gets a (Support) Commissar
		 */
		private void getSupport_commissars()
		{
			//HQs
			int choice = roll(5);
			if ( (choice >= 1) && (choice <= 4) && (slots.HQ < slots.HQ_Max) )
			{
				if ( (choice >= 1) && (choice <= 2) && (units.counts.Yarrick<1) )
				{
					if (rerollerThere)
						units.addHQ_LordCommissar();
					else
					{
						units.addHQ_Yarrick();
						rerollerThere = true;
					}
				}
				if ( (choice >= 3) && (choice <= 4) )
					units.addHQ_LordCommissar();
			}
			
			//Elites
			if ( (choice == 5) && (slots.E < slots.E_Max) )
				units.add_Commissar();
		}
		
		/**
		 * Gets a (Support) Bodyguard
		 */
		private void getSupport_bodyguards()
		{
			if (slots.E < slots.E_Max)
			{
				//Kell
				if ( (regiment.equals(Cadian)) && (units.counts.Creed>0) )
					units.add_Kell();
				
				//Ogryns
				else
				{
					int choice = roll(2);
					if (choice == 1)
						units.add_OgrBodyguard();
					if (choice == 2)
						units.add_NorkDeddog();
				}
			}
		}
		
		/**
		 * Selects an Appropriate Support Character
		 * @param num The Choice
		 */
		private void getSupportChars()
		{
			//Rerollers
			if (!rerollerThere)
				getSupport_rerollers();
			
			//Commissars & Platoon Commanders
			else if (units.counts.mainTroops > (3*(units.counts.commissars+1)))
			{
				int num = roll(2);
				if (num == 1)
					getSupport_commissars();
				else if ( (num == 2) && (slots.E < slots.E_Max) )
					units.add_PlatoonCommander();
			}
			
			//Bodyguards
			else if ( units.counts.bodyguards < (units.counts.seniorOfficers+units.counts.commissars) )
				getSupport_bodyguards();
			
			//Techpriests
			else if ( (units.counts.Techpriest < units.counts.vehicles) && (slots.E < slots.E_Max) )
				units.add_Techpriest();
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
		public int Manticores;
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
			Manticores = 0;
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
	public String finalRegiment = "";
	
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
		if (pointsAllotted > 750)
		{
			//Battalion Detachment Maxes
			slots.HQ_Min = 2;
			slots.HQ_Max = 3;
			slots.T_Min = 3;
			slots.T_Max = 6;
			slots.E_Max = 6;
			slots.FA_Max = 3;
			slots.HS_Max = 3;
		}
		if (pointsAllotted > 1000)
		{
			//ONLY 1001+ Points Lists...
			units.maxCount = 3;
			units.tanksAllowed = true;
		}
		
		//Choose Regiment
		int regiment = roll(5);
		if (regiment == 1)
			units.regiment = Custom;
		if (regiment == 2)
			units.regiment = Cadian;
		if (regiment == 3)
			units.regiment = Catchan;
		if (regiment == 4)
			units.regiment = Tallarn;
		if (regiment == 5)
			units.regiment = Kasrkins;
		
		//Choose Sub-Regiment (If Kasrkins)
		finalRegiment = units.regiment;
		if (units.regiment.equals(Kasrkins))
		{
			finalRegiment = "Militarum Tempestus: -> ";
			int whichKasrkins = roll(6);
			if ( (whichKasrkins >= 1) && (whichKasrkins <= 2) )
				finalRegiment = (finalRegiment + "Iotan Dragons");
			if ( (whichKasrkins >= 3) && (whichKasrkins <= 4) )
				finalRegiment = (finalRegiment + "Lambdian Lions");
			if (whichKasrkins == 5)
				finalRegiment = (finalRegiment + "Psion Jackals");
			if (whichKasrkins == 6)
				finalRegiment = (finalRegiment + "Kappic Eagles");
		}
		
		//Do Core Army Building
		Lists lists = new Lists();
		lists.regiment = units.regiment;
		lists.firstOfficer = 0;
		lists.firstTroops = 0;
		lists.getBase();
		
		//Do Rest of Army Building
		long originalTime = System.currentTimeMillis();
		while ( units.pointsSpent < (pointsAllotted - 100) )
		{
			//Emergency Timer Exit
			long curTime = (System.currentTimeMillis() - originalTime);
			if (curTime > const_time)	//If Longer Than CONSTANT Time (See Top), Stop Looping
				break;
			
			//Army List Construction
			lists.getOtherUnits();
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
					+ finalRegiment + "\nWarlord: ____________\nRelic: ____________\nReserves: ____________"
					+ "\nCP Spent: ____________\nTank Ace(s) (If Applicable): ____________\n\n" + units.armyList + "\n\n");
			
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
