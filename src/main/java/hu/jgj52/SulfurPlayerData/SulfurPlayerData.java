package hu.jgj52.SulfurPlayerData;

import com.quietterminal.cattlelog.CowFileManager;
import com.quietterminal.cattlelog.PlayerDataSerializer;
import hu.jgj52.Sulfur.Utils.Plugin;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;

import java.nio.file.Path;

public class SulfurPlayerData implements Plugin {
    public static CowFileManager fileManager;
    @Override
    public void onEnable() {
        fileManager = new CowFileManager(Path.of("playerdata"));

        try {
            fileManager.ensureBarnExists();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }

        new SaverListener();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                fileManager.save(
                        player.getUuid(),
                        PlayerDataSerializer.serialize(player)
                );
            }
        }));
    }

    @Override
    public void onDisable() {

    }
}
