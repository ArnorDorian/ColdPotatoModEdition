package com.github.ArnorDorian.cp.items;

import com.github.ArnorDorian.cp.entities.EntityCheekGrenade;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

/**
 * Created by arnor on 2016/6/8 0008.
 */
public class ItemCheekGrenade extends Item {

    int ticks = 0;

    public ItemCheekGrenade() {
        super();
        //this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setMaxDamage(1);
        this.setUnlocalizedName("cheek_grenade");
    }

    /*@Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + (stack.getItemDamage() == 0 ? "full_cheek_grenade" : "empty_cheek_grenade");
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }*/

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

        if (playerIn.isCreative() || itemStackIn.getItemDamage() < itemStackIn.getMaxDamage()) {
            worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw,
                    SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote) {
                EntityCheekGrenade grenade = new EntityCheekGrenade(worldIn, playerIn);
                grenade.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.65F, 0.0F);
                worldIn.spawnEntityInWorld(grenade);
            }

            playerIn.swingArm(hand);
            this.setDamage(itemStackIn, 1);

            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
        }

        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);

    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (stack.getItemDamage() > 0) {
            if (ticks == 40) {
                stack.setItemDamage(stack.getItemDamage() - 1);
                ticks = 0;
            } else ticks++;
        }
    }

}
