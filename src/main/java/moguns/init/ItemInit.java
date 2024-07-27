package moguns.init;

import com.mrcrayfish.guns.common.GunModifiers;
import com.mrcrayfish.guns.item.AmmoItem;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.ScopeItem;
import com.mrcrayfish.guns.item.attachment.impl.Scope;

import moguns.MoGuns;
import moguns.items.TrumpetItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class is where all of the mod's items are registered.
 */
public class ItemInit {
	
	/*
     * This creates a Deferred Register where all of the items will be registered
     * This is called and added to the event bus in the main mod file.
     */
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			MoGuns.MOD_ID);
	
	//Gun Items
	public static final RegistryObject<GunItem> SCAR_L = ITEMS.register("scar_l", 
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> G36C = ITEMS.register("g36c", 
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> BIG_IRON = ITEMS.register("big_iron", 
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> TRASHCAN = ITEMS.register("trashcan", 
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> AKM = ITEMS.register("akm",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> AS_VAL = ITEMS.register("as_val",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> THOMPSON = ITEMS.register("thompson",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M16A1 = ITEMS.register("m16a1",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> FAMAS = ITEMS.register("famas",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M1_GARAND = ITEMS.register("m1_garand",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> AKM_CUSTOM = ITEMS.register("akm_custom",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> AWP = ITEMS.register("awp",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> BENELLI = ITEMS.register("benelli",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> GLOCK17 = ITEMS.register("glock17",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> M14 = ITEMS.register("m14",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> M14_EBR = ITEMS.register("m14_ebr",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> M1911 = ITEMS.register("m1911",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> MOSSBERG = ITEMS.register("mossberg",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> REMINGTON_870 = ITEMS.register("remington_870",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> SCAR_H = ITEMS.register("scar_h",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> VSS_VINTOREZ = ITEMS.register("vss_vintorez",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> WALTHER_PPK = ITEMS.register("walther_ppk",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> UZI = ITEMS.register("uzi",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> WELROD = ITEMS.register("welrod",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> GLOCKEST_GLOCK = ITEMS.register("glockest_glock",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> BAKER = ITEMS.register("baker_rifle",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> LANCHESTER = ITEMS.register("lanchester",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> MP5 = ITEMS.register("mp5",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> PPSH = ITEMS.register("ppsh_41",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> MICRO_UZI = ITEMS.register("micro_uzi",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> DOUBLE_BARREL = ITEMS.register("double_barrel",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> BUTTERFLY_GUN = ITEMS.register("butterfly_gun",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> WRAPPED_RIFLE = ITEMS.register("wrapped_rifle",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> HELLFIRE = ITEMS.register("hellfire",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> BLUE_HEAT = ITEMS.register("blue_heat",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> HOG_BONKER = ITEMS.register("hog_bonker",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	public static final RegistryObject<GunItem> M2 = ITEMS.register("m2",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> FLAMER = ITEMS.register("flamer",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> FLARE_GUN = ITEMS.register("flare_gun",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M16_LSW = ITEMS.register("m16_lsw",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M60 = ITEMS.register("m60",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M107 = ITEMS.register("m107",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> M249 = ITEMS.register("m249",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

	public static final RegistryObject<GunItem> TAT = ITEMS.register("tat",
			() -> new GunItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	//Scope Items
	public static final RegistryObject<ScopeItem> REFLEX_SIGHT = ITEMS.register("reflex_sight",
			() -> new ScopeItem(Scope.builder().aimFovModifier(1.1F).modifiers(GunModifiers.SLOW_ADS).build(), new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));
	
	//Ammo Items
	public static final RegistryObject<Item> AMMO762X51 = ITEMS.register("762x51",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO556X45 = ITEMS.register("556x45",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> GARBAGE = ITEMS.register("garbage", 
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO9x39 = ITEMS.register("9x39",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));

	public static final RegistryObject<Item> AMMO9X19 = ITEMS.register("9x19",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO12GAUGE = ITEMS.register("12_gauge",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO12SLUG = ITEMS.register("slug",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO45ACP = ITEMS.register("45acp",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO3006 = ITEMS.register("3006",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO762X39 = ITEMS.register("762x39",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMOMUSKETCARTRIDGE = ITEMS.register("musket_cartridge",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO762X25 = ITEMS.register("762x25",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final RegistryObject<Item> AMMO_XMAS_LIGHT = ITEMS.register("x_mas_light",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	public static final FoodProperties TAKI = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.1F).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 40, 1), 1.0F).fast().build();
	
	public static final RegistryObject<Item> AMMO_TAKI = ITEMS.register("taki",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP).food(TAKI)));
	
	public static final RegistryObject<Item> FLARE = ITEMS.register("flare",
			() -> new AmmoItem(new Item.Properties().stacksTo(8).tab(MoGuns.GROUP)));

	public static final RegistryObject<Item> AMMO50BMG = ITEMS.register("50_bmg",
			() -> new AmmoItem(new Item.Properties().tab(MoGuns.GROUP)));
	
	//Misc Items
	public static final RegistryObject<TrumpetItem> TRUMPET = ITEMS.register("trumpet",
			() -> new TrumpetItem(new Item.Properties().stacksTo(1).tab(MoGuns.GROUP)));

}