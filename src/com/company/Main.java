package com.company;

import com.company.warehouse.ShortPathSearch;
import com.company.warehouse.node.RackNode;
import com.company.warehouse.node.VertexNode;
import com.company.warehouse.node.WayNode;

import java.util.Collections;
import java.util.List;

public class Main {
    private final static String FILE_PATH = "warehouseMap.txt";

    public static void main(String[] args) {
        ShortPathSearch shortPathSearch = new ShortPathSearch();
        shortPathSearch.initalizeWarehouseMap(FILE_PATH);

        final WayNode startNode = new WayNode("a2");
        final RackNode finishNode = new RackNode("a9");

        final List<VertexNode> shortestPath = shortPathSearch.getShortestPath(startNode, finishNode);
        shortestPath.add(startNode);

        Collections.reverse(shortestPath);

        System.out.println(shortestPath);
    }
}
