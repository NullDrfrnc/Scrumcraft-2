package nl.delphinity.scrumcraft2.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ChangeDimensionTrigger;
import net.minecraft.advancements.criterion.ConsumeItemTrigger;
import net.minecraft.advancements.criterion.CuredZombieVillagerTrigger;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import nl.delphinity.scrumcraft2.Scrumcraft2;
import nl.delphinity.scrumcraft2.init.ModItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static nl.delphinity.scrumcraft2.Scrumcraft2.identifierOf;

public class AdvancementProvider extends FabricAdvancementProvider {
    protected AdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }
    public static final ResourceKey<Level> AGARTHA = ResourceKey.create(Registries.DIMENSION, identifierOf("agartha_dim"));
    /**
     * <a href="https://docs.fabricmc.net/develop/data-generation/advancements">documentation</a>
     * @param registryLookup registryLookup
     * @param consumer consumer
     */
    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder scrumcraft2BaseAdvace = Advancement.Builder.advancement()
                .display(
                        ModItems.SCRUM_BALL,
                        Component.literal("Scrumcraft2"),
                        Component.literal("Welcome to Scrumcraft2! NOW SCRUM!"),
                        identifierOf("gui/advancements/scrum_ground"),
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRUM_BALL))
                .save(consumer, Scrumcraft2.MOD_ID + ":base");

        AdvancementHolder getUltimateScrumBall = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.ULTIMATE_SCRUM_BALL,
                        Component.literal("AAAAHHHH"),
                        Component.literal("Get an Ultimate Scrum Ball"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("ultimate_scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ULTIMATE_SCRUM_BALL))
                .save(consumer, Scrumcraft2.MOD_ID + ":got_ultimate_scrum_ball");

        AdvancementHolder getScrumMasterBall = Advancement.Builder.advancement()
                .parent(getUltimateScrumBall)
                .display(
                        ModItems.SCRUM_MASTER_BALL,
                        Component.literal("Scrum Master"),
                        Component.literal("Be a certified Scrum Master"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("scrum_ball", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SCRUM_MASTER_BALL))
                .save(consumer, Scrumcraft2.MOD_ID + ":scrum_master");

        AdvancementHolder getLinkedIn = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.LINKED_IN,
                        Component.literal("Linked in"),
                        Component.literal("Get linked in"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.LINKED_IN))
                .save(consumer, Scrumcraft2.MOD_ID + ":linked_in");

        AdvancementHolder getEvilLinkedIn = Advancement.Builder.advancement()
                .parent(getLinkedIn)
                .display(
                        ModItems.EVIL_LINKED_IN,
                        Component.literal("Linked out"),
                        Component.literal("Get Evil linked in (haha get it?)"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.EVIL_LINKED_IN))
                .save(consumer, Scrumcraft2.MOD_ID + ":linked_out");

        AdvancementHolder getAgarthaLinkedIn = Advancement.Builder.advancement()
                .parent(getLinkedIn)
                .display(
                        ModItems.AGARTHA_LINKED_IN,
                        Component.literal("DESCEND to linked in"),
                        Component.literal("Get Agartha linked in"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "linked_in", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AGARTHA_LINKED_IN))
                .save(consumer, Scrumcraft2.MOD_ID + ":agartha_linked_in");

        AdvancementHolder getToAgartha = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.VERY_WHITE_BREW,
                        Component.literal("DESCEND..."),
                        Component.literal("Enter Agartha."),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "agarthian", ChangeDimensionTrigger.TriggerInstance.changedDimensionTo(AGARTHA))
                .save(consumer, Scrumcraft2.MOD_ID + ":agartha");

        AdvancementHolder drinkAyran = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.AYRAN,
                        Component.literal("Drink... Ayran"),
                        Component.literal("Yum..!"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "drink_ayran", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.AYRAN))
                .save(consumer, Scrumcraft2.MOD_ID + ":drink_ayran");

        AdvancementHolder takeTerroristPotion = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.POTION_OF_TERRORISM,
                        Component.literal("What have you done.."),
                        Component.literal("Take a Potion Of Terrorism"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "take_terrorism", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.POTION_OF_TERRORISM))
                .save(consumer, Scrumcraft2.MOD_ID + ":take_terrorism");

        AdvancementHolder takeBowlOfCode = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.BOWL_OF_CODE,
                        Component.literal("you're too slow"),
                        Component.literal("Become an Eclipse user"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "take_bowl_of_code", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.BOWL_OF_CODE))
                .save(consumer, Scrumcraft2.MOD_ID + ":take_bowl_of_code");

        AdvancementHolder takeWorsteBolus = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.WORSTE_BOLUS,
                        Component.literal("ASCEND!"),
                        Component.literal("Eat a Worste Bolus"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "eat_worste_bolus", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.WORSTE_BOLUS))
                .save(consumer, Scrumcraft2.MOD_ID + ":eat_worste_bolus");

        AdvancementHolder eatGoldenFish = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.GOLDEN_FISH,
                        Component.literal("Feesh"),
                        Component.literal("Become blub"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "eat_golden_fish", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.GOLDEN_FISH))
                .save(consumer, Scrumcraft2.MOD_ID + ":eat_golden_fish");

        AdvancementHolder decieveZombie = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.WEAK_HEART,
                        Component.literal("Deception"),
                        Component.literal("Decieve a Zombie villager"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "cured_vilg", CuredZombieVillagerTrigger.TriggerInstance.curedZombieVillager())
                .save(consumer, Scrumcraft2.MOD_ID + ":cured_zombi");

        AdvancementHolder chooChoo = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.NS_TRAIN,
                        Component.literal("ChooChoo"),
                        Component.literal("Take the NS train"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "ns_train", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.NS_TRAIN))
                .save(consumer, Scrumcraft2.MOD_ID + ":ns_train");

        AdvancementHolder veryAgile = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.CATAMARAN,
                        Component.literal("Very agile"),
                        Component.literal("Use the catamaran"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "catamaran", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.CATAMARAN))
                .save(consumer, Scrumcraft2.MOD_ID + ":catamaran");

        AdvancementHolder pullRequestDeclied = Advancement.Builder.advancement()
                .parent(scrumcraft2BaseAdvace)
                .display(
                        ModItems.PULLREQUEST_DECLINED,
                        Component.literal("Pull request declined"),
                        Component.literal("Declie someone's pull request"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion(
                        "pull_request_declined", ConsumeItemTrigger.TriggerInstance.usedItem(registryLookup.lookupOrThrow(Registries.ITEM) ,ModItems.PULLREQUEST_DECLINED))
                .save(consumer, Scrumcraft2.MOD_ID + ":pull_request_declined");

    }
}
