package de.jinx.smpevents;

import de.jinx.smpevents.Config.ConfigManager;
import de.jinx.smpevents.Listeners.*;
import de.jinx.smpevents.items.CustomEnchants;
import de.jinx.smpevents.villager.VillagerHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public final class SMPEvents extends JavaPlugin {

    static SMPEvents plugin;
    public LinkedHashMap<Material,Material> autoSmeltOre = new LinkedHashMap<>();

    public ConfigManager cfM;

    public static final String WIZARDPREFIX = "§6[§bWizard§6] ";

    public List<String> phase0 = new ArrayList<>();
    public List<String> phase1 = new ArrayList<>();
    public List<String> phase2 = new ArrayList<>();
    public List<String> phase3 = new ArrayList<>();
    public List<String> phase4 = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;

        System.out.println("SPMEvents Plugin has loaded!");

        PluginManager pl = Bukkit.getPluginManager();

        pl.registerEvents(new MobDrops(),this);
        pl.registerEvents(new MobSpawning(),this);
        pl.registerEvents(new MobHandler(),this);
        pl.registerEvents(new PickaxeAutoSmelt(),this);
        pl.registerEvents(new VillagerHandler(),this);
        pl.registerEvents(new GUIHandler(),this);
        pl.registerEvents(new ScoreboadListener(),this);
        pl.registerEvents(new ForbiddeRecipe(),this);
        pl.registerEvents(new JoinListener(),this);

        getCommand("mob").setExecutor(new Commands());
        getCommand("movein").setExecutor(new Commands());
        getCommand("phase").setExecutor(new Commands());

        fillAutoSmeltList();
        fillStoryList();
        fillStoryList2();
        fillStoryList3();
        fillStoryList4();
        fillStoryList5();

        cfM = setup();

        CustomEnchants.register();
    }

    public ConfigManager setup() {
        File progressFile;

        if (!SMPEvents.getPlugin().getDataFolder().exists()) {
            SMPEvents.getPlugin().getDataFolder().mkdir();
        }

        progressFile = new File(SMPEvents.getPlugin().getDataFolder() + File.separator + "progress.yml");
        if (!progressFile.exists()) {
            try {
                progressFile.createNewFile();
                FileConfiguration progressCfg = YamlConfiguration.loadConfiguration(progressFile);
                progressCfg.set("Event.stage",0);
                progressCfg.set("Event.progress",0);
                progressCfg.set("Stage1.maxProgress",0);
                progressCfg.set("Stage2.maxProgress",0);
                progressCfg.set("Stage3.maxProgress",0);
                progressCfg.set("Stage4.maxProgress",0);
                progressCfg.save(progressFile);
            } catch (Exception e) {
                Bukkit.getServer().getConsoleSender().sendMessage("§cCould not create the progress.yml file");
            }
        }
        return new ConfigManager();
    }

    public static int randomNumber(){
        int chance = (int) ((Math.random() * 100) + 1);
        return chance;
    }

    public static boolean isPrime(int chance){
        int chanceNr = randomNumber();
        if(chanceNr <= chance){
            return true;
        }
        return false;
    }

    public static boolean isCustomItem(ItemStack item){
        if (item == null)
            return false;

        if (!item.hasItemMeta())
            return false;

        if (!item.getItemMeta().hasLore())
            return false;

        return true;
    }


    public static SMPEvents getPlugin() {
        return plugin;
    }

    public void fillAutoSmeltList(){
        autoSmeltOre.put(Material.IRON_ORE,Material.IRON_INGOT);
        autoSmeltOre.put(Material.GOLD_ORE,Material.GOLD_INGOT);
        autoSmeltOre.put(Material.COPPER_ORE,Material.COPPER_INGOT);
        autoSmeltOre.put(Material.DEEPSLATE_COPPER_ORE,Material.COPPER_INGOT);
        autoSmeltOre.put(Material.DEEPSLATE_IRON_ORE,Material.IRON_INGOT);
        autoSmeltOre.put(Material.DEEPSLATE_GOLD_ORE,Material.GOLD_INGOT);
    }

    public void fillStoryList(){
        phase0.add(WIZARDPREFIX + "§6Hello! :)");
        phase0.add(WIZARDPREFIX + "§6Im now here! And I have to say this §bTower§6 looks...");
        phase0.add(WIZARDPREFIX + "§4good?");
        phase0.add(WIZARDPREFIX + "§6Anyways there are other §cimportant§6 things right now!");
        phase0.add(WIZARDPREFIX + "§6It looks like some §cMonsters§6 are being converted into an weird but strong looking Mob.");
        phase0.add(WIZARDPREFIX + "§6Could you §ckill§6 some of them for me to study?");
        phase0.add(WIZARDPREFIX + "§4 Sneak + Right Click to open the Interface!");
    }

    public void fillStoryList2(){
        phase1.add(WIZARDPREFIX + "§6As it seems those §lAstroiders§6 are made out of the §cAncient Asteroid§6.");
        phase1.add(WIZARDPREFIX + "§6Maybe you can try and §ckill§6 some of them and harvest their §aFragments§6.");
        phase1.add(WIZARDPREFIX + "§6I could do some §3experiments §6with it.");

    }

    public void fillStoryList3(){
        phase2.add(WIZARDPREFIX + "§6I made some §3experiments§6 with it.");
        phase2.add(WIZARDPREFIX + "§6And I could figure out there weakness is §e§lGold§6.");
        phase2.add(WIZARDPREFIX + "§6So maybe if you kill them with a §e§lGold Pickaxe§6 they may drop more §aFragments§6.");
        phase2.add(WIZARDPREFIX + "§dI hope so...");


    }

    public void fillStoryList4(){
        phase3.add(WIZARDPREFIX + "§6Okok I think I have created something §5§lEXTREMELEY COOL§6!");
        phase3.add(WIZARDPREFIX + "§6A §a§lPICKAXE");
        phase3.add(WIZARDPREFIX + "§7Wait you're not looking that excited. :(");
        phase3.add(WIZARDPREFIX + "§6Well...");
        phase3.add(WIZARDPREFIX + "§6It cool feature is that it has the §bEnchantment §5Smelting I");
        phase3.add(WIZARDPREFIX + "§4ISNT THAT GREAT!?");
        phase3.add(WIZARDPREFIX + "...");
        phase3.add(WIZARDPREFIX + "§6§oBut for that I need some other §cFragments§l so go get them ASAP!");
    }
    public void fillStoryList5(){
        phase4.add(WIZARDPREFIX + "§a§lPerfect!");
        phase4.add(WIZARDPREFIX + "§6I could figure out what we need to to!");
        phase4.add(WIZARDPREFIX + "§4Kill the Ender dragon AGAIN!");
        phase4.add(WIZARDPREFIX + "§6Be aware those §4§lEvil Spritis§6 will may buff the §5Ender Dragon§6. So be prepared...");
        phase4.add(WIZARDPREFIX + "§bOh you ask whats with §bme§6?");
        phase4.add(WIZARDPREFIX + "§6I will move out when where done with him.");
        phase4.add(WIZARDPREFIX + "§6After the §4§lEvil Spirits §6has been banned from the Overworld the §cAsteroid§6 should vanish.");
    }
}
