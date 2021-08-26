package com.company.warehouse.node;

import java.util.Objects;

public abstract class VertexNode implements Comparable<VertexNode>{
    private String nodeCode;    // 위치코드
    private Integer distance;   // 거리

    public VertexNode(String nodeCode, Integer distance) {
        this.nodeCode = nodeCode;
        this.distance = distance;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public Integer getDistance() {
        return distance;
    }

    @Override
    public int compareTo(VertexNode o) {
        return Integer.compare(this.distance, o.getDistance());
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return String.format("Node[code=%s, distance=%d]", getNodeCode(), getDistance());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (Objects.isNull(obj)) return false;

        if (!(obj instanceof VertexNode)) return false;

        VertexNode other = (VertexNode) obj;
        if (distance == null) {
            if (other.distance != null) return false;
        }

        if (Objects.isNull(nodeCode)) {
            if (other.nodeCode != null) {
                return false;
            }
        } else if (!nodeCode.equals(other.nodeCode)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.nodeCode.hashCode();
    }
}
