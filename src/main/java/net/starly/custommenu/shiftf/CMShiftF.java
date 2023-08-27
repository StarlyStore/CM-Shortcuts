package net.starly.custommenu.shiftf;

import lombok.Getter;
import net.starly.custommenu.configuration.GlobalPropertyManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CMShiftF extends JavaPlugin {

    @Getter private static CMShiftF instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        /* DEPENDENCY
         ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
        if (!isPluginEnabled("ST-Core")) {
            getServer().getLogger().warning("[" + getName() + "] ST-Core 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            getServer().getLogger().warning("[" + getName() + "] 다운로드 링크 : §fhttps://starly.kr/");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else if (!isPluginEnabled("ST-CustomMenu")) {
            getServer().getLogger().warning("[" + getName() + "] ST-CustomMenu 플러그인이 적용되지 않았습니다! 플러그인을 비활성화합니다.");
            getServer().getLogger().warning("[" + getName() + "] 다운로드 링크 : §fhttps://starly.kr/");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        /* CONFIG
         ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
        GlobalPropertyManager propertyManager = GlobalPropertyManager.getInstance();
        if (propertyManager.getProperty("CMSF_DEFAULT_MENU") == null) {
            propertyManager.setProperty("CMSF_DEFAULT_MENU", "undefined");
        }

        /* LISTENER
         ──────────────────────────────────────────────────────────────────────────────────────────────────────────────── */
    }

    private boolean isPluginEnabled(String name) {
        Plugin plugin = getServer().getPluginManager().getPlugin(name);
        return plugin != null && plugin.isEnabled();
    }
}
