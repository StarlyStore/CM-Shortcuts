package net.starly.custommenu.shiftf.service;

import net.starly.custommenu.configuration.GlobalPropertyManager;
import net.starly.custommenu.expansion.command.CommandExpansion;
import net.starly.custommenu.inventory.listener.MenuGUI;
import net.starly.custommenu.menu.Menu;
import net.starly.custommenu.menu.repo.MenuRepository;
import net.starly.custommenu.shiftf.CMShiftF;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OpenDefaultExpansion extends CommandExpansion {

    @Override
    public String getLabelName() {
        return "빠른열기";
    }

    @Override
    public JavaPlugin getProvider() {
        return CMShiftF.getInstance();
    }

    @Override
    public String getVersion() {
        return CMShiftF.getInstance().getDescription().getVersion();
    }

    @Override
    public String getAuthor() {
        return CMShiftF.getInstance().getDescription().getAuthors().toString();
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c콘솔에서 사용 불가능한 명령어입니다.");
            return false;
        }
        Player player = (Player) sender;

        if (args.length != 1) {
            sender.sendMessage("§c잘못된 명령어입니다.");
            return false;
        }

        GlobalPropertyManager propertyManager = GlobalPropertyManager.getInstance();
        String defaultMenuId = propertyManager.getProperty("CMSF_DEFAULT_MENU");
        if ("undefined".equals(defaultMenuId)) {
            sender.sendMessage("§c기본 메뉴가 지정되지 않았습니다.");
            return false;
        }

        MenuRepository menuRepository = MenuRepository.getInstance();
        Menu menu = menuRepository.getMenu(defaultMenuId);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        MenuGUI.getInstance().openInventory(player, menu);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.of("starly.custommenu.shiftf.open");
    }
}