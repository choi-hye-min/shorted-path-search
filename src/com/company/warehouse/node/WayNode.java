package com.company.warehouse.node;

public class WayNode extends VertexNode{
    public WayNode(String wayCode, Integer distance) {
        super(wayCode, distance);
    }

    public WayNode(String wayCode) {
        super(wayCode, 0);
    }
}
