package com.dheeraj.lowestpath;

/**
 * Created by dheeraj on 08/01/18.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GridVisitor {
    private Grid grid;
    private PathStateComparator pathComparator;

    public GridVisitor(Grid grid) {
        if(grid == null) {
            throw new IllegalArgumentException("A visitor requires a grid");
        } else {
            this.grid = grid;
            this.pathComparator = new PathStateComparator();
        }
    }

    public PathState getBestPathForGrid() {
        List<PathState> allPaths = new ArrayList();

        for(int row = 1; row <= this.grid.getRowCount(); ++row) {
            RowVisitor visitor = new RowVisitor(row, this.grid, new PathStateCollector());
            allPaths.add(visitor.getBestPathForRow());
        }

        Collections.sort(allPaths, this.pathComparator);
        return (PathState)allPaths.get(0);
    }
}
