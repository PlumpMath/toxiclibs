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
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package toxi.color;

/**
 * A single histogram entry, a coupling of color & frequency. Implements a
 * comparator to sort histogram entries based on freq.
 */
public class HistEntry implements Comparable<HistEntry> {

    protected float freq;
    protected TColor col;

    HistEntry(TColor c) {
        col = c;
        freq = 1;
    }

    public int compareTo(HistEntry e) {
        return (int) (e.freq - freq);
    }

    public TColor getColor() {
        return col;
    }

    public float getFrequency() {
        return freq;
    }
}