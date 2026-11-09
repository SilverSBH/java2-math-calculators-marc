package edu.kirkwood.controller.bailey;

import edu.kirkwood.model.bailey.AstroOEV;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AstroOEVCalculatorTest {
    // Create object to test.
    private AstroOEV astroOEV;

    // parseInt tests----------------------------------------------------------------------

    @Test
    @DisplayName("Returns a valid positive integer")
    void parseInt_PositiveInt(){
        // Arrange
        String s = "5";
        // Act
        int result = AstroOEVCalculator.parseInt(s);
        // Assert
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Returns zero")
    void parseInt_Zero() {
        // Arrange
        String s = "0";
        // Act
        int result = AstroOEVCalculator.parseInt(s);
        // Assert
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Returns a negative integer")
    void parseInt_NegativeInt() {
        String s = "-5";
        int result = AstroOEVCalculator.parseInt(s);
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("Returns a NumberFormatException for a decimal")
    void parseInt_DecimalNFX() {
        String s = "5.32";
        assertThrows(NumberFormatException.class, () ->
                AstroOEVCalculator.parseInt(s));
    }

    @Test
    @DisplayName("Returns a NumberFormatException for text")
    void parseInt_TextNFX() {
        String s = "text";
        assertThrows(NumberFormatException.class, () ->
                AstroOEVCalculator.parseInt(s));
    }
    // parseDouble tests----------------------------------------------------------------------

    @Test
    @DisplayName("Returns a valid decimal number")
    void parseDouble_Decimal() {
        String s = "5.25";
        double result = AstroOEVCalculator.parseDouble(s);
        assertEquals(5.25, result);
    }

    @Test
    @DisplayName("Returns a whole number as a double")
    void parseDouble_WholeNumber() {
        String s = "8";
        double result = AstroOEVCalculator.parseDouble(s);
        assertEquals(8.0, result);
    }

    @Test
    @DisplayName("Returns zero as a double")
    void parseDouble_Zero() {
        String s = "0";
        double result = AstroOEVCalculator.parseDouble(s);
        assertEquals(0.0, result);
    }

    @Test
    @DisplayName("Returns a negative double")
    void parseDouble_Negative() {
        String s = "-5.25";
        double result = AstroOEVCalculator.parseDouble(s);
        assertEquals(-5.25, result);
    }

    @Test
    @DisplayName("Returns a NumberFormatException for text")
    void parseDouble_TextNFX() {
        String s = "text";
        assertThrows(NumberFormatException.class, () ->
                AstroOEVCalculator.parseDouble(s));
    }
    // --------------------------------------------------------------------------------

    @Test
    @DisplayName("Returns a valid sub-exposure time")
    void setSubExposureTime_valid(){
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        double result = astroOEV.getSubExposureTime();
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Returns an error for an invalid sub-exposure time")
    void setSubExposureTime_Invalid() {
        double subExposureTime = -5;
        assertThrows(IllegalArgumentException.class, () ->
                new AstroOEV(subExposureTime, 7.1, 300, "apsc", 4));
    }

    @Test
    @DisplayName("Returns a valid frame count")
    void setFrameCount_Valid() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        double result = astroOEV.getFrameCount();
        assertEquals(300, result);
    }

    @Test
    @DisplayName("Returns an error for an invalid frame count")
    void setFrameCount_Invalid() {
        int frameCount = -300;
        assertThrows(IllegalArgumentException.class, () ->
                new AstroOEV(5, 7.1, frameCount, "apsc", 4));
    }

    @Test
    @DisplayName("Returns a valid F-stop")
    void setFStop_Valid() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        double result = astroOEV.getFStop();
        assertEquals(7.1, result);
    }

    @Test
    @DisplayName("Returns an error for an invalid F-stop")
    void setFStop_Invalid() {
        double fStop = -7.1;
        assertThrows(IllegalArgumentException.class, () ->
                new AstroOEV(5, fStop, 300, "apsc", 4));
    }

    @Test
    @DisplayName("Returns a valid sensor size")
    void setSensorSize_Valid() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        String result = astroOEV.getSensorSize();
        assertEquals("apsc", result);
    }

    @Test
    @DisplayName("Returns an error for an invalid sensor size")
    void setSensorSize_Invalid() {
        String sensorSize = "bigSensor";
        assertThrows(IllegalArgumentException.class, ()->
                new AstroOEV(5, 7.1, 300, sensorSize, 4));
    }

    @Test
    @DisplayName("Returns a valid Bortle value")
    void setBortle_Valid() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        int result = astroOEV.getBortle();
        assertEquals(4, result);
    }

    @Test
    @DisplayName("Returns an error for an invalid Bortle value")
    void setBortle_Invalid() {
        int bortle = 10;
        assertThrows(IllegalArgumentException.class, () ->
                new AstroOEV(5, 7.1, 300, "apsc", bortle));
    }
    // Calculation tests----------------------------------------------------------------------

    @Test
    @DisplayName("Returns the calculated integration time")
    void calcIntegrationTime_Success() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        double time = astroOEV.calcIntegrationTime();
        assertEquals(1500, time);
    }

    @Test
    @DisplayName("Returns the calculated overall exposure value")
    void calcOEV_Success() {
        AstroOEV astroOEV = new AstroOEV(5, 7.1, 300, "apsc", 4);
        double oev = astroOEV.calcOEV();
        assertEquals(751, oev);
    }
}