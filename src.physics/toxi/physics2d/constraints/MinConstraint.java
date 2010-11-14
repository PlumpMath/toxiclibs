package toxi.physics2d.constraints;

import toxi.geom.Vec2D.Axis;
import toxi.physics2d.VerletParticle2D;

public class MinConstraint implements ParticleConstraint2D {

    public Axis axis;
    public float threshold;

    public MinConstraint(Axis axis, float threshold) {
        this.axis = axis;
        this.threshold = threshold;
    }

    public void apply(VerletParticle2D p) {
        if (p.getComponent(axis) < threshold) {
            p.setComponent(axis, threshold);
        }
    }

}
