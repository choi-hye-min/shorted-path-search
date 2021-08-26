package com.company.warehouse.node;

public class RackNode extends VertexNode {
    public RackNode(String nodeCode, Integer distance) {
        super(nodeCode, distance);
    }

    public RackNode(String nodeCode) {
        super(nodeCode, 0);
    }
}
