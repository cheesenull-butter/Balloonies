package cheesenull.balloonies.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

public class BallooningEntity extends FlyingEntity {

    public BallooningEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new BallooningMoveControl(this);
    }

    public static DefaultAttributeContainer.Builder createBallooningAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0F);
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {

        super.onPlayerCollision(player);

        discard();
//        int i = 0 /0;

    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new BallooningTargetPlayerGoal(this));
    }

    private static class BallooningTargetPlayerGoal extends Goal {

        private final BallooningEntity ballooning;

        public BallooningTargetPlayerGoal(BallooningEntity ballooning) {

            this.ballooning = ballooning;
            this.setControls(EnumSet.of(Control.MOVE));

        }

        public boolean canStart() {

            MoveControl moveControl = this.ballooning.getMoveControl();

            if (!moveControl.isMoving()) {

                return true;

            } else {

                double d = moveControl.getTargetX() - this.ballooning.getX();
                double e = moveControl.getTargetY() - this.ballooning.getY();
                double f = moveControl.getTargetZ() - this.ballooning.getZ();

                double g = d * d + e * e + f * f;
                return g < 1.0F || g > 3600.0F;

            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {

            PlayerEntity nearestPlayer = this.ballooning.getWorld().getClosestPlayer(this.ballooning, 100.0F);

            if (nearestPlayer != null) {

                double d = nearestPlayer.getX();
                double e = nearestPlayer.getY() + 1.0F;
                double f = nearestPlayer.getZ();

                this.ballooning.getMoveControl().moveTo(d, e, f, 5.0F);

            }

        }
    }

    static class BallooningMoveControl extends MoveControl {

        private final BallooningEntity ballooning;
        private int collisionCheckCooldown;

        public BallooningMoveControl(BallooningEntity ballooning) {
            super(ballooning);
            this.ballooning = ballooning;
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.ballooning.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.ballooning.getX(), this.targetY - this.ballooning.getY(), this.targetZ - this.ballooning.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.ballooning.setVelocity(this.ballooning.getVelocity().add(vec3d.multiply(0.5)));
                    } else {
                        this.state = State.WAIT;
                    }
                }

            }
        }

        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.ballooning.getBoundingBox();

            for(int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (!this.ballooning.getWorld().isSpaceEmpty(this.ballooning, box)) {
                    return false;
                }
            }

            return true;
        }
    }

}