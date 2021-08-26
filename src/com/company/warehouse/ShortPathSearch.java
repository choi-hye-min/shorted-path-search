package com.company.warehouse;

import com.company.warehouse.node.RackNode;
import com.company.warehouse.node.VertexNode;
import com.company.warehouse.node.WayNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortPathSearch {
    private final Map<VertexNode, List<VertexNode>> vertices;

    public ShortPathSearch() {
        this.vertices = new HashMap<>();
    }

    public void addNode(VertexNode currentNode, List<VertexNode> nodes) {
        this.vertices.put(currentNode, nodes);
    }

    public void initalizeWarehouseMap(String filePath) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
                final String[] locationCodes = lineText.split(" ");
                final String currentLocationCode = locationCodes[0];

                List<VertexNode> vertexNodes = new ArrayList<>();
                for (int i = 1; i <= locationCodes.length - 1; i++) {
                    final VertexNode node = createNode(locationCodes[i]);
                    vertexNodes.add(node);
                }

                this.addNode(createNode(currentLocationCode), vertexNodes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<VertexNode> getShortestPath(VertexNode start, VertexNode finish) {
        final Map<VertexNode, Integer> distances = new HashMap<>();
        final Map<VertexNode, VertexNode> privouse = new HashMap<>();
        PriorityQueue<VertexNode> nodes = new PriorityQueue<>();

        for (VertexNode vertex : vertices.keySet()) {
            if (vertex.equals(start)) {
                distances.put(vertex, 0);
                vertex.setDistance(0);
                nodes.add(vertex);
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(createVertexNode(vertex));
            }

            privouse.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            VertexNode smallest = nodes.poll();
            if (smallest.equals(finish)) {
                List<VertexNode> path = new ArrayList<>();
                while (privouse.get(smallest) != null) {
                    path.add(smallest);
                    smallest = privouse.get(smallest);
                }

                return path;
            }

            if (distances.get(smallest) == Integer.MAX_VALUE) break;

            for (VertexNode neighbor : vertices.get(smallest)) {
                int alt = distances.get(smallest) + neighbor.getDistance();
                if (alt < distances.get(neighbor)) {
                    distances.put(neighbor, alt);
                    privouse.put(neighbor, smallest);

                    forCalculrate:
                    for (VertexNode node : nodes) {
                        if (node.equals(neighbor)) {
                            nodes.remove(node);
                            node.setDistance(alt);
                            nodes.add(node);
                            break forCalculrate;
                        }
                    }
                }
            }
        }

        return new ArrayList<>(distances.keySet());
    }

    private VertexNode createVertexNode(VertexNode vertex) {
        return vertex instanceof RackNode ?
                new RackNode(vertex.getNodeCode(), Integer.MAX_VALUE) : new WayNode(vertex.getNodeCode(), Integer.MAX_VALUE);
    }

    private VertexNode createNode(String locationCode) {
        final byte[] bytes = locationCode.getBytes();
        return (char) bytes[0] == 'w' ? new WayNode(locationCode, 1) : new RackNode(locationCode, 1);
    }
}
