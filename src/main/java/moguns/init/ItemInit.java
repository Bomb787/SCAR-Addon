package moguns.init;

import com.mrcrayfish.guns.common.GunModifiers;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.ScopeItem;
import com.mrcrayfish.guns.item.attachment.impl.Scope;
import moguns.MoGuns;
import moguns.items.BurstGunItem;
import moguns.items.GarandGunItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class is where all of the mod's items are registered
 * Author: Bomb787
 */
public class ItemInit {
	
	/*
     * This creates a Deferred Register where all of the items will be registered
     * This is called and added to the event bus in the main mod file.
     */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			MoGuns.MOD_ID);
	
	//Gun Items
	public static final RegistryObject<GunItem> SCARL = ITEMS.register("scarl", 
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	public static final RegistryObject<BurstGunItem> G36C = ITEMS.register("g36c", 
			() -> new BurstGunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> BIG_IRON = ITEMS.register("big_iron", 
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> TRASHCAN = ITEMS.register("trashcan", 
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> AKM = ITEMS.register("akm",
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> AS_VAL = ITEMS.register("as_val",
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> THOMPSON = ITEMS.register("thompson",
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M16A1 = ITEMS.register("m16a1",
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> FAMAS = ITEMS.register("famas",
			() -> new GunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));

	public static final RegistryObject<GarandGunItem> M1_GARAND = ITEMS.register("m1_garand",
			() -> new GarandGunItem(new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	//Scope Items
	public static final RegistryObject<ScopeItem> REFLEX_SIGHT = ITEMS.register("reflex_sight",
			() -> new ScopeItem(Scope.create(0.1F, 2F, GunModifiers.SLOW_ADS).viewFinderOffset(0.3), new Item.Properties().maxStackSize(1).group(MoGuns.GROUP)));
	
	//Ammo Items
	public static final RegistryObject<Item> AMMO762X51 = ITEMS.register("762x51",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO556X45 = ITEMS.register("556x45",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> GARBAGE = ITEMS.register("garbage", 
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO9x39 = ITEMS.register("9x39",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));

	public static final RegistryObject<Item> AMMO9X19 = ITEMS.register("9x19",
			() -> new AmmoItem(new Item.Properties().group(MoGuns.GROUP)));

}