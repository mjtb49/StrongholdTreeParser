import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;


public class Main {
    public static void main(String[] args) {
        PermIterator fPerms = new PermIterator(5);
        PermIterator cPerms = new PermIterator(3);
        PermIterator sPerms = new PermIterator(3);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("output.txt")));
        int[] f = {0,4,1,3,2}; //default values for if I comment out the loops
        int[] c = {0,1,2};
        int[] s = {0,1,2};
       while (fPerms.hasNext()) {
          f = fPerms.next();
           while (cPerms.hasNext()) {
               c = cPerms.next();
               while (sPerms.hasNext()) {
                   s = sPerms.next();
                    File file = new File(args[0]);
                    StrongholdParser parser = new StrongholdParser(file);
                    //System.out.println(parser.getNextStronghold());
                    HashMap<String, Integer> numPieces;
                    HashMap<String, Integer> numPortals;
                    int total = 0;
                    int successes = 0;
                    int[] numRoomsExplored = new int[100];
                    int numChecked = 0;
                    while (parser.hasNextStronghold() && numChecked < 1000) {
                        numChecked++;
                        Node start = parser.getNextStronghold();
                        //int i = DFSWithBound.exploreStrongholdBranchRandomOrder(start, 7, 0, 0);
                        int i = DFSWithBound.exploreStrongholdBranchSetOrder(start, 7, 0, 0, f, c, s);
                        total++;
                        if (i != -1) {
                            successes++;
                            numRoomsExplored[i]++;
                        }
                    }
                    String str = Arrays.toString(f)+" " +Arrays.toString(c)+" " +Arrays.toString(s)+" ";
                    str += successes+" ";
                    str += total+" ";
                    double last = 0.0;
                   StringBuilder strBuilder = new StringBuilder(str);
                   for (int i : numRoomsExplored) {
                        last += i / (double) successes;
                        strBuilder.append(last).append(" ");
                    }
                   str = strBuilder.toString();
                   str += "\n";
                   writer.write(str);
               }
               sPerms = new PermIterator(3);
           }
           cPerms = new PermIterator(3);
        }
       writer.flush();
       writer.close();
        } catch (IOException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
