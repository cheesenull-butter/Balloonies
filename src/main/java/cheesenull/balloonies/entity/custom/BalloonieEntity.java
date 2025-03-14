package cheesenull.balloonies.entity.custom;

import cheesenull.balloonies.block.BallooniesBlocks;
import cheesenull.balloonies.entity.BallooniesEntities;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.Random;

public class BalloonieEntity extends FlyingEntity {

    Random ran = new Random();
    int pool = ran.nextInt(0, 10);

    public BalloonieEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BalloonieEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public static DefaultAttributeContainer.Builder createBalloonieAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyStraightlyGoal(this));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {

        if (getTypeVariant() == 2) {

            if (pool < 8) {

                discard();

                for (int i = 0; i < 5; i++) {

                    BallooningEntity ballooning =
                            new BallooningEntity(BallooniesEntities.BALLOONING, getWorld());
                    ballooning.refreshPositionAndAngles(
                            getPos().getX(), getPos().getY(), getPos().getZ(),
                            0, 0);
                    getWorld().spawnEntity(ballooning);

                    double velocityX = (getWorld().random.nextDouble() - 0.5) * 2;
                    double velocityY = getWorld().random.nextDouble() * 0.5 + 0.5;
                    double velocityZ = (getWorld().random.nextDouble() - 0.5) * 2;
                    ballooning.setVelocity(velocityX, velocityY, velocityZ);

                }


            } else {

                ItemStack itemStack = new ItemStack(BallooniesBlocks.BLUE_ROSE.asItem());
                getWorld().spawnEntity(new ItemEntity(getWorld(),
                        getPos().getX(), getPos().getY(), getPos().getZ(), itemStack));

                discard();

            }

        } else {
            discard();
        }

        return true;

    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);

    }

    public BalloonieVariant getVariant() {
        return BalloonieVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(BalloonieVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {

        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getTypeVariant());

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {

        super.readCustomDataFromNbt(nbt);
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, nbt.getInt("Variant"));

    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                 @Nullable EntityData entityData) {

        BalloonieVariant variant = Util.getRandom(BalloonieVariant.values(), this.random);
        setVariant(variant);

        return super.initialize(world, difficulty, spawnReason, entityData);

    }

    private static class FlyStraightlyGoal extends Goal {

        private final BalloonieEntity balloonie;

        public FlyStraightlyGoal(BalloonieEntity balloonie) {

            this.balloonie = balloonie;
            this.setControls(EnumSet.of(Control.MOVE));

        }

        public boolean canStart() {

            MoveControl moveControl = this.balloonie.getMoveControl();

            if (!moveControl.isMoving()) {

                return true;

            } else {

                double d = moveControl.getTargetX() - this.balloonie.getX();
                double e = moveControl.getTargetY() - this.balloonie.getY();
                double f = moveControl.getTargetZ() - this.balloonie.getZ();

                double g = d * d + e * e + f * f;
                return g < 1.0F || g > 3600.0F;

            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {

            double bobbingY = Math.sin(this.balloonie.age * 0.05F) * 0.01F;
            this.balloonie.setVelocity(0.0F, bobbingY, 0.05F);

        }

    }

}