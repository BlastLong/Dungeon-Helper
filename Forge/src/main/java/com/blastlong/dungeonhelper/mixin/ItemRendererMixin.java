package com.blastlong.dungeonhelper.mixin;

import com.blastlong.dungeonhelper.item.DungeonItems;
import com.blastlong.dungeonhelper.util.CEData;
import com.blastlong.dungeonhelper.util.CustomEnchantType;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @Shadow
    @Final
    private ItemModelShaper itemModelShaper;

    @Inject(method = {"getModel(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/LivingEntity;I)Lnet/minecraft/client/resources/model/BakedModel;"}, at = {@At("HEAD")}, cancellable = true)
    public void getModelMixin(ItemStack p_174265_, Level p_174266_, LivingEntity p_174267_, int p_174268_, CallbackInfoReturnable<BakedModel> cir) {
        cir.cancel();

        BakedModel bakedmodel;
        CustomEnchantType type;
        if (p_174265_.is(Items.TRIDENT)) {
            bakedmodel = this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation("minecraft:trident_in_hand#inventory"));
        } else if (p_174265_.is(Items.SPYGLASS)) {
            bakedmodel = this.itemModelShaper.getModelManager().getModel(new ModelResourceLocation("minecraft:spyglass_in_hand#inventory"));
        } else if ((type = CEData.getType(p_174265_)) != null) {
            // Minecraft.getInstance().player.displayClientMessage(Component.literal("ITEM CODE: " + Item.getId(p_174265_.getItem())), false);
            switch(type) {
                case COMMON -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.COMMON_BOOK.get(), p_174265_.getCount()));
                case UNCOMMON -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.UNCOMMON_BOOK.get(), p_174265_.getCount()));
                case RARE -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.RARE_BOOK.get(), p_174265_.getCount()));
                case EPIC -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.EPIC_BOOK.get(), p_174265_.getCount()));
                case LEGENDARY -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.LEGENDARY_BOOK.get(), p_174265_.getCount()));
                case MYTHIC -> bakedmodel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.MYTHIC_BOOK.get(), p_174265_.getCount()));
                default -> bakedmodel = this.itemModelShaper.getItemModel(p_174265_);
            }
        } else {
            bakedmodel = this.itemModelShaper.getItemModel(p_174265_);
        }

        ClientLevel clientlevel = p_174266_ instanceof ClientLevel ? (ClientLevel)p_174266_ : null;
        BakedModel bakedmodel1 = bakedmodel.getOverrides().resolve(bakedmodel, p_174265_, clientlevel, p_174267_, p_174268_);

        cir.setReturnValue(bakedmodel1 == null ? this.itemModelShaper.getModelManager().getMissingModel() : bakedmodel1);
    }
}
