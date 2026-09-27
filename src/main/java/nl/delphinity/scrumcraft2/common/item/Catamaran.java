package nl.delphinity.scrumcraft2.common.item;

import net.minecraft.util.Util;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.List;

public class Catamaran extends VehicleSpawnerItem {
    private static final List<EntityType<Boat>> BOATS = List.of(
            EntityTypes.OAK_BOAT,
            EntityTypes.SPRUCE_BOAT,
            EntityTypes.BIRCH_BOAT,
            EntityTypes.JUNGLE_BOAT,
            EntityTypes.ACACIA_BOAT,
            EntityTypes.DARK_OAK_BOAT,
            EntityTypes.MANGROVE_BOAT,
            EntityTypes.CHERRY_BOAT,
            EntityTypes.PALE_OAK_BOAT
    );

    public Catamaran(Properties properties) {
        super(properties);
    }

    @Override
    protected Entity createVehicle(Level level) {
        return new Boat(Util.getRandom(BOATS, level.getRandom()), level, () -> ModItems.CATAMARAN);
    }
}
