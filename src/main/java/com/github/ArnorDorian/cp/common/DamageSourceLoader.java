package com.github.ArnorDorian.cp.common;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

/**
 * Created by arnor on 2016/6/6 0006.
 */
public class DamageSourceLoader {

    public static DamageSource causeScalpelDamage(Entity source,Entity indirectEntityIn){
        return (new EntityDamageSourceIndirect("byScalpel",source,indirectEntityIn)).setProjectile();
    }

}
