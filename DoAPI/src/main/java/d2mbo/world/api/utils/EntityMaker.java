package d2mbo.world.api.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffect;

import java.util.HashMap;
import java.util.List;

public class EntityMaker {
    @Getter @Setter private EntityType entityType;
    @Getter @Setter private List<PotionEffect> effects;
    @Getter @Setter private String name;
    @Getter @Setter double hearts = 24.0;
    @Getter @Setter HashMap<Integer, PotionEffect> hitEffects;
}
