package com.github.ArnorDorian.cp.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.translation.I18n;

import java.util.List;

/**
 * Created by arnor on 2016/6/7 0007.
 */
public class ItemUnnamedSword extends ItemSword{


    public ItemUnnamedSword(){
        super(ToolMaterial.STONE);
        this.setMaxDamage(-1);
        this.setMaxStackSize(1);
        this.setUnlocalizedName("unnamed_sword");
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced){
        tooltip.add(I18n.translateToLocalFormatted("info.unnamed_sword"));
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker){
        target.setFire(8);
        return true;
    }

}
