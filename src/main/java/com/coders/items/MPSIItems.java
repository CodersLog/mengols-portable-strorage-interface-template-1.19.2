package com.coders.items;

import com.coders.MengolsPortableStrorageInterface;
import com.coders.items.custom.ItemPortableStorageInterface;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MPSIItems {


    public static final Item PORTABLECORE = registerItems("portablecore", new Item(
            new FabricItemSettings()));

    public static final Item PORTABLESTORAGEINTERFACE = registerItems("portablestorageinterface", new ItemPortableStorageInterface(
            new FabricItemSettings()));

    private static Item registerItems(String name, Item item) {

        return Registry.register(Registry.ITEM, new Identifier(MengolsPortableStrorageInterface.MODID, name), item);

    }

    public static void registerModItems() {



    }
}


