package edu.kirkwood.model.morgan;

import java.util.Objects;

/**
 * A point on the Earth's surface, described by latitude and longitude in
 * decimal degrees.
 * <p>
 * The constructor deliberately accepts any values so that the application can
 * follow the "Validate coordinate ranges" step of the design flowchart: it
 * builds the coordinate from user input, then calls {@link #isValid()} and
 * re-prompts the user if the answer is {@code false}.
 * <p>
 * This class also holds the Haversine great-circle distance calculation
 * ({@link #computeDistance(Coordinate, Coordinate)}), as shown on the Week 1
 * class diagram.
 * <p>
 *  drafted with some AI assistance (Claude, Anthropic).
 */
public class Coordinate {

    /** Mean radius of the Earth in kilometers, used by the Haversine formula. */
    private static final double EARTH_RADIUS_KM = 6371.0;

    /** Latitude in decimal degrees. Valid range is -90 to 90 inclusive. */
    private final double latitude;

    /** Longitude in decimal degrees. Valid range is -180 to 180 inclusive. */
    private final double longitude;

    /**
     * Creates a coordinate. No range checking is done here; call
     * {@link #isValid()} to check the values.
     *
     * @param latitude  latitude in decimal degrees (north is positive)
     * @param longitude longitude in decimal degrees (east is positive)
     */
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude.
     *
     * @return latitude in decimal degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude.
     *
     * @return longitude in decimal degrees
     */
    public double getLongitude() {
        return longitude;
    }

    @Override 
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Coordinate other = (Coordinate) o;
        return latitude == other.latitude && longitude == other.longitude;
    }

    @Override 
    public int hashCode()
    {
        return Objects.hash(latitude, longitude);
    }

    /**
     * Checks whether this coordinate is a real location on Earth.
     * <p>
     * A coordinate is valid when latitude is between -90 and 90 inclusive,
     * longitude is between -180 and 180 inclusive, and neither value is
     * {@code NaN} or infinite.
     *
     * @return {@code true} if both values are within range, otherwise {@code false}
     */
    public boolean isValid() {
        return latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180;
    }

    /**
     * Computes the great-circle ("as the crow flies") distance between two
     * coordinates using the Haversine formula:
     * <pre>
     * a = sin²(Δlat / 2) + cos(lat1) · cos(lat2) · sin²(Δlon / 2)
     * c = 2 · atan2(√a, √(1 − a))
     * d = EARTH_RADIUS_KM · c
     * </pre>
     * The result is always in kilometers. Use
     * {@link UnitConverter#convert(double, DistanceUnit)} for other units.
     *
     * @param p1 the first point
     * @param p2 the second point
     * @return the distance between the points in kilometers (never negative)
     * @throws IllegalArgumentException if either point is {@code null} or not
     *                                  {@linkplain #isValid() valid}
     */
    public static double computeDistance(Coordinate p1, Coordinate p2) {
        if (p1 == null || p2 == null)
        {
            throw new IllegalArgumentException("Coordinates must not be null");
        }
        if (!p1.isValid() || !p2.isValid())
        {
            throw new IllegalArgumentException("Coordinates must be valid");
        }
        double lat1Rad = Math.toRadians(p1.getLatitude());
        double lat2Rad = Math.toRadians(p2.getLatitude());
        double deltaLatRad = Math.toRadians(p2.getLatitude() - p1.getLatitude());
        double deltaLonRad = Math.toRadians(p2.getLongitude() - p1.getLongitude());
        double a = Math.sin(deltaLatRad/2) * Math.sin(deltaLatRad/2) 
        + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.sin(deltaLonRad/2) 
        * Math.sin(deltaLonRad/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = EARTH_RADIUS_KM * c;
        return d;
    }
}