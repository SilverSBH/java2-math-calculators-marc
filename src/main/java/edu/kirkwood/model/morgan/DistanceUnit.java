package edu.kirkwood.model.morgan;

/**
 * Units in which a distance between two map points can be reported.
 * <p>
 * Kilometers are the base unit: {@link Coordinate#computeDistance(Coordinate, Coordinate)}
 * always returns kilometers, and {@link UnitConverter#convert(double, DistanceUnit)}
 * translates that value into the unit the user selected.
 * <p>
 * drafted with some AI assistance (Claude, Anthropic).
 */
public enum DistanceUnit {

    /** Kilometers (the base unit for all calculations). */
    KILOMETERS,

    /** Statute miles. */
    MILES
}
