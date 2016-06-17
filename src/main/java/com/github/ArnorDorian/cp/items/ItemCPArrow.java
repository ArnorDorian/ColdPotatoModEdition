package com.github.ArnorDorian.cp.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by arnor on 2016/6/12 0012.
 */
public class ItemCPArrow extends ItemArrow {

    public ItemCPArrow() {

    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return stack.getMetadata() == 0 ? "windy_arrow" : "icy_arrow";
    }
}
