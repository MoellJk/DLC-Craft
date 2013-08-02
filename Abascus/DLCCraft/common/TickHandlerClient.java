package Abascus.DLCCraft.common;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandlerClient implements ITickHandler
{

	public TickHandlerClient()
	{
	}

	private boolean nagged = false;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(!nagged && DLCCraft.instance.startUpInfo)
		{

			EntityPlayer player = (EntityPlayer) tickData[0];
			String[] s = DLCCraft.instance.Msg;
			for(int i = 0; s.length > i; i++)
			{
				player.addChatMessage(EnumChatFormatting.BOLD+s[i]);
			}

			nagged = true;
		}
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.PLAYER);
	}

	@Override
	public String getLabel()
	{
		return "UniversalBlocks - Player update tick";
	}

}
