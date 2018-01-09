package com.dheeraj.lowestpath;

/**
 * Created by dheeraj on 08/01/18.
 */

public class Matrix {

    public static int[][] matrixFromEditText(String input) {
        if (input != null) {
            String[] rows = input.split("\n");
            String[] firstColumns = rows[0].split("\\s+");
            int[][] output = new int[rows.length][firstColumns.length];

            try {
                for (int row = 0; row < rows.length; row++) {
                    String[] columns = rows[row].split("\\s+");
                    for (int column = 0; column < columns.length; column++) {
                        if (column < output[0].length) {
                            output[row][column] = Integer.valueOf(columns[column]);
                        }
                    }
                }

                return output;
            } catch (Exception e) {
                return new int[0][0];
            }
        } else {
            return new int[0][0];
        }
    }
}
