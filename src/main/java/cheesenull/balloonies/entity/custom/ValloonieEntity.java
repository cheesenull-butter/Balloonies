package cheesenull.balloonies.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
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

public class ValloonieEntity extends FlyingEntity {

    public ValloonieEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new ValloonieMoveControl(this);
    }

    public static DefaultAttributeContainer.Builder createValloonieAttributes() {
        return MobEntity.createMobAttributes()

                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1000.0F)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 100.0F);

    }

    @Override
    protected void initGoals() {

        this.goalSelector.add(1, new ValloonieChasePlayerGoal(this));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 100.0F));

    }

    private static class ValloonieChasePlayerGoal extends Goal {

        private final ValloonieEntity valloonie;

        public ValloonieChasePlayerGoal(ValloonieEntity valloonie) {

            this.valloonie = valloonie;
            this.setControls(EnumSet.of(Control.MOVE));

        }

        public boolean canStart() {

            MoveControl moveControl = this.valloonie.getMoveControl();

            if (!moveControl.isMoving()) {

                return true;

            } else {

                double d = moveControl.getTargetX() - this.valloonie.getX();
                double e = moveControl.getTargetY() - this.valloonie.getY();
                double f = moveControl.getTargetZ() - this.valloonie.getZ();

                double g = d * d + e * e + f * f;
                return g < 1.0F || g > 3600.0F;

            }
        }

        public boolean shouldContinue() {
            return false;
        }

        public void start() {

            PlayerEntity nearestPlayer = this.valloonie.getWorld().getClosestPlayer(this.valloonie, 100.0F);

            if (nearestPlayer != null) {

                double d = nearestPlayer.getX();
                double e = nearestPlayer.getY() + 1.5F;
                double f = nearestPlayer.getZ();

                double bobbingY = Math.sin(this.valloonie.age * 0.05F) * 0.01F;
                this.valloonie.getMoveControl().moveTo(d, e + bobbingY, f, 10.0F);

            }

        }
    }

    static class ValloonieMoveControl extends MoveControl {

        private final ValloonieEntity valloonie;
        private int collisionCheckCooldown;

        public ValloonieMoveControl(ValloonieEntity valloonie) {
            super(valloonie);
            this.valloonie = valloonie;
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                if (this.collisionCheckCooldown-- <= 0) {
                    this.collisionCheckCooldown += this.valloonie.getRandom().nextInt(5) + 2;
                    Vec3d vec3d = new Vec3d(this.targetX - this.valloonie.getX(), this.targetY - this.valloonie.getY(), this.targetZ - this.valloonie.getZ());
                    double d = vec3d.length();
                    vec3d = vec3d.normalize();
                    if (this.willCollide(vec3d, MathHelper.ceil(d))) {
                        this.valloonie.setVelocity(this.valloonie.getVelocity().add(vec3d.multiply(0.5)));
                    } else {
                        this.state = State.WAIT;
                    }
                }

            }
        }

        private boolean willCollide(Vec3d direction, int steps) {
            Box box = this.valloonie.getBoundingBox();

            for(int i = 1; i < steps; ++i) {
                box = box.offset(direction);
                if (!this.valloonie.getWorld().isSpaceEmpty(this.valloonie, box)) {
                    return false;
                }
            }

            return true;
        }
    }

}
