package com.dheeraj.lowestpath;

import java.util.Comparator;

/**
 * Created by dheeraj on 08/01/18.
 */

public class PathStateComparator implements Comparator<PathState> {
    private static int SORT_LEFT_FIRST = -1;
    private static int SORT_RIGHT_FIRST = 1;
    private static int SORT_EQUAL = 0;

    public PathStateComparator() {
    }

    public int compare(PathState leftPath, PathState rightPath) {
        int comparedLength = this.compareLengths(leftPath, rightPath);
        return comparedLength != 0?comparedLength:this.compareCosts(leftPath, rightPath);
    }

    private int compareLengths(PathState leftPath, PathState rightPath) {
        int leftLength = this.getLengthFromPath(leftPath);
        int rightLength = this.getLengthFromPath(rightPath);
        return leftLength > rightLength?SORT_LEFT_FIRST:(leftLength == rightLength?SORT_EQUAL:SORT_RIGHT_FIRST);
    }

    private int compareCosts(PathState leftPath, PathState rightPath) {
        int leftCost = this.getCostFromPath(leftPath);
        int rightCost = this.getCostFromPath(rightPath);
        return leftCost < rightCost?SORT_LEFT_FIRST:(leftCost == rightCost?SORT_EQUAL:SORT_RIGHT_FIRST);
    }

    private int getLengthFromPath(PathState path) {
        return path != null?path.getPathLength():-2147483648;
    }

    private int getCostFromPath(PathState path) {
        return path != null?path.getTotalCost():2147483647;
    }
}
