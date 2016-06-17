package com.github.ArnorDorian.cp.items;

import com.github.ArnorDorian.cp.entities.EntityScalpel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Random;
import java.util.Timer;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class ItemScalpel extends Item {

    int ticks = 0;

    public ItemScalpel() {
        super();
        this.setMaxStackSize(1);
        this.setMaxDamage(5);
        this.setUnlocalizedName("scalpel");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (playerIn.getTeam() != null) {
            Collection<String> collectionlist = playerIn.getTeam().getMembershipCollection();
            String[] list = collectionlist.toArray(new String[collectionlist.size()]);
            for (String name : list) {
                System.out.println(name);
            }
        }


        if (playerIn.isCreative() || itemStackIn.getItemDamage() < itemStackIn.getMaxDamage()) {
            worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_snowball_throw,
                    SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!worldIn.isRemote) {
                EntityScalpel scalpel = new EntityScalpel(worldIn, playerIn);
                scalpel.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                worldIn.spawnEntityInWorld(scalpel);
            }
            playerIn.swingArm(hand);
            damage(playerIn, itemStackIn, 2);

            return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {

        Random rand = new Random();
        double x = entity.posX;
        double y = entity.posY;
        double z = entity.posZ;
        double d0 = rand.nextGaussian() * 0.02;
        double d1 = rand.nextGaussian() * 0.02;
        double d2 = rand.nextGaussian() * 0.02;
        float healthDifference = ((EntityLivingBase) entity).getMaxHealth() - ((EntityLivingBase) entity).getHealth();
        float playerHealthDifference = player.getMaxHealth() - player.getHealth();
        boolean canBeHealed = (healthDifference != 0);

        Collection<PotionEffect> effects = ((EntityLivingBase) entity).getActivePotionEffects();
        for (PotionEffect effect : effects) {
            if (effect.getPotion() == MobEffects.regeneration) canBeHealed = false;
        }


        if (entity instanceof EntityLivingBase && (player.isCreative() || stack.getItemDamage() < stack.getMaxDamage()) && canBeHealed) {
            //entity.worldObj.spawnParticle(EnumParticleTypes.HEART, x, y + 1, z, 0.0, 0.0, 0.0, new int[40]);
            //if (healthDifference > 3) {
            for (int i = 1; i <= 3; i++) {
                entity.worldObj.spawnParticle(EnumParticleTypes.HEART, x + (double) (rand.nextFloat() * entity.width * 2.0F) - (double) entity.width, y + 1.0D +
                        (double) (rand.nextFloat() * entity.height), z + (double) (rand.nextFloat() * entity.width * 2.0F) - (double) entity.width, d0, d1, d2, new int[0]);
            }
            /*} else {
                for (int i = 1; i <= healthDifference; i++) {
                    entity.worldObj.spawnParticle(EnumParticleTypes.HEART, x + (double) (rand.nextFloat() * entity.width * 2.0F) - (double) entity.width, y + 1.0D +
                            (double) (rand.nextFloat() * entity.height), z + (double) (rand.nextFloat() * entity.width * 2.0F) - (double) entity.width, d0, d1, d2, new int[0]);
                }
            }*/

            if (healthDifference != 0 || playerHealthDifference != 0 && canBeHealed) damage(player, stack, 1);
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.regeneration, 40, 3));
            //((EntityLivingBase) entity).heal(4.0F);
            player.heal(2.0F);
        }
        canBeHealed = true;
        return true;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (stack.getItemDamage() > 0) {
            if (ticks == 60) {
                stack.setItemDamage(stack.getItemDamage() - 1);
                ticks = 0;
            } else ticks++;
        }
    }

    public void damage(EntityPlayer player, ItemStack stack, int damageCount) {
        if (!player.isCreative()) {
            stack.setItemDamage(stack.getItemDamage() + damageCount);
        }
    }

}