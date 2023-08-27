package net.starly.custommenu.shiftf.service;

import net.starly.custommenu.configuration.GlobalPropertyManager;
import net.starly.custommenu.expansion.command.CommandExpansion;
import net.starly.custommenu.menu.Menu;
import net.starly.custommenu.menu.repo.MenuRepository;
import net.starly.custommenu.shiftf.CMShiftF;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SetDefaultExpansion extends CommandExpansion {

    @Override
    public String getLabelName() {
        return "빠른열기설정";
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
        if (args.length == 1) {
            sender.sendMessage("§c기본 메뉴로 지정할 ID를 입력해주세요.");
            return false;
        } else if (args.length != 2) {
            sender.sendMessage("§c잘못된 명령어입니다.");
            return false;
        }

        String menuId = args[1];

        MenuRepository menuRepository = MenuRepository.getInstance();
        Menu menu = menuRepository.getMenu(menuId);

        GlobalPropertyManager propertyManager = GlobalPropertyManager.getInstance();
        if (menu != null) {
            propertyManager.setProperty("CMSF_DEFAULT_MENU", menu.getId());
            sender.sendMessage("§a기본 메뉴로 메뉴 " + menu.getId() + "를 지정하였습니다.");
        } else {
            propertyManager.setProperty("CMSF_DEFAULT_MENU", "undefined");
            sender.sendMessage("§a기본 메뉴 설정을 삭제하였습니다.");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("<ID>");

            MenuRepository menuRepository = MenuRepository.getInstance();
            completions.addAll(menuRepository.getMenuIdList());
        }

        return StringUtil.copyPartialMatches(args[args.length - 1], completions, new ArrayList<>());
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.of("starly.custommenu.shiftf.set");
    }
}