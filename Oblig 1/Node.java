public class Node {

    private int memorySize;
    private int numProcessors;

    public Node(int memorySize, int numProcessors) {
        this.memorySize = memorySize;
        this.numProcessors = numProcessors;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public int getNumProcessors() {
        return numProcessors;
    }

}