package com.github.ArnorDorian.cp.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Created by arnor on 2016/6/8 0008.
 */
public class EntityCheekGrenade extends EntityThrowable {

    public EntityCheekGrenade(World worldIn) {
        super(worldIn);
        this.setSize(1.0F, 1.0F);
    }

    public EntityCheekGrenade(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityCheekGrenade(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        /*if (!worldObj.isRemote)*/
        this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, -3, false);
        this.setDead();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        this.setBeenAttacked();

        if (source.getEntity() != null) {

            Vec3d vec3d = source.getEntity().getLookVec();

            if (vec3d != null) {
                this.motionX = vec3d.xCoord;
                this.motionY = vec3d.yCoord;
                this.motionZ = vec3d.zCoord;
                /*this.accelerationX = this.motionX * 0.1D;
                this.accelerationY = this.motionY * 0.1D;
                this.accelerationZ = this.motionZ * 0.1D;*/
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote)
            this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
    }

    /*public EntityCheekGrenade(World worldIn) {
        super(worldIn);
    }

    @SideOnly(Side.CLIENT)
    public EntityCheekGrenade(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.setSize(0.5F, 0.5F);
    }

    public EntityCheekGrenade(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.5F, 0.5F);
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!worldObj.isRemote && result.entityHit != this.shootingEntity) {
            this.getEntityWorld().createExplosion(this, this.posX, this.posY, this.posZ, 3, false);
            this.setDead();
        }
    }

    @Override
    public boolean isBurning() {
        return false;
    }*/

}
