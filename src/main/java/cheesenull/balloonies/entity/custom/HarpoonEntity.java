package cheesenull.balloonies.entity.custom;

import cheesenull.balloonies.client.BallooniesDamageTypes;
import cheesenull.balloonies.entity.BallooniesEntities;
import cheesenull.balloonies.item.BallooniesItems;
import cheesenull.balloonies.sound.BallooniesSounds;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HarpoonEntity extends PersistentProjectileEntity {

    private int stringTicks;
    private LivingEntity stuckTo = null;

    private static final TrackedData<Boolean> ENCHANTED;
    private boolean dealtDamage;
    public int returnTimer;

    public HarpoonEntity(EntityType<? extends HarpoonEntity> entityType, World world) {
        super(entityType, world);
    }

    public HarpoonEntity(World world, LivingEntity owner, ItemStack stack) {
        super(BallooniesEntities.HARPOON, owner, world, stack, (ItemStack)null);
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
    }

    public HarpoonEntity(World world, double x, double y, double z, ItemStack stack) {
        super(BallooniesEntities.HARPOON, x, y, z, world, stack, stack);
        this.dataTracker.set(ENCHANTED, stack.hasGlint());
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ENCHANTED, false);
    }

    public void tick() {

        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();

        if ((this.dealtDamage || this.isNoClip()) && entity != null) {

            if (!this.isOwnerAlive()) {

                if (!this.getWorld().isClient && this.pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();

            } else {

                this.setNoClip(true);

                Vec3d vec3d = entity.getPos().subtract(this.getPos());

                if (this.getWorld().isClient) {
                    this.lastRenderY = this.getY();
                }

                double d = 1.0D;
                this.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));

                if (stuckTo != null) {

                    if (stuckTo.isAlive() && this.getOwner() != null) {

                        boolean inRange = stuckTo.getPos().distanceTo(this.getOwner().getPos()) <= 1.0D;

                        this.setPos(this.getX(), this.getY() + vec3d.y * 0.015, this.getZ());

                        stuckTo.setVelocity(this.getVelocity().multiply(0.95).add(vec3d.normalize().multiply(d)));

                        if (inRange) {
                            stuckTo.setVelocity(Vec3d.ZERO);
                            stuckTo = null;
                        }

                    }

                }

                if (this.returnTimer == 0) {
                    this.playSound(BallooniesSounds.ITEM_HARPOON_RETURN, 1.0F, 5.0F);
                }

                ++this.returnTimer;
            }

        }

        super.tick();

    }

    private boolean isOwnerAlive() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayerEntity) || !entity.isSpectator();
        } else {
            return false;
        }
    }

    public boolean isEnchanted() {
        return (Boolean)this.dataTracker.get(ENCHANTED);
    }

    @Nullable
    protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = 4.0F;
        DamageSource damageSource = this.getDamageSources().create(BallooniesDamageTypes.HARPOON, this);
        World var7 = this.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            f = EnchantmentHelper.getDamage(serverWorld, this.getWeaponStack(), entity, damageSource, f);
        }

        this.dealtDamage = true;
        if (entity.damage(damageSource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            var7 = this.getWorld();
            if (var7 instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld)var7;
                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource, this.getWeaponStack());
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity)entity;
                this.knockback(livingEntity, damageSource);
                this.onHit(livingEntity);
            }

        }

        this.playSound(BallooniesSounds.ITEM_HARPOON_HIT, 1.0F, 1.0F);

    }

    @Override
    protected void onHit(LivingEntity target) {
        this.stuckTo = target;
        super.onHit(target);
    }

    protected void onBlockHitEnchantmentEffects(ServerWorld world, BlockHitResult blockHitResult, ItemStack weaponStack) {
        Vec3d vec3d = blockHitResult.getBlockPos().clampToWithin(blockHitResult.getPos());
        Entity var6 = this.getOwner();
        LivingEntity var10002;
        if (var6 instanceof LivingEntity livingEntity) {
            var10002 = livingEntity;
        } else {
            var10002 = null;
        }

        EnchantmentHelper.onHitBlock(world, weaponStack, var10002, this, (EquipmentSlot)null, vec3d, world.getBlockState(blockHitResult.getBlockPos()), (item) -> {
            this.kill();
        });
    }

    public ItemStack getWeaponStack() {
        return this.getItemStack();
    }

    protected boolean tryPickup(PlayerEntity player) {
        return super.tryPickup(player)
                || (this.isNoClip()
                && this.isOwner(player)
                && player.getInventory().insertStack(this.asItemStack()));
    }

    protected ItemStack getDefaultItemStack() {
        return new ItemStack(BallooniesItems.HARPOON);
    }

    protected SoundEvent getHitSound() {
        return BallooniesSounds.ITEM_HARPOON_HIT_GROUND;
    }

    public void onPlayerCollision(PlayerEntity player) {
        if (this.isOwner(player) || this.getOwner() == null) {
            super.onPlayerCollision(player);
        }

    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.dealtDamage = nbt.getBoolean("DealtDamage");
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("DealtDamage", this.dealtDamage);
    }

    protected float getDragInWater() {
        return 0.99F;
    }

    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    public float getStringTicks() {
        return (float)this.stringTicks;
    }

    static {
        ENCHANTED = DataTracker.registerData(HarpoonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

}
