package net.starly.custommenu.shiftf.listener;

import net.starly.custommenu.configuration.GlobalPropertyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapHandItemsListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSwap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()) return;

        GlobalPropertyManager propertyManager = GlobalPropertyManager.getInstance();
        String menuId = propertyManager.getProperty("CMSF_DEFAULT_MENU");
        if ("undefined".equals(menuId)) return;

        player.performCommand("/st-custommenu:custom-menu 빠른열기");
    }
}
