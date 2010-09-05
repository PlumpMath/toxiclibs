package toxi.volume;

import java.util.logging.Level;

import toxi.math.MathUtils;

public class RoundBrush extends VolumetricBrush {

    protected float radius, radSquared;

    /**
     * Creates a new spherical brush to work on the given volume.
     * 
     * @param volume
     *            VolumetricSpaceArray instance
     * @param radius
     *            radius in world units
     */
    public RoundBrush(VolumetricSpace volume, float radius) {
        super(volume);
        setSize(radius);
    }

    @Override
    public void drawAtGridPos(float cx, float cy, float cz, float density) {
        int minX = MathUtils.max((int) (cx - cellRadiusX), 0);
        int minY = MathUtils.max((int) (cy - cellRadiusY), 0);
        int minZ = MathUtils.max((int) (cz - cellRadiusZ), 0);
        int maxX = MathUtils.min((int) (cx + cellRadiusX), volume.resX);
        int maxY = MathUtils.min((int) (cy + cellRadiusY), volume.resY);
        int maxZ = MathUtils.min((int) (cz + cellRadiusZ), volume.resZ);
        for (int z = minZ; z < maxZ; z++) {
            float dz = (z - cz) * stretchZ;
            dz *= dz;
            for (int y = minY; y < maxY; y++) {
                float dyz = (y - cy) * stretchY;
                dyz = dyz * dyz + dz;
                for (int x = minX; x < maxX; x++) {
                    float dx = x - cx;
                    float d = (float) Math.sqrt(dx * dx + dyz);
                    if (d <= cellRadiusX) {
                        float cellVal = (1 - d / cellRadiusX) * density;
                        updateVoxel(x, y, z, cellVal);
                    }
                }
            }
        }
    }

    @Override
    public void setSize(float radius) {
        this.radius = radius;
        this.cellRadiusX = (int) (radius / volume.scale.x * volume.resX + 1);
        this.cellRadiusY = (int) (radius / volume.scale.y * volume.resY + 1);
        this.cellRadiusZ = (int) (radius / volume.scale.z * volume.resZ + 1);
        stretchY = (float) cellRadiusX / cellRadiusY;
        stretchZ = (float) cellRadiusX / cellRadiusZ;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine("new brush size: " + radius);
        }
    }
}
