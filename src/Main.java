import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);
        StrongholdParser parser = new StrongholdParser(file);
        //System.out.println(parser.getNextStronghold());
        HashMap<String, Integer> numPieces;
        HashMap<String, Integer> numPortals;
        int total = 0;
        int successes = 0;
        int[] numRoomsExplored = new int[100];
        while (parser.hasNextStronghold()) {
            Node start = parser.getNextStronghold();
            int i = DFSWithBound.exploreStrongholdBranch(start,7,0,0);
            total++;
            if (i != -1) {
                successes++;
                numRoomsExplored[i]++;
            }
        }
        System.out.println(successes);
        System.out.println(total);
        for (int i:numRoomsExplored) {
            System.out.println(i/(float) successes);
        }
    }
}
