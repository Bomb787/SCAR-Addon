package moguns;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.client.render.gun.model.SimpleModel;
import com.mrcrayfish.guns.common.ProjectileManager;

import moguns.client.SpecialModels;
import moguns.client.render.gun.model.*;
import moguns.entities.FireballProjectileEntity;
import moguns.entities.FlareProjectileEntity;
import moguns.entities.TakiProjectileEntity;
import moguns.events.RecoilShootingEvent;
import moguns.init.EntityInit;
import moguns.init.ItemInit;
import moguns.init.ParticleInit;
import moguns.init.SoundInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("moguns")
public class MoGuns {
	
	public static final String MOD_ID = "moguns";
	
	//Creative Tab
	public static final CreativeModeTab GROUP = new CreativeModeTab(MOD_ID) {
		/*
		 * We use this to tell the game what item to use as the icon for the tab.
		 * You can just use "return new ItemStack(Items.xxx);" replacing xxx with the item you want, instead of adding ".get()" at the end if you want to use a vanilla item.
		 */
		@Override
		public ItemStack makeIcon() {
			//Gets the gun item, unneeded if you're not gonna use a gun.
			ItemStack stack = new ItemStack(ItemInit.SCAR_L.get());
			//Makes sure that the icon gun has full ammo so the durability bar doesn't show up.
			stack.getOrCreateTag().putInt("AmmoCount", ItemInit.SCAR_L.get().getGun().getGeneral().getMaxAmmo());
			//Returns the loaded gun icon.
	        return stack;
		}
    };
	
	public MoGuns() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
		//Registers all of the Deferred Registers from our init classes.
		ItemInit.ITEMS.register(bus);
		SoundInit.SOUNDS.register(bus);
		EntityInit.ENTITIES.register(bus);
		ParticleInit.PARTICLES.register(bus);
		bus.addListener(this::onClientSetup);
	}
	
	//This is the common setup event, only really registers the Taki entity to the item
	private void setup(final FMLCommonSetupEvent event) {
		System.out.println("MoGuns preinit, if you're reading this then I hope you're having a nice day :)");
		System.out.println("Slava Ukraini! Heroiam Slava!");
		ProjectileManager.getInstance().registerFactory(ItemInit.AMMO_TAKI.get(), ((world, livingEntity, itemStack, gunItem, gun) -> new TakiProjectileEntity(EntityInit.TAKI.get(), world, livingEntity, itemStack, gunItem, gun)));
		ProjectileManager.getInstance().registerFactory(Items.MAGMA_CREAM, ((world, livingEntity, itemStack, gunItem, gun) -> new FireballProjectileEntity(EntityInit.FLAMMABLE_GEL.get(), world, livingEntity, itemStack, gunItem, gun)));
		ProjectileManager.getInstance().registerFactory(ItemInit.FLARE.get(), ((world, livingEntity, itemStack, gunItem, gun) -> new FlareProjectileEntity(EntityInit.FLARE.get(), world, livingEntity, itemStack, gunItem, gun)));
	}
	
	//This is the client setup event.
	private void onClientSetup(FMLClientSetupEvent event) {
		//Register all of our models.
		ModelOverrides.register(ItemInit.SCAR_L.get(), new ScarLModel());
		ModelOverrides.register(ItemInit.G36C.get(), new G36CModel());
		ModelOverrides.register(ItemInit.AKM.get(), new AKMModel());
		ModelOverrides.register(ItemInit.AS_VAL.get(), new ASVALModel());
		ModelOverrides.register(ItemInit.FAMAS.get(), new FamasModel());
		ModelOverrides.register(ItemInit.M1_GARAND.get(), new M1GarandModel());
		ModelOverrides.register(ItemInit.THOMPSON.get(), new ThompsonModel());
		ModelOverrides.register(ItemInit.AKM_CUSTOM.get(), new AKMCustomModel());
		ModelOverrides.register(ItemInit.AWP.get(), new AWPModel());
		ModelOverrides.register(ItemInit.BENELLI.get(), new BenelliModel());
		ModelOverrides.register(ItemInit.GLOCK17.get(), new Glock17Model());
		ModelOverrides.register(ItemInit.M14_EBR.get(), new M14EBRModel());
		ModelOverrides.register(ItemInit.M14.get(), new M14Model());
		ModelOverrides.register(ItemInit.M1911.get(), new M1911Model());
		ModelOverrides.register(ItemInit.MOSSBERG.get(), new MossbergModel());
		ModelOverrides.register(ItemInit.REMINGTON_870.get(), new Remington870Model());
		ModelOverrides.register(ItemInit.SCAR_H.get(), new ScarHModel());
		ModelOverrides.register(ItemInit.VSS_VINTOREZ.get(), new VSSVintorezModel());
		ModelOverrides.register(ItemInit.WALTHER_PPK.get(), new WaltherPPKModel());
		ModelOverrides.register(ItemInit.WELROD.get(), new WelrodModel());
		ModelOverrides.register(ItemInit.LANCHESTER.get(), new LanchesterModel());
		ModelOverrides.register(ItemInit.PPSH.get(), new PPSH41Model());
		ModelOverrides.register(ItemInit.BUTTERFLY_GUN.get(), new ButterflyModel());
		ModelOverrides.register(ItemInit.WRAPPED_RIFLE.get(), new WrappedRifleModel());
		ModelOverrides.register(ItemInit.HELLFIRE.get(), new HellfireModel());
		ModelOverrides.register(ItemInit.BLUE_HEAT.get(), new BlueHeatModel());
		ModelOverrides.register(ItemInit.HOG_BONKER.get(), new HogBonkerModel());
		ModelOverrides.register(ItemInit.DOUBLE_BARREL.get(), new DoubleBarrelModel());
		MinecraftForge.EVENT_BUS.register(RecoilShootingEvent.get());
		ModelOverrides.register(ItemInit.BAKER.get(), new SimpleModel(SpecialModels.BAKER_RIFLE::getModel));
		ModelOverrides.register(ItemInit.BIG_IRON.get(), new SimpleModel(SpecialModels.BIG_IRON::getModel));
		ModelOverrides.register(ItemInit.FLAMER.get(), new SimpleModel(SpecialModels.FLAMER::getModel));
		ModelOverrides.register(ItemInit.FLARE_GUN.get(), new SimpleModel(SpecialModels.FLARE_GUN::getModel));
		ModelOverrides.register(ItemInit.GLOCKEST_GLOCK.get(), new SimpleModel(SpecialModels.GLOCKEST_GLOCK::getModel));
		ModelOverrides.register(ItemInit.M2.get(), new SimpleModel(SpecialModels.M2::getModel));
		ModelOverrides.register(ItemInit.M16A1.get(), new SimpleModel(SpecialModels.M16A1::getModel));
		ModelOverrides.register(ItemInit.MICRO_UZI.get(), new SimpleModel(SpecialModels.MICRO_UZI::getModel));
		ModelOverrides.register(ItemInit.MP5.get(), new SimpleModel(SpecialModels.MP5::getModel));
		ModelOverrides.register(ItemInit.TRASHCAN.get(), new SimpleModel(SpecialModels.TRASHCAN::getModel));
		ModelOverrides.register(ItemInit.UZI.get(), new SimpleModel(SpecialModels.UZI::getModel));
		ModelOverrides.register(ItemInit.M16_LSW.get(), new SimpleModel(SpecialModels.M16_LSW::getModel));
		ModelOverrides.register(ItemInit.M60.get(), new SimpleModel(SpecialModels.M60::getModel));
		ModelOverrides.register(ItemInit.M107.get(), new M107CustomModel());
		ModelOverrides.register(ItemInit.M249.get(), new SimpleModel(SpecialModels.M249::getModel));
		ModelOverrides.register(ItemInit.TAT.get(), new SimpleModel(SpecialModels.TAT::getModel));
	}

}