import java.util.ArrayList;
import java.util.Collections;

public class DFSWithBound {
    public static int exploreStrongholdBranch(Node n, int maxDepth, int numRooms, int depth) {
        //no cost and no portal because there wasn't a room
        if (n == null)
            return -1;
        //this is the portal room
        if (n.getName().equals("PortalRoom")) {
            return numRooms;
        }
        //we peeked into the room 1 past max depth, so doesn't take travel time
        if (depth > maxDepth)
            return -1;
        //ok it is a real room we are in
        numRooms++;
        ArrayList<Node> c = n.getChildren();
        //head to the portal room cuz I like that
        for (Node child:c) {
            if (child !=  null)
                if (child.getName().equals("PortalRoom"))
                    return exploreStrongholdBranch(child, maxDepth, numRooms, depth + 1);
        }
        //check children in random order
        Collections.shuffle(c);
        for (Node child: c) {
            int i = exploreStrongholdBranch(child, maxDepth, numRooms, depth + 1);
             if (i == -1)
                 numRooms++;
             else return i;
        }
        return -1;
    }
}
