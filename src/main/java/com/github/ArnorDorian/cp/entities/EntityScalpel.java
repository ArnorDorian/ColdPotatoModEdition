package com.github.ArnorDorian.cp.entities;

import com.github.ArnorDorian.cp.common.DamageSourceLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class EntityScalpel extends EntityThrowable {

    public EntityScalpel(World worldIn) {
        super(worldIn);
    }

    public EntityScalpel(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityScalpel(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

        DamageSource damageSource = DamageSourceLoader.causeScalpelDamage(this, this.getThrower());
        Entity entity = result.entityHit;
        double d0 = this.rand.nextGaussian() * 0.02;
        double d1 = this.rand.nextGaussian() * 0.02;
        double d2 = this.rand.nextGaussian() * 0.02;

        if (!this.worldObj.isRemote && entity != null) {
            entity.worldObj.spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, this.posX + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, this.posY + 1.0D +
                    (double) (this.rand.nextFloat() * this.height), this.posZ + (double) (this.rand.nextFloat() * this.width * 2.0F) - (double) this.width, d0, d1, d2, new int[0]);
            if (entity.getTeam() != null) {
                if (entity.getTeam() != this.getThrower().getTeam() && entity != this.getThrower()) {
                    entity.attackEntityFrom(damageSource, 7.0F);
                    entity.hurtResistantTime = 0;
                }
            } else {
                entity.attackEntityFrom(damageSource, 7.0F);
            }
            //entity.worldObj.spawnParticle(EnumParticleTypes.DAMAGE_INDICATOR, entity.posX, entity.posY + 1, entity.posZ, 0.0, 0.0, 0.0, new int[40]);

        }
        this.setDead();
    }


/*
    public ItemStack getArrowStack() {
        return null;
    }

    @Override
    public void func_184549_a(RayTraceResult p_184549_1_) {

        Entity entity = p_184549_1_.entityHit;

        if (entity != null) {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int i = MathHelper.ceiling_double_int((double) f * 8.0D);

            if (this.getIsCritical()) {
                i += this.rand.nextInt(i / 2 + 2);
            }

            DamageSource damagesource = new DamageSource("byScalpel");

            this.playSound(SoundEvents.entity_snowball_throw, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));

            this.setDead();
        }
        else{
            this.motionX *= -0.10000000149011612D;
            this.motionY *= -0.10000000149011612D;
            this.motionZ *= -0.10000000149011612D;
            this.rotationYaw += 180.0F;
            this.prevRotationYaw += 180.0F;

            if (!this.worldObj.isRemote && this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ < 0.0010000000474974513D)
            {
                this.setDead();

            }
        }

    }*/

}
