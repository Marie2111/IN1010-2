import java.util.ArrayList;

public class Rack {

    private int maxNodes;
    private ArrayList<Node> nodes;

    public Rack(int maxNodes) {
        this.maxNodes = maxNodes;
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        if (this.hasCapacity()) {
            nodes.add(node);
        } else {
            System.out.println("Couldn't add the node because the rack is full :c ");
        }
    }

    public boolean hasCapacity() {
        return nodes.size() < maxNodes;
    }

    public int numProcessors() {
        int processorCount = 0;

        for (Node node : nodes) {
            processorCount += node.getNumProcessors();
        }

        return processorCount;
    }

    public int numNodesWithMemory(int targetMemory) {
        int numNodes = 0;

        for (Node node : nodes) {
            if (node.getMemorySize() >= targetMemory) {
                numNodes++;
            }
        }

        return numNodes;
    }
}
