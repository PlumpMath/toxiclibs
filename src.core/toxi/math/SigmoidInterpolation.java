/*
 * Copyright (c) 2006-2011 Karsten Schmidt
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * http://creativecommons.org/licenses/LGPL/2.1/
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301, USA
 */

package toxi.math;

/**
 * Implements the sigmoid interpolation function with adjustable curve sharpness
 */
public class SigmoidInterpolation implements InterpolateStrategy {

    private float sharpness;

    private float sharpPremult;

    /**
     * Initializes the s-curve with default sharpness = 2
     */
    public SigmoidInterpolation() {
        this(2f);
    }

    public SigmoidInterpolation(float s) {
        setSharpness(s);
    }

    public float getSharpness() {
        return sharpness;
    }

    public float interpolate(float a, float b, float f) {
        f = (f * 2 - 1) * sharpPremult;
        f = (float) (1.0f / (1.0f + Math.exp(-f)));
        return a + (b - a) * f;
    }

    private void setSharpness(float s) {
        sharpness = s;
        sharpPremult = 5 * s;
    }
}
