package com.blastlong.dungeonhelper.mixin;

import com.blastlong.dungeonhelper.DungeonHelperClient;
import com.blastlong.dungeonhelper.gui.SkillCooltimeGui;
import com.blastlong.dungeonhelper.gui.screen.SkillCooltimeSettingsScreen;
import com.blastlong.dungeonhelper.util.ClassCategory;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin {

    private static final int VALID_BLADE_DASH_DISTANCE = 110;
    private static final int VALID_BLADE_DANCE_DISTANCE = 5;
    private static final int VALID_DRAGON_DASH_DISTANCE = 5;
    private static final int VALID_DRAGON_SMASH_DISTANCE = 5;
    private static final int VALID_AGILE_STRIKE_DISTANCE = 30;
    private static final int VALID_MULTIPLE_BLOW_DISTANCE = 5;

    @Shadow
    private ClientLevel level;

    @Inject(method = "handleSetEquipment(Lnet/minecraft/network/protocol/game/ClientboundSetEquipmentPacket;)V", at = {@At("TAIL")})
    private void handleSetEquipmentMixin(ClientboundSetEquipmentPacket clientboundSetEquipmentPacket, CallbackInfo info) {
        Entity entity = this.level.getEntity(clientboundSetEquipmentPacket.getEntity());

        if(entity instanceof ArmorStand armorStand) {
            Minecraft mc = Minecraft.getInstance();
            DungeonHelperClient client = DungeonHelperClient.getInstance();

            if (mc.player != null) {
                List<Pair<EquipmentSlot, ItemStack>> slots = clientboundSetEquipmentPacket.getSlots();
                for(Pair<EquipmentSlot, ItemStack> slot : slots) {
                    CompoundTag tag = slot.getSecond().getTag();

                    if(tag == null)
                        continue;

                    if(slot.getFirst().getName().equals("mainhand")) {
                        int id = tag.getInt("CustomModelData");
                        double distance = armorStand.position().distanceToSqr(mc.player.position());

                        /*
                        mc.player.displayClientMessage(Component.literal(tag.toString()), false);
                        mc.player.displayClientMessage(Component.literal(String.valueOf(distance)), false);
                        */

                        if(client.data.classType == ClassCategory.ASSASSIN) {
                            if(id == 2109 && distance < VALID_BLADE_DASH_DISTANCE)
                                client.updateLastDashTime();
                            else if(id == 2129 && distance < VALID_BLADE_DANCE_DISTANCE)
                                client.updateLastUltimateTime();
                        }
                        else if (client.data.classType == ClassCategory.DRAGON_WARRIOR) {
                            if(id == 2325 && distance < VALID_DRAGON_DASH_DISTANCE)
                                client.updateLastDashTime();
                            else if(id == 2334 && distance < VALID_DRAGON_SMASH_DISTANCE)
                                client.updateLastUltimateTime();
                        }
                        else if (client.data.classType == ClassCategory.MARTIAL_ARTIST) {
                            if(id == 2395 && distance < VALID_AGILE_STRIKE_DISTANCE)
                                client.updateLastDashTime();
                            else if(id == 2334 && distance < VALID_MULTIPLE_BLOW_DISTANCE)
                                client.updateLastUltimateTime();
                        }

                        // else 2329 - dragon piercing
                        // else 2109 - assassin_dash_1
                        // else 2395 - blue_strike_1
                    }
                }
            }
        }
    }
}
