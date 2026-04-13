package hu.jgj52.SulfurPlayerData;

import com.quietterminal.cattlelog.PlayerDataDeserializer;
import com.quietterminal.cattlelog.PlayerDataSerializer;
import dev.sulfurmc.Sulfur.Utils.Listeners.Event;
import dev.sulfurmc.Sulfur.Utils.Listeners.Listener;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerSpawnEvent;

public class SaverListener extends Listener {
    @Event(priority = 10)
    public void onJoin(PlayerSpawnEvent event) {
        if (!event.isFirstSpawn()) return;

        SulfurPlayerData.fileManager.load(event.getPlayer().getUuid()).ifPresent(cow ->
            PlayerDataDeserializer.deserialize(event.getPlayer(), cow)
        );
    }

    @Event
    public void onLeave(PlayerDisconnectEvent event) {
        SulfurPlayerData.fileManager.save(
                event.getPlayer().getUuid(),
                PlayerDataSerializer.serialize(event.getPlayer())
        );
    }
}
