package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.vehicle.minecart.Minecart;
import net.minecraft.world.level.Level;

import java.util.List;

public class NsTrain extends VehicleSpawnerItem {
    private static final List<EntityType<?>> CARTS = List.of(
            EntityTypes.MINECART,
            EntityTypes.TNT_MINECART,
            EntityTypes.HOPPER_MINECART,
            EntityTypes.CHEST_MINECART,
            EntityTypes.FURNACE_MINECART,
            EntityTypes.COMMAND_BLOCK_MINECART,
            EntityTypes.SPAWNER_MINECART
    );

    public NsTrain(Properties properties) {
        super(properties);
    }

    @Override
    protected Entity createVehicle(Level level) {
        return new Minecart(Util.getRandom(CARTS, level.getRandom()), level);
    }
}
