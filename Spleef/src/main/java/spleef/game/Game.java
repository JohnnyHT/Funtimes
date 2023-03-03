package spleef.game;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

public final class Game extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        Player player = (Player) e.getPlayer();

        if (e.getPlayer().getGameMode() == GameMode.ADVENTURE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void gotSpleefed(PlayerDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player killer = event.getEntity().getKiller();
            Player p = event.getEntity();

            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + p.getDisplayName() + ChatColor.GRAY + " got spleefed by " + ChatColor.RED + "" + ChatColor.BOLD + killer.getDisplayName());
        }
    }

    @EventHandler
    public void stopTrap(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || e.getClickedBlock().getType() == Material.ITEM_FRAME || e.getClickedBlock().getType() == Material.SPRUCE_TRAPDOOR || e.getClickedBlock().getType() == Material.ACACIA_TRAPDOOR || e.getClickedBlock().getType() == Material.BIRCH_TRAPDOOR || e.getClickedBlock().getType() == Material.CRIMSON_TRAPDOOR || e.getClickedBlock().getType() == Material.JUNGLE_TRAPDOOR || e.getClickedBlock().getType() == Material.OAK_TRAPDOOR || e.getClickedBlock().getType() == Material.DARK_OAK_TRAPDOOR || e.getClickedBlock().getType() == Material.MANGROVE_TRAPDOOR || e.getClickedBlock().getType() == Material.WARPED_TRAPDOOR)
                    e.setCancelled(true);
            }
        }
    }


    private boolean isSlimeBlock(World world, int x, int y, int z) {

        Location loc = new Location(world, x, y, z);
        if (loc.getBlock().getType() == Material.SLIME_BLOCK || loc.getBlock().getType() == Material.POWDER_SNOW || loc.getBlock().getType() == Material.WATER || loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.STRUCTURE_VOID || loc.getBlock().getType() == Material.BLUE_ICE) {
            return true;
        }
        return false;
    }

    private boolean isBlueIce(World world, int x, int y, int z) {

        Location loc = new Location(world, x, y, z);
        if (loc.getBlock().getType() == Material.BLUE_ICE || loc.getBlock().getType() == Material.WATER || loc.getBlock().getType() == Material.AIR || loc.getBlock().getType() == Material.STRUCTURE_VOID || loc.getBlock().getType() == Material.SLIME_BLOCK) {
            return true;
        }
        return false;
    }

    private void blueIce(Player player) {

        List<Block> blockLocations = new ArrayList<Block>();
        List<Material> blockMaterial = new ArrayList<Material>();
        Material material = player.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
        Location LOC = player.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation();
        Block block = LOC.getBlock();
        Vehicle vehicle = (Vehicle) player.getVehicle().getFacing().getDirection();

        int numb1 = 2;
        int XCord = player.getPlayer().getLocation().getBlockX();
        int ZCord = player.getPlayer().getLocation().getBlockZ();
        int YCord = player.getPlayer().getLocation().getBlockY();
        if(player.getFacing() == BlockFace.NORTH) {
             XCord = player.getPlayer().getLocation().getBlockX();
             ZCord = player.getPlayer().getLocation().getBlockZ() - numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.NORTH_EAST) {
             XCord = player.getPlayer().getLocation().getBlockX() + numb1;
             ZCord = player.getPlayer().getLocation().getBlockZ() - numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.EAST) {
             XCord = player.getPlayer().getLocation().getBlockX() + numb1;
             ZCord = player.getPlayer().getLocation().getBlockZ();
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.SOUTH_EAST) {
             XCord = player.getPlayer().getLocation().getBlockX() + numb1;
             ZCord = player.getPlayer().getLocation().getBlockZ() + numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.SOUTH) {
             XCord = player.getPlayer().getLocation().getBlockX() ;
             ZCord = player.getPlayer().getLocation().getBlockZ() + numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.SOUTH_WEST) {
             XCord = player.getPlayer().getLocation().getBlockX() - numb1 ;
             ZCord = player.getPlayer().getLocation().getBlockZ() + numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if (player.getFacing() == BlockFace.WEST) {
             XCord = player.getPlayer().getLocation().getBlockX() - numb1;
             ZCord = player.getPlayer().getLocation().getBlockZ();
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }
        if(player.getFacing() == BlockFace.NORTH_WEST){
             XCord = player.getPlayer().getLocation().getBlockX() - numb1 ;
             ZCord = player.getPlayer().getLocation().getBlockZ() - numb1;
             YCord = player.getPlayer().getLocation().getBlockY() - 1;
        }

        World world = player.getPlayer().getWorld();

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord, YCord, ZCord))) {
            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord));
            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord).getType());
            world.getBlockAt(XCord, YCord, ZCord).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 1, YCord, ZCord))) {
            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord));
            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord).getType());
            world.getBlockAt(XCord - 1, YCord, ZCord).setType(Material.BLUE_ICE);

        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 1, YCord, ZCord))) {
            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord));
            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord).getType());
            world.getBlockAt(XCord + 1, YCord, ZCord).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord, YCord, ZCord - 1))) {
            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord - 1));
            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord - 1).getType());
            world.getBlockAt(XCord, YCord, ZCord - 1).setType(Material.BLUE_ICE);

        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord, YCord, ZCord + 1))) {
            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord + 1));
            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord + 1).getType());
            world.getBlockAt(XCord, YCord, ZCord + 1).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 1, YCord, ZCord - 1))) {
            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord - 1));
            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord - 1).getType());
            world.getBlockAt(XCord - 1, YCord, ZCord - 1).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 1, YCord, ZCord + 1))) {
            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord + 1));
            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord + 1).getType());
            world.getBlockAt(XCord - 1, YCord, ZCord + 1).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 1, YCord, ZCord - 1))) {
            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord - 1));
            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord - 1).getType());
            world.getBlockAt(XCord + 1, YCord, ZCord - 1).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 1, YCord, ZCord + 1))) {
            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord + 1));
            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord + 1).getType());
            world.getBlockAt(XCord + 1, YCord, ZCord + 1).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord, YCord, ZCord + 2))) {
            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord + 2));
            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord + 2).getType());
            world.getBlockAt(XCord, YCord, ZCord + 2).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 2, YCord, ZCord))) {
            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord));
            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord).getType());
            world.getBlockAt(XCord + 2, YCord, ZCord).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 2, YCord, ZCord))) {
            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord));
            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord).getType());
            world.getBlockAt(XCord - 2, YCord, ZCord).setType(Material.BLUE_ICE);
        }

        if (!(isBlueIce(player.getPlayer().getWorld(), XCord, YCord, ZCord - 2))) {
            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord - 2));
            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord - 2).getType());
            world.getBlockAt(XCord, YCord, ZCord - 2).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 1, YCord, ZCord + 2))) {
            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord + 2));
            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord + 2).getType());
            world.getBlockAt(XCord - 1, YCord, ZCord + 2).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 1, YCord, ZCord + 2))) {
            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord + 2));
            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord + 2).getType());
            world.getBlockAt(XCord + 1, YCord, ZCord + 2).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 2, YCord, ZCord - 1))) {
            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord - 1));
            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord - 1).getType());
            world.getBlockAt(XCord + 2, YCord, ZCord - 1).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 2, YCord, ZCord + 1))) {
            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord + 1));
            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord + 1).getType());
            world.getBlockAt(XCord + 2, YCord, ZCord + 1).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 2, YCord, ZCord - 1))) {
            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord - 1));
            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord - 1).getType());
            world.getBlockAt(XCord - 2, YCord, ZCord - 1).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 2, YCord, ZCord + 1))) {
            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord + 1));
            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord + 1).getType());
            world.getBlockAt(XCord - 2, YCord, ZCord + 1).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord - 1, YCord, ZCord - 2))) {
            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord - 2));
            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord - 2).getType());
            world.getBlockAt(XCord - 1, YCord, ZCord - 2).setType(Material.BLUE_ICE);
        }
        if (!(isBlueIce(player.getPlayer().getWorld(), XCord + 1, YCord, ZCord - 2))) {
            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord - 2));
            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord - 2).getType());
            world.getBlockAt(XCord + 1, YCord, ZCord - 2).setType(Material.BLUE_ICE);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < blockLocations.size(); i++) {

                    world.getBlockAt(blockLocations.get(i).getLocation()).setType(blockMaterial.get(i));

                }
            }
        }.runTaskLater(this, 40);

    }


    @EventHandler
    public void snowBaller(ProjectileHitEvent e) {
        Material material = e.getHitBlock().getType();
        Projectile projectile = e.getEntity();

        if (projectile instanceof Snowball) {
            if (material == Material.ICE || material == Material.PACKED_ICE || material == Material.BLUE_ICE) {
                List<Block> blockLocations = new ArrayList<Block>();
                List<Material> blockMaterial = new ArrayList<Material>();
                Location LOC = e.getHitBlock().getLocation();
                Block block = (Block) LOC.getBlock();
                int XCord = e.getHitBlock().getX();
                int ZCord = e.getHitBlock().getZ();
                int YCord = e.getHitBlock().getY();
                World world = e.getEntity().getWorld();
                new BukkitRunnable() {
                    @Override
                    public void run() {


                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord, YCord, ZCord))) {
                            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord));
                            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord).getType());
                            world.getBlockAt(XCord, YCord, ZCord).setType(Material.SLIME_BLOCK);
                        }


                    }
                }.runTaskLater(this, 0);

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 1, YCord, ZCord))) {
                            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord));
                            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord).getType());
                            world.getBlockAt(XCord - 1, YCord, ZCord).setType(Material.SLIME_BLOCK);

                        }
                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 1, YCord, ZCord))) {
                            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord));
                            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord).getType());
                            world.getBlockAt(XCord + 1, YCord, ZCord).setType(Material.SLIME_BLOCK);

                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord, YCord, ZCord - 1))) {
                            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord - 1));
                            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord - 1).getType());
                            world.getBlockAt(XCord, YCord, ZCord - 1).setType(Material.SLIME_BLOCK);

                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord, YCord, ZCord + 1))) {
                            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord + 1));
                            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord + 1).getType());
                            world.getBlockAt(XCord, YCord, ZCord + 1).setType(Material.SLIME_BLOCK);
                        }


                    }
                }.runTaskLater(this, 5);

                new BukkitRunnable() {
                    @Override
                    public void run() {


                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 1, YCord, ZCord - 1))) {
                            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord - 1));
                            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord - 1).getType());
                            world.getBlockAt(XCord - 1, YCord, ZCord - 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 1, YCord, ZCord + 1))) {
                            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord + 1));
                            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord + 1).getType());
                            world.getBlockAt(XCord - 1, YCord, ZCord + 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 1, YCord, ZCord - 1))) {
                            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord - 1));
                            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord - 1).getType());
                            world.getBlockAt(XCord + 1, YCord, ZCord - 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 1, YCord, ZCord + 1))) {
                            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord + 1));
                            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord + 1).getType());
                            world.getBlockAt(XCord + 1, YCord, ZCord + 1).setType(Material.SLIME_BLOCK);
                        }
                    }

                }.runTaskLater(this, 10);
                new BukkitRunnable() {
                    @Override
                    public void run() {


                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord, YCord, ZCord + 2))) {
                            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord + 2));
                            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord + 2).getType());
                            world.getBlockAt(XCord, YCord, ZCord + 2).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 2, YCord, ZCord))) {
                            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord));
                            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord).getType());
                            world.getBlockAt(XCord + 2, YCord, ZCord).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 2, YCord, ZCord))) {
                            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord));
                            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord).getType());
                            world.getBlockAt(XCord - 2, YCord, ZCord).setType(Material.SLIME_BLOCK);
                        }

                        // Mishka is here
                        // Hello Here
                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord, YCord, ZCord - 2))) {
                            blockLocations.add(world.getBlockAt(XCord, YCord, ZCord - 2));
                            blockMaterial.add(world.getBlockAt(XCord, YCord, ZCord - 2).getType());
                            world.getBlockAt(XCord, YCord, ZCord - 2).setType(Material.SLIME_BLOCK);
                        }


                    }
                }.runTaskLater(this, 15);

                new BukkitRunnable() {
                    @Override
                    public void run() {

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 1, YCord, ZCord + 2))) {
                            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord + 2));
                            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord + 2).getType());
                            world.getBlockAt(XCord - 1, YCord, ZCord + 2).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 1, YCord, ZCord + 2))) {
                            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord + 2));
                            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord + 2).getType());
                            world.getBlockAt(XCord + 1, YCord, ZCord + 2).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 2, YCord, ZCord - 1))) {
                            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord - 1));
                            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord - 1).getType());
                            world.getBlockAt(XCord + 2, YCord, ZCord - 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 2, YCord, ZCord + 1))) {
                            blockLocations.add(world.getBlockAt(XCord + 2, YCord, ZCord + 1));
                            blockMaterial.add(world.getBlockAt(XCord + 2, YCord, ZCord + 1).getType());
                            world.getBlockAt(XCord + 2, YCord, ZCord + 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 2, YCord, ZCord - 1))) {
                            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord - 1));
                            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord - 1).getType());
                            world.getBlockAt(XCord - 2, YCord, ZCord - 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 2, YCord, ZCord + 1))) {
                            blockLocations.add(world.getBlockAt(XCord - 2, YCord, ZCord + 1));
                            blockMaterial.add(world.getBlockAt(XCord - 2, YCord, ZCord + 1).getType());
                            world.getBlockAt(XCord - 2, YCord, ZCord + 1).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord - 1, YCord, ZCord - 2))) {
                            blockLocations.add(world.getBlockAt(XCord - 1, YCord, ZCord - 2));
                            blockMaterial.add(world.getBlockAt(XCord - 1, YCord, ZCord - 2).getType());
                            world.getBlockAt(XCord - 1, YCord, ZCord - 2).setType(Material.SLIME_BLOCK);
                        }

                        if (!(isSlimeBlock(e.getHitBlock().getWorld(), XCord + 1, YCord, ZCord - 2))) {
                            blockLocations.add(world.getBlockAt(XCord + 1, YCord, ZCord - 2));
                            blockMaterial.add(world.getBlockAt(XCord + 1, YCord, ZCord - 2).getType());
                            world.getBlockAt(XCord + 1, YCord, ZCord - 2).setType(Material.SLIME_BLOCK);
                        }


                    }
                }.runTaskLater(this, 20);

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < blockLocations.size(); i++) {

                            world.getBlockAt(blockLocations.get(i).getLocation()).setType(blockMaterial.get(i));

                        }
                    }
                }.runTaskLater(this, 200);


            }
        }
    }

    @EventHandler
    public void playerBreakBlock(BlockBreakEvent e) {
        Material material = e.getBlock().getType();
        ItemStack itemStack = new ItemStack(Material.SNOWBALL);
        Player player = e.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {

            if (e.getBlock().getType() == Material.SNOW_BLOCK) {
                player.getInventory().addItem(itemStack);
            }
        }
    }

    @EventHandler
    public void playerOpenChest(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.getGameMode() == GameMode.ADVENTURE) {

            if (e.getInventory().getType().equals(InventoryType.CHEST)) {
                e.setCancelled(true);
            }


            if (e.getInventory().getType().equals(InventoryType.BARREL)) {
                e.setCancelled(true);
            }

            if (e.getInventory().getType().equals(InventoryType.DISPENSER)) {
                e.setCancelled(true);
            }

            if (e.getInventory().getType().equals(InventoryType.FURNACE)) {
                e.setCancelled(true);
            }

            if (e.getInventory().getType().equals(InventoryType.BLAST_FURNACE)) {
                e.setCancelled(true);
            }

            if (e.getInventory().getType().equals(InventoryType.SMOKER)) {
                e.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void noLeaveBoat(VehicleExitEvent event) {
        Player player = (Player) event.getExited();
        if (player.getGameMode() == GameMode.ADVENTURE) {
            if (event.getVehicle().getType() == EntityType.BOAT) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void jump(PlayerInteractEvent e) {


        Player player = e.getPlayer();
        if (e.getItem().getType() == Material.DIAMOND_HORSE_ARMOR) {
            for (int i = 0; i <= 200; i++)

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        blueIce(e.getPlayer());
                    }
                }.runTaskLater(this, i);

        }


        if (e.getItem().getType() == Material.IRON_HORSE_ARMOR) {
            if (e.getPlayer().isInsideVehicle()) {
                Vehicle vehicle = (Vehicle) player.getVehicle();
                Location playerLocation = player.getLocation();
                @NotNull Vector vehicleDirection;
                @NotNull Vector playerDirection = playerLocation.getDirection();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Vehicle vehicle = (Vehicle) player.getVehicle();
                        Location playerLocation = player.getLocation();
                        @NotNull Vector vehicleDirection;
                        @NotNull Vector playerDirection = playerLocation.getDirection();
                        playerDirection.setY(0);
                        playerDirection.setX(0);
                        playerDirection.setZ(0);
                        vehicle.setVelocity(playerDirection.setY(0));
                    }
                }.runTaskLater(this, 0);
            }
        }

        if (e.getItem().getType() == Material.FEATHER) {

            if (e.getPlayer().isInsideVehicle()) {
                for (int i = 0; i <= 100; i++) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            helper(e.getPlayer());
                        }
                    }.runTaskLater(this, i);
            }
                new BukkitRunnable() {
                    @Override
                    public void run() {

                        Vehicle vehicle = (Vehicle) player.getVehicle();
                        Vehicle vehicle1 = (Vehicle) player.getVehicle();
                        Location playerLocation = player.getLocation();
                        @NotNull Vector vehicleDirection;
                        @NotNull Vector playerDirection = playerLocation.getDirection();
                        playerDirection.multiply(1).normalize();
                        playerDirection.setY(0).normalize();
                        vehicle.setVelocity(playerDirection.setY(0));
                        System.out.println(playerDirection + "    §6" + playerDirection.length());
                    }
                }.runTaskLater(this, 101);

        }
    }
}


    private void helper(Player player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                Vehicle vehicle = (Vehicle) player.getVehicle();
                Location playerLocation = player.getLocation();
                @NotNull Vector vehicleDirection;
                @NotNull Vector playerDirection = playerLocation.getDirection();
                playerDirection.multiply(4).normalize();
                playerDirection.setY(0).normalize();
                vehicle.setVelocity(playerDirection.setY(0));
                System.out.println(playerDirection + "    §6" + playerDirection.length());
            }
        }.runTaskLater(this, 10);
    }
}