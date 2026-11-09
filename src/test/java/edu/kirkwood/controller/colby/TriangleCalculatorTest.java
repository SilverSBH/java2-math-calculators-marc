package edu.kirkwood.controller.colby;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleCalculatorTest {

    @Test
    @DisplayName("Throws an exception when fewer than 3 inputs are given")
    void solveTriangleWithNotEnoughInputs(){
        // Arrange
        double sideA = 5.00;
        double sideB = 5.00;
        double sideC = -Double.MAX_VALUE;
        double angleA = -Double.MAX_VALUE;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "You must enter at least three values.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when only angles are given")
    void solveTriangleWithOnlyAngles(){
        // Arrange
        double sideA = -Double.MAX_VALUE;
        double sideB = -Double.MAX_VALUE;
        double sideC = -Double.MAX_VALUE;
        double angleA = 60.00;
        double angleB = 60.00;
        double angleC = 60.00;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "You must provide at least 1 side length.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when the three side provided can't form a valid triangle")
    void solveTriangleWithInvalidSides(){
        // Arrange
        double sideA = 5.00;
        double sideB = 5.00;
        double sideC = 11.00;
        double angleA = -Double.MAX_VALUE;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "The provided sides do not form a valid triangle. " +
                "The sum of two sides must be greater than the third side.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when given a given angle exceeds 180 degrees")
    void solveTriangleWithInvalidAngleInput(){
        // Arrange
        double sideA = 5.00;
        double sideB = 5.00;
        double sideC = -Double.MAX_VALUE;
        double angleA = 190.00;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "Angle A must be less than 180 degrees.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when given angles sum exceed 180 degrees")
    void solveTriangleWithInvalidAngleInputSum(){
        // Arrange
        double sideA = 5.00;
        double sideB = -Double.MAX_VALUE;
        double sideC = -Double.MAX_VALUE;
        double angleA = 80.00;
        double angleB = 110.00;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "The provided angle(s) and their sum must be less than 180 degrees.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when more than 3 inputs are provided")
    void solveTriangleWithTooManyInputs(){
        // Arrange
        double sideA = 5.00;
        double sideB = 5.00;
        double sideC = 5.00;
        double angleA = 60.00;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "You entered too many values. Please enter exactly three values (including at least one side).";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when a side length is less than zero")
    void solveTriangleWithNegativeSideLength(){
        // Arrange
        double sideA = -5.00;
        double sideB = 5.00;
        double sideC = 5.00;
        double angleA = -Double.MAX_VALUE;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "Side a must be greater than zero.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Throws an exception when a side length is equal to zero")
    void solveTriangleWithZeroSideLength(){
        // Arrange
        double sideA = 5.00;
        double sideB = 5.00;
        double sideC = 0.00;
        double angleA = -Double.MAX_VALUE;
        double angleB = -Double.MAX_VALUE;
        double angleC = -Double.MAX_VALUE;

        // Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleCalculator.solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
        });

        String expectedMessage = "Side c must be greater than zero.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}