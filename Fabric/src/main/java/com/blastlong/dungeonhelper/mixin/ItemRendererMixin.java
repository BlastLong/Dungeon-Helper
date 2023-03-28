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
    public void getModelMixin(ItemStack stack, Level level, LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> cir) {
        cir.cancel();

        BakedModel bakedModel;
        CustomEnchantType type;
        if (stack.is(Items.TRIDENT)) {
            bakedModel = this.itemModelShaper.getModelManager().getModel(ItemRenderer.TRIDENT_IN_HAND_MODEL);
        } else if (stack.is(Items.SPYGLASS)) {
            bakedModel = this.itemModelShaper.getModelManager().getModel(ItemRenderer.SPYGLASS_IN_HAND_MODEL);
        } else if ((type = CEData.getType(stack)) != null) {
            switch(type) {
                case COMMON -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.COMMON_BOOK, stack.getCount()));
                case UNCOMMON -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.UNCOMMON_BOOK, stack.getCount()));
                case RARE -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.RARE_BOOK, stack.getCount()));
                case EPIC -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.EPIC_BOOK, stack.getCount()));
                case LEGENDARY -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.LEGENDARY_BOOK, stack.getCount()));
                case MYTHIC -> bakedModel = this.itemModelShaper.getItemModel(new ItemStack(DungeonItems.MYTHIC_BOOK, stack.getCount()));
                default -> bakedModel = this.itemModelShaper.getItemModel(stack);
            }
        } else {
            bakedModel = this.itemModelShaper.getItemModel(stack);
        }

        ClientLevel clientWorld = level instanceof ClientLevel ? (ClientLevel)level : null;
        BakedModel bakedModel2 = bakedModel.getOverrides().resolve(bakedModel, stack, clientWorld, entity, seed);
        cir.setReturnValue(bakedModel2 == null ? this.itemModelShaper.getModelManager().getMissingModel() : bakedModel2);
    }
}
