import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class SphereListener implements Listener {
    private final EventManager eventManager;
    private final SphereUtils sphereUtils;

    public SphereListener(EventManager eventManager, SphereUtils sphereUtils) {
        this.eventManager = eventManager;
        this.sphereUtils = sphereUtils;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock() == null) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (eventManager.canStartEvent(player)) {
            if (item.getType() == Material.PLAYER_HEAD) {
                sphereUtils.openGiftBox(player, item);
                eventManager.startEvent(player);
                player.sendMessage("Event started! You have 5 minutes to collect gifts.");
            }
        } else {
            player.sendMessage("You are in cooldown. Please wait before starting another event.");
        }
    }
}
