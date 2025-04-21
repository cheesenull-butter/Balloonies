package cheesenull.balloonies.client;

import cheesenull.balloonies.Balloonies;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class BallooniesDamageTypes {

    public static final RegistryKey<DamageType> HARPOON =
            RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Balloonies.MOD_ID, "harpoon"));

}
