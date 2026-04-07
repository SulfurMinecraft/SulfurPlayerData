package hu.jgj52.SulfurPlayerData;

import com.quietterminal.cattlelog.Cattlelog;
import hu.jgj52.Sulfur.Utils.Plugin;

import java.nio.file.Path;

public class SulfurPlayerData implements Plugin {
    @Override
    public void onEnable() {
        Cattlelog.initialize(Path.of("playerdata"));
    }

    @Override
    public void onDisable() {

    }
}
