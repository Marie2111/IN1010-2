import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cluster {

    private int nodesPerRack;
    private ArrayList<Rack> racks;

    public Cluster(int nodesPerRack) {
        this.nodesPerRack = nodesPerRack;
        racks = new ArrayList<>();
    }

    public Cluster(String fileName) {
        Scanner scanner = null;

        try {
            File file = new File(fileName);
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        nodesPerRack = 0;
        racks = new ArrayList<>();

        parseFile(scanner);

        scanner.close();
    }

    public void parseFile(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("#") || line.isEmpty())
                continue;

            String[] data = line.split(" ");

            if (data.length == 1) {
                nodesPerRack = Integer.parseInt(data[0]);
            }

            if (data.length == 3) {
                int numNodes = Integer.parseInt(data[0]);
                int memory = Integer.parseInt(data[1]);
                int processors = Integer.parseInt(data[2]);

                for (int i = 0; i < numNodes; i++) {
                    this.addNode(new Node(memory, processors));
                }
            }
        }
    }

    public void addNode(Node node) {
        if (racks.isEmpty() || !racks.get(racks.size() - 1).hasCapacity()) {
            racks.add(new Rack(nodesPerRack));
        }

        racks.get(racks.size() - 1).addNode(node);
    }

    public int numProcessors() {
        int processorCount = 0;

        for (Rack rack : racks) {
            processorCount += rack.numProcessors();
        }

        return processorCount;
    }

    public int numNodesWithMemory(int targetMemory) {
        int numNodes = 0;

        for (Rack rack : racks) {
            numNodes += rack.numNodesWithMemory(targetMemory);
        }

        return numNodes;
    }

    public int numRacks() {
        return racks.size();
    }
}
