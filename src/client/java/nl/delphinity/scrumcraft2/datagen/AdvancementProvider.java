package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.triggers.ChangeDimensionTrigger;
import net.minecraft.advancements.triggers.ConsumeItemTrigger;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.CuredZombieVillagerTrigger;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.ClientAsset;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import nl.delphinity.scrumcraft2.init.ModDimensions;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class AdvancementProvider extends FabricAdvancementProvider {
    protected AdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/advancements">documentation</a>
     */
    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        HolderGetter<Item> items = registryLookup.lookupOrThrow(Registries.ITEM);

        AdvancementHolder base = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStackTemplate(ModItems.SCRUM_BALL),
                        Component.literal("Scrumcraft2"),
                        Component.literal("Welcome to Scrumcraft2! NOW SCRUM!"),
                        Optional.of(new ClientAsset.ResourceTexture(identifierOf("gui/advancements/scrum_ground"))),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                ))
                .addCriterion("scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRUM_BALL))
                .save(consumer, identifierOf("base"));

        // Scrum balls
        AdvancementHolder ultimateScrumBall = advancement(consumer, base, "got_ultimate_scrum_ball", ModItems.ULTIMATE_SCRUM_BALL,
                "AAAAHHHH", "Get an Ultimate Scrum Ball",
                "ultimate_scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ULTIMATE_SCRUM_BALL));
        advancement(consumer, ultimateScrumBall, "scrum_master", ModItems.SCRUM_MASTER_BALL,
                "Scrum Master", "Be a certified Scrum Master",
                "scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRUM_MASTER_BALL));

        // LinkedIn
        AdvancementHolder linkedIn = advancement(consumer, base, "linked_in", ModItems.LINKED_IN,
                "Linked in", "Get linked in",
                "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LINKED_IN));
        advancement(consumer, linkedIn, "linked_out", ModItems.EVIL_LINKED_IN,
                "Linked out", "Get Evil linked in (haha get it?)",
                "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EVIL_LINKED_IN));
        advancement(consumer, linkedIn, "agartha_linked_in", ModItems.AGARTHA_LINKED_IN,
                "DESCEND to linked in", "Get Agartha linked in",
                "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AGARTHA_LINKED_IN));

        advancement(consumer, base, "agartha", ModItems.VERY_WHITE_BREW,
                "DESCEND...", "Enter Agartha.",
                "agarthian", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(ModDimensions.AGARTHA));
        advancement(consumer, base, "cured_zombi", ModItems.WEAK_HEART,
                "Deception", "Decieve a Zombie villager",
                "cured_vilg", CuredZombieVillagerTrigger.TriggerInstance.curedZombieVillager());

        // Eating/drinking/using items
        advancement(consumer, base, "drink_ayran", ModItems.AYRAN,
                "Drink... Ayran", "Yum..!",
                "drink_ayran", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.AYRAN));
        advancement(consumer, base, "take_terrorism", ModItems.POTION_OF_TERRORISM,
                "What have you done..", "Take a Potion Of Terrorism",
                "take_terrorism", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.POTION_OF_TERRORISM));
        advancement(consumer, base, "take_bowl_of_code", ModItems.BOWL_OF_CODE,
                "you're too slow", "Become an Eclipse user",
                "take_bowl_of_code", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.BOWL_OF_CODE));
        advancement(consumer, base, "eat_worste_bolus", ModItems.WORSTE_BOLUS,
                "ASCEND!", "Eat a Worste Bolus",
                "eat_worste_bolus", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.WORSTE_BOLUS));
        advancement(consumer, base, "eat_golden_fish", ModItems.GOLDEN_FISH,
                "Feesh", "Become blub",
                "eat_golden_fish", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.GOLDEN_FISH));
        advancement(consumer, base, "ns_train", ModItems.NS_TRAIN,
                "ChooChoo", "Take the NS train",
                "ns_train", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.NS_TRAIN));
        advancement(consumer, base, "catamaran", ModItems.CATAMARAN,
                "Very agile", "Use the catamaran",
                "catamaran", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.CATAMARAN));
        advancement(consumer, base, "pull_request_declined", ModItems.PULLREQUEST_DECLINED,
                "Pull request declined", "Declie someone's pull request",
                "pull_request_declined", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.PULLREQUEST_DECLINED));

        // Java
        AdvancementHolder exception = advancement(consumer, base, "exception", ModItems.EXCEPTION,
                "throws Exception", "Get an Exception to throw",
                "exception", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EXCEPTION));
        advancement(consumer, exception, "class_cast_exception", ModItems.CLASS_CAST_EXCEPTION,
                "(Pig) cow", "Get a ClassCastException",
                "class_cast_exception", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CLASS_CAST_EXCEPTION));
        advancement(consumer, base, "cup_of_java", ModItems.CUP_OF_JAVA,
                "Write once, run anywhere", "Drink a Cup of Java",
                "cup_of_java", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.CUP_OF_JAVA));
        advancement(consumer, base, "abstract_singleton_proxy_factory_bean", ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN,
                "Enterprise ready", "Get the AbstractSingletonProxyFactoryBean",
                "abstract_singleton_proxy_factory_bean", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ABSTRACT_SINGLETON_PROXY_FACTORY_BEAN));

        // Tomcat
        advancement(consumer, base, "war_file", ModItems.WAR_FILE,
                "Deploy on friday", "Get a WAR file for Tomcat",
                "war_file", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WAR_FILE));

        // Hibernate
        AdvancementHolder hibernateSession = advancement(consumer, base, "hibernate_session", ModItems.HIBERNATE_SESSION,
                "Hibernate", "Drink a Hibernate Session",
                "hibernate_session", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.HIBERNATE_SESSION));
        advancement(consumer, hibernateSession, "lazy_loaded_sandwich", ModItems.LAZY_LOADED_SANDWICH,
                "Lazy loading", "Eat a Lazy Loaded Sandwich",
                "lazy_loaded_sandwich", ConsumeItemTrigger.TriggerInstance.usedItem(items, ModItems.LAZY_LOADED_SANDWICH));

        // Struts
        advancement(consumer, base, "struts", ModItems.STRUTS,
                "Legacy code", "Get Struts, it's deprecated since 2013",
                "struts", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRUTS));
    }

    private AdvancementHolder advancement(Consumer<AdvancementHolder> consumer, AdvancementHolder parent, String id, Item icon,
                                          String title, String description, String criterionName, Criterion<?> criterion) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(icon, Component.literal(title), Component.literal(description), AdvancementType.TASK, true, true, false)
                .addCriterion(criterionName, criterion)
                .save(consumer, identifierOf(id));
    }
}
