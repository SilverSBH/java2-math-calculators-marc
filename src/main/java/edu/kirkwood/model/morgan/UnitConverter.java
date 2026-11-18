package edu.kirkwood.model.morgan;

/**
 * Converts a distance measured in kilometers into the unit the user chose.
 * <p>
 * This covers the "Unit selected?" branch of the design flowchart: kilometers
 * pass through unchanged, and miles use the "Convert km to miles" step.
 * <p>
 * drafted with some AI assistance (Claude, Anthropic).
 */
public class UnitConverter {

    /** Number of miles in one kilometer. */
    private static final double KM_TO_MILES = 0.621371;

    /**
     * Creates a converter. The converter holds no state.
     */
    public UnitConverter() {
        // nothing to initialize
    }

    /**
     * Converts a distance in kilometers to the requested unit.
     *
     * @param km   the distance in kilometers; must be zero or greater and finite
     * @param unit the unit to convert to
     * @return {@code km} unchanged for {@link DistanceUnit#KILOMETERS}, or
     *         {@code km * 0.621371} for {@link DistanceUnit#MILES}
     * @throws IllegalArgumentException if {@code unit} is {@code null}, or
     *                                  {@code km} is negative, {@code NaN}, or infinite
     */
    public double convert(double km, DistanceUnit unit) {
        if(unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if(km <0)
        {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
        if(Double.isNaN(km) || Double.isInfinite(km))
        {
            throw new IllegalArgumentException("Distance cannot be NaN or infinite");
        }
        if(unit == DistanceUnit.KILOMETERS) {
            return km;
        }
        if(unit == DistanceUnit.MILES)
        {
            return km * KM_TO_MILES;
        }
           throw new IllegalArgumentException("Unsupported unit: " + unit);  
    }
}