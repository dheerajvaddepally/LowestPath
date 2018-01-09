package com.dheeraj.lowestpath;

/**
 * Created by dheeraj on 08/01/18.
 */

public class PathStateCollector {
    private PathState bestPath;
    private PathStateComparator comparator = new PathStateComparator();

    public PathStateCollector() {
    }

    public PathState getBestPath() {
        return this.bestPath;
    }

    public void addPath(PathState newPath) {
        if(this.comparator.compare(newPath, this.bestPath) < 0) {
            this.bestPath = newPath;
        }

    }
}