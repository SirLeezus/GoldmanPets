package lee.code.pets.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import lee.code.pets.Pets;
import lee.code.pets.utils.MobUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KeyboardPacketListener extends PacketAdapter {
  public KeyboardPacketListener(Pets pets) {
    super(pets, PacketType.Play.Client.STEER_VEHICLE);
  }

  public void onPacketReceiving(PacketEvent e) {
    final Player player = e.getPlayer();
    final Entity vehicle = player.getVehicle();
    if (vehicle == null) return;
    final PacketContainer packet = e.getPacket();
    final float sideways = packet.getFloat().read(0);
    final float forward = packet.getFloat().read(1);
    final boolean jump = packet.getBooleans().read(0);
    final boolean unmount = packet.getBooleans().read(1);
    if (unmount) return;

    // Calculate the player's yaw and pitch
    final Location playerLocation = player.getLocation();
    final float yaw = playerLocation.getYaw();
    final float pitch = playerLocation.getPitch();

    //set rotation
    if (vehicle instanceof Phantom) {
      vehicle.setRotation(yaw, -pitch);
    } else {
      vehicle.setRotation(yaw, pitch);
    }

    // Calculate the direction of movement based on player input
    final double radians = Math.toRadians(yaw);
    final double x = -forward * Math.sin(radians) + sideways * Math.cos(radians);
    final double z = forward * Math.cos(radians) + sideways * Math.sin(radians);

    // Create a velocity vector
    final Vector velocity = (new Vector(x, 0.0D, z)).normalize().multiply(0.5D);
    velocity.setY(vehicle.getVelocity().getY());

    // finite floating-point value check
    if (!Double.isFinite(velocity.getX())) velocity.setX(0);
    if (!Double.isFinite(velocity.getZ())) velocity.setZ(0);

    // Simulate moving downward when the player looks down
    if (MobUtil.canSwim(vehicle)) {
      if (pitch > 40.0F) {
        velocity.setY(-0.4D); // Adjust the value for the desired downward speed
      } else if (pitch < -40.0F) {
        if (vehicle.isInWater()) velocity.setY(0.2D);
        else velocity.setY(-0.4D);
      } else {
        if (!vehicle.isInWater()) velocity.setY(-0.4D);
        else velocity.setY(0.0D);
      }
    }

    // Apply specific adjustments when in water
    if (vehicle.isInWater() && !MobUtil.canSwim(vehicle) && !vehicle.isOnGround()) velocity.setY(-0.08D);

    // Apply specific adjustments for flying mobs or other conditions if needed
    if (MobUtil.canFly(vehicle) && !vehicle.isOnGround()) velocity.setY(-0.08D);

    // Apply a jump boost if conditions are met
    if (jump && (MobUtil.canFly(vehicle) || (MobUtil.canSwim(vehicle) && vehicle.isInWater()) || vehicle.isOnGround())) velocity.setY(0.5D);

    // Check if each component of this Vector is finite
    velocity.checkFinite();
    vehicle.setVelocity(velocity);
  }
}
