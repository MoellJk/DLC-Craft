package Abascus.DLCCraft.common;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class PlayerDLCStats
{
	public WeakReference<EntityPlayer> player;
	public ConcurrentHashMap<String, State> states = new ConcurrentHashMap<String, State>();
	public List<DLC> dlcs;
	public DLCManager dlcManager = new DLCManager();
	public int Coins = 20;

	public void init()
	{
		dlcManager = new DLCManager();
	}

	public void saveToNBT (NBTTagCompound tags)
	{
		NBTTagCompound dlc = new NBTTagCompound();
		NBTTagList tagList = new NBTTagList();
		dlc.setInteger("Coins", Coins);
		System.out.println("Coins save: " + Coins);
		tags.setTag("DLCCraft", dlc);
		//tags.setTag("DLCCraft", tagList);
		dlcManager.saveToNBT(tags);
	}

	public void readFromNBT (NBTTagCompound tags)
	{
		NBTTagList tagList = tags.getTagList("DLCCraft");
		 NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(0);
		 Coins = tags.getInteger("Coins");
		 System.out.println("Coins load: " + Coins);
		 
		dlcManager.readFromNBT(tags);
	}

}
