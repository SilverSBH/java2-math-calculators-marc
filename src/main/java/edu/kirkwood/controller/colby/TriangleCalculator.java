package edu.kirkwood.controller.colby;

import edu.kirkwood.model.colby.Triangle;

import java.util.ArrayList;
import java.util.List;

import static edu.kirkwood.view.Messages.triangleGoodbye;
import static edu.kirkwood.view.Messages.triangleGreet;
import static edu.kirkwood.view.UIUtility.displayError;
import static edu.kirkwood.view.UIUtility.pressEnterToContinue;
import static edu.kirkwood.view.UserInput.getDouble;

public class TriangleCalculator {

    public static void start() {
        triangleGreet();

        while(true) {
            // get inputs
            double sideA = getDouble("\nEnter the length of side a (press [Enter] if unknown or type '-1' to quit)", false);
            if(sideA == -1){
                break;
            }
            double sideB = getDouble("Enter the length of side b (press [Enter] if unknown or type '-1' to quit)", false);
            if(sideB == -1){
                break;
            }
            double sideC = getDouble("Enter the length side c (press [Enter] if unknown or type '-1' to quit)", false);
            if(sideC == -1){
                break;
            }
            double angleA = getDouble("Enter angle A in degrees (press [Enter] if unknown or type '-1' to quit)", false);
            if(angleA == -1){
                break;
            }
            double angleB = getDouble("Enter angle B in degrees (press [Enter] if unknown or type '-1' to quit)", false);
            if(angleB == -1){
                break;
            }
            double angleC = getDouble("Enter angle C in degrees (press [Enter] if unknown or type '-1' to quit)", false);
            if(angleC == -1){
                break;
            }

            // pass inputs to solveTriangle()
            try {
                List<Triangle> results = solveTriangle(sideA, sideB, sideC, angleA, angleB, angleC);
                // check for empty list of triangles
                if (results.isEmpty()) {
                    displayError("No valid triangle could be formed with the given inputs.");
                } else if(results.size() == 1){
                    for(Triangle triangle : results) {
                        System.out.println("\nCalculated Triangle:");
                        System.out.println(triangle.toString());
                        // print perimeter
                        System.out.printf("Perimeter: %.2f%n", results.getFirst().calculatePerimeter());
                        // print area
                        System.out.printf("Area: %.2f%n", results.getFirst().calculateArea());
                    }
                } else { // check for ambiguous case of two triangles
                    System.out.println("\nAmbiguous SSA Case - 2 Triangles Found");
                    int triangleNumber = 1;
                    for (Triangle triangle : results) {
                        System.out.println("\nCalculated Triangle " + triangleNumber + ":");
                        System.out.println(triangle.toString());
                        // print perimeter
                        System.out.printf("Perimeter: %.2f%n", triangle.calculatePerimeter());
                        // print area
                        System.out.printf("Area: %.2f%n", triangle.calculateArea());
                        triangleNumber ++;
                    }
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                displayError(e.getMessage());
            }
        }

        triangleGoodbye();
        pressEnterToContinue();
    }

    /**
     * Determines what triangle solver to use (e.g. solveSSS()) based the user inputs
     * and calls that method to determine the missing values
     *
     * @param sideA Side a of the triangle
     * @param sideB Side b of the triangle
     * @param sideC Side c of the triangle
     * @param angleA Angle A of the triangle
     * @param angleB Angle B of the triangle
     * @param angleC Angle C of the triangle
     *
     * @return A List of the created Triangle(s)
     */
    public static List<Triangle> solveTriangle(
            double sideA, double sideB, double sideC, double angleA, double angleB, double angleC){

        Triangle triangle = new Triangle();
        /*
         Check count of side and angle inputs
         getDouble() sets value to -Double.MAX_VALUE when the user doesn't enter a value as long as the value
         isn't required.
         */
        boolean hasSideA = sideA > -Double.MAX_VALUE;
        boolean hasSideB = sideB > -Double.MAX_VALUE;
        boolean hasSideC = sideC > -Double.MAX_VALUE;
        boolean hasAngleA = angleA > -Double.MAX_VALUE;
        boolean hasAngleB = angleB > -Double.MAX_VALUE;
        boolean hasAngleC = angleC > -Double.MAX_VALUE;
        /*
            Validate sides and angles to throw setter error messages if user input is out of bounds
            Set count of sides and angles
         */
        int sideCount = 0;
        int angleCount = 0;

        if(hasSideA){
            triangle.setSideA(sideA);
            sideCount++;
        }
        if(hasSideB){
            triangle.setSideB(sideB);
            sideCount++;
        }
        if(hasSideC){
            triangle.setSideC(sideC);
            sideCount++;
        }

        if(hasAngleA){
            triangle.setAngleA(angleA);
            angleCount++;
        }
        if(hasAngleB){
            triangle.setAngleB(angleB);
            angleCount++;
        }
        if(hasAngleC){
            triangle.setAngleC(angleC);
            angleCount++;
        }

        // Check there are at least 3 inputs
        if (sideCount + angleCount < 3){
            throw new IllegalArgumentException("You must enter at least three values.");
        }
        // Check the user doesn't enter more than 3 values
        if(sideCount + angleCount > 3){
            throw new IllegalArgumentException("You entered too many values. Please enter exactly three values (including at least one side).");
        }
        // Check for at least 1 side input
        if(sideCount <= 0){
            throw new IllegalArgumentException("You must provide at least 1 side length.");
        }
        // Check for SSS (3 sides)
        if(sideCount == 3){
            return List.of(triangle.solveSSS(sideA, sideB, sideC));
        }
        // Check SAS or SSA
        if(sideCount == 2 && angleCount >=1){
            // SAS
            if(hasSideA && hasSideB && hasAngleC){
                return List.of(triangle.solveSAS(sideA, sideB, angleC));
            }
            if(hasSideB && hasSideC && hasAngleA){
                triangle = triangle.solveSAS(sideB, sideC, angleA);
                // rearrange sides and angles to match user input
                return List.of(new Triangle(
                        triangle.getSideC(), triangle.getSideA(), triangle.getSideB(),
                        triangle.getAngleC(), triangle.getAngleA(), triangle.getAngleB()));
            }
            if(hasSideA && hasSideC && hasAngleB){
                triangle = triangle.solveSAS(sideA, sideC, angleB);
                return List.of(new Triangle(
                        triangle.getSideA(), triangle.getSideC(), triangle.getSideB(),
                        triangle.getAngleA(), triangle.getAngleC(), triangle.getAngleB()));
            }
            // SSA
            if(hasSideA && hasSideB && hasAngleA){
                return triangle.solveSSA(sideA, sideB, angleA);
            }
            if(hasSideA && hasSideB && hasAngleB){
                List<Triangle> triangles = triangle.solveSSA(sideB, sideA, angleB);
                List<Triangle> rearrangedTriangle = new ArrayList<>();
                for(Triangle t : triangles){
                    rearrangedTriangle.add(new Triangle(
                            t.getSideB(), t.getSideA(), t.getSideC(),
                            t. getAngleB(), t.getAngleA(), t.getAngleC()
                    ));
                }
                return rearrangedTriangle;
            }
            if (hasSideB && hasSideC && hasAngleB){
                List<Triangle> triangles = triangle.solveSSA(sideB, sideC, angleB);
                List<Triangle> rearrangedTriangle = new ArrayList<>();
                for(Triangle t : triangles){
                    rearrangedTriangle.add(new Triangle(
                            t.getSideC(), t.getSideA(), t.getSideB(),
                            t. getAngleC(), t.getAngleA(), t.getAngleB()
                    ));
                }
                return rearrangedTriangle;
            }
            if (hasSideB && hasSideC && hasAngleC){
                List<Triangle> triangles = triangle.solveSSA(sideC, sideB, angleC);
                List<Triangle> rearrangedTriangle = new ArrayList<>();
                for(Triangle t : triangles){
                    rearrangedTriangle.add(new Triangle(
                            t.getSideC(), t.getSideB(), t.getSideA(),
                            t. getAngleC(), t.getAngleB(), t.getAngleA()
                    ));
                }
                return rearrangedTriangle;
            }
            if (hasSideA && hasSideC && hasAngleA){
                List<Triangle> triangles = triangle.solveSSA(sideA, sideC, angleA);
                List<Triangle> rearrangedTriangle = new ArrayList<>();
                for(Triangle t : triangles){
                    rearrangedTriangle.add(new Triangle(
                            t.getSideA(), t.getSideC(), t.getSideB(),
                            t. getAngleA(), t.getAngleC(), t.getAngleB()
                    ));
                }
                return rearrangedTriangle;
            }
            if (hasSideA && hasSideC && hasAngleC){
                List<Triangle> triangles = triangle.solveSSA(sideC, sideA, angleC);
                List<Triangle> rearrangedTriangle = new ArrayList<>();
                for(Triangle t : triangles){
                    rearrangedTriangle.add(new Triangle(
                            t.getSideB(), t.getSideC(), t.getSideA(),
                            t. getAngleB(), t.getAngleC(), t.getAngleA()
                    ));
                }
                return rearrangedTriangle;
            }
        }
        // Check ASA or AAS
        if(sideCount >= 1 && angleCount >= 2){
            // ASA
            if(hasSideC && hasAngleA && hasAngleB){
                return List.of(triangle.solveASA(angleA, sideC, angleB));
            }
            if(hasSideA && hasAngleB && hasAngleC){
                triangle = triangle.solveASA(angleB, sideA, angleC);
                return List.of(new Triangle(
                        triangle.getSideC(), triangle.getSideA(), triangle.getSideB(),
                        triangle.getAngleC(), triangle.getAngleA(), triangle.getAngleB()));
            }
            if(hasSideB && hasAngleA && hasAngleC){
                triangle = triangle.solveASA(angleA, sideB, angleC);
                return List.of(new Triangle(
                        triangle.getSideA(), triangle.getSideC(), triangle.getSideB(),
                        triangle.getAngleA(), triangle.getAngleC(), triangle.getAngleB()));
            }
            // AAS
            if(hasSideA && hasAngleA && hasAngleB){
                return List.of(triangle.solveAAS(angleA, angleB, sideA));
            }
            if(hasSideA && hasAngleA && hasAngleC){
                triangle = triangle.solveAAS(angleA, angleC, sideA);
                return List.of(new Triangle(
                        triangle.getSideA(), triangle.getSideC(), triangle.getSideB(),
                        triangle.getAngleA(), triangle.getAngleC(), triangle.getAngleB()));
            }
            if(hasSideB && hasAngleB && hasAngleA){
                triangle = triangle.solveAAS(angleB, angleA, sideB);
                return List.of(new Triangle(
                        triangle.getSideB(), triangle.getSideA(), triangle.getSideC(),
                        triangle.getAngleB(), triangle.getAngleA(), triangle.getAngleC()));
            }
            if(hasSideB && hasAngleB && hasAngleC){
                triangle = triangle.solveAAS(angleB, angleC, sideB);
                return List.of(new Triangle(
                        triangle.getSideC(), triangle.getSideA(), triangle .getSideB(),
                        triangle.getAngleC(), triangle.getAngleA(), triangle.getAngleB()));
            }
            if(hasSideC && hasAngleC && hasAngleA){
                triangle = triangle.solveAAS(angleC, angleA, sideC);
                return List.of(new Triangle(
                        triangle.getSideB(), triangle.getSideC(), triangle.getSideA(),
                        triangle.getAngleB(), triangle.getAngleC(), triangle.getAngleA()));
            }
            if(hasSideC && hasAngleC && hasAngleB){
                triangle = triangle.solveAAS(angleC, angleB, sideC);
                return List.of(new Triangle(
                        triangle.getSideC(), triangle.getSideB(), triangle.getSideA(),
                        triangle.getAngleC(), triangle.getAngleB(), triangle.getAngleA()));
            }
        }
        throw new IllegalArgumentException("No valid triangle could be solved with those inputs.");
    }
}
