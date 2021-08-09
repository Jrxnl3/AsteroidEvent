package de.jinx.smpevents;

import de.jinx.smpevents.Config.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class ScoreboardHandler {

    public static void createScoreboard(Player p) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = scoreboard.registerNewObjective("test", "dummy", "§2SM§aPain§6");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore("").setScore(8);
        obj.getScore("§6§lPlayer:").setScore(7);
        obj.getScore("§a" + p.getName()).setScore(6);
        obj.getScore(" ").setScore(5);
        obj.getScore("§6§lObjective").setScore(4);
        Team goal = scoreboard.registerNewTeam("goal");
        ConfigManager cfM = SMPEvents.getPlugin().cfM;
        cfM.reload();

        FileConfiguration cfg =cfM.getProgressCfg();

        int stage = cfg.getInt("Event.stage");

        if(stage == 1){
            goal.setSuffix("§4§lAstroiders §6§l(§c" + cfg.getInt("Event.progress") + "§6§l/§a" + cfg.getInt("Stage" + stage + ".maxProgress") + "§6§l)");


        }else if(stage >= 5){
            goal.setSuffix("§5§lKill the Ender Dragon");
        }else
            goal.setSuffix("§aFragments §6§l(§c" + cfg.getInt("Event.progress") + "§6§l/§a" + cfg.getInt("Stage" + stage + ".maxProgress") + "§6§l)");

        goal.addEntry(ChatColor.BLACK.toString());

        obj.getScore(ChatColor.BLACK.toString()).setScore(3);

        obj.getScore("  ").setScore(2);
        obj.getScore("§d§lDiscord:").setScore(1);
        obj.getScore("§adiscord.gg/wUUKbk7eaz").setScore(0);

        p.setScoreboard(scoreboard);
    }

    public static void updateScoreboard() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = all.getScoreboard();

            Team goal = scoreboard.getTeam("goal");

            File progressFile = new File(SMPEvents.getPlugin().getDataFolder() + File.separator + "progress.yml");
            FileConfiguration progressCfg = YamlConfiguration.loadConfiguration(progressFile);

            int stage = progressCfg.getInt("Event.stage");

            if(stage == 1){
                goal.setSuffix("§4§lAstroiders §6§l(§c" + progressCfg.getInt("Event.progress") + "§6§l/§a" + progressCfg.getInt("Stage" + stage + ".maxProgress") + "§6§l)");


            }else if(stage >= 5){
                goal.setSuffix("§5§lKill the Ender Dragon");
            }else
                goal.setSuffix("§aFragments §6§l(§c" + progressCfg.getInt("Event.progress") + "§6§l/§a" + progressCfg.getInt("Stage" + stage + ".maxProgress") + "§6§l)");


        }
    }
}
