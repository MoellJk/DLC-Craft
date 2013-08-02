package Abascus.DLCCraft.common;

import java.lang.ref.WeakReference;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class EventManager 
{

	@ForgeSubscribe
	public void playerInteract(PlayerInteractEvent event)
	{
		if(event.action == event.action.RIGHT_CLICK_BLOCK)
		{
			rightClickBlock(event);
		}
		else if(event.action == event.action.RIGHT_CLICK_AIR)
		{
			rightClickAir(event);
		}
		else if(event.action == event.action.LEFT_CLICK_BLOCK)
		{
			leftClickBlock(event);
		}

	}
	@ForgeSubscribe
	public void entityConstructing(EntityConstructing event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			PlayerDLCStats stats = new PlayerDLCStats();
			EntityPlayer ep = (EntityPlayer)event.entity;
			NBTTagCompound tags = event.entity.getEntityData();
			tags.setCompoundTag("DLCCraft", new NBTTagCompound());
			NBTTagList tagList = new NBTTagList();
			NBTTagCompound dlc;

			for (int i = 0; i < DLCManager.names.length; ++i)
			{
				if (DLCManager.names[i] != null)
				{
					dlc = new NBTTagCompound();
					dlc.setInteger(DLCManager.names[i], (byte) 0);
					tagList.appendTag(dlc);
				}
			}

			tags.setTag("DLCCraft", tagList);
			stats.init();
			stats.player = new WeakReference<EntityPlayer>(ep);
		}
	}
	
	@ForgeSubscribe
	public void pEvent(PlayerEvent event)
	{
		
	}
	
	@ForgeSubscribe
	public void itemEvent(ItemEvent event)
	{

	}


	@ForgeSubscribe
	public void onLivingDrop (LivingDropsEvent event)
	{
			EntityPlayer ep = (EntityPlayer)event.source.getSourceOfDamage();
			DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(ep.username).dlcManager;
			
			if(dlcs.getState("mobDrops") != 2)
			{
				event.setCanceled(true);
			}
		
	}

	public void rightClickBlock(PlayerInteractEvent event)
	{
		PlayerDLCStats stats = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username);

	}

	public void rightClickAir(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(Item.itemsList[event.entityPlayer.getCurrentEquippedItem().itemID] instanceof ItemFood)
		{
			if(dlcs.getState("eat") != 2)
			{
				event.setCanceled(true);
			}
		}
	}

	public void leftClickBlock(PlayerInteractEvent event)
	{
		DLCManager dlcs = DLCCraft.playerTracker.getPlayerDLCStats(event.entityPlayer.username).dlcManager;
		if(event.entityPlayer.worldObj.getBlockId(event.x, event.y, event.z) == Block.wood.blockID)
		{

		if(dlcs.getState(0) != 2)
		{
			event.setCanceled(true);
		}
		}
	}

}
