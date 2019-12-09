public class Main {

    public static void main(String[] args) {

        Cluster abel = new Cluster("abel.cluster");

        System.out.printf("Noder med minst 32GB: %d \n", abel.numNodesWithMemory(32));
        System.out.printf("Noder med minst 64GB: %d \n", abel.numNodesWithMemory(64));
        System.out.printf("Noder med minst 128GB: %d \n", abel.numNodesWithMemory(128));
        System.out.println();
        System.out.printf("Antall Prosessorer: %d \n", abel.numProcessors());
        System.out.printf("Antall Racks: %d \n", abel.numRacks());
    }

}
