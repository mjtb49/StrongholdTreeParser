import java.util.ArrayList;
import java.util.Collections;

public class DFSWithBound {
    public static int exploreStrongholdBranchRandomOrder(Node n, int maxDepth, int numRooms, int depth) {
        //no cost and no portal because there wasn't a room
        if (n == null)
            return -2;
        //this is the portal room
        if (n.getName().equals("PortalRoom")) {
            return numRooms;
        }
        //we peeked into the room 1 past max depth, so doesn't take travel time
        if (depth > maxDepth)
            return -2;
        //ok it is a real room we are in
        numRooms++;
        ArrayList<Node> c = n.getChildren();
        //head to the portal room cuz I like that
        for (Node child:c) {
            if (child !=  null)
                if (child.getName().equals("PortalRoom"))
                    return exploreStrongholdBranchRandomOrder(child, maxDepth, numRooms, depth + 1);
        }
        //check children in random order
        Collections.shuffle(c);
        for (Node child: c) {
            int i = exploreStrongholdBranchRandomOrder(child, maxDepth, numRooms, depth + 1);
             if (i == -1)
                 numRooms++;
             else if (i > 0) return i;
        }
        return -1;
    }

    public static int exploreStrongholdBranchSetOrder(Node n, int maxDepth, int numRooms, int depth, int[] fiveWayOrder, int[] corridorOrder, int[] squareRoomOrder) {
        //no cost and no portal because there wasn't a room
        if (n == null)
            return -2;
        //this is the portal room
        if (n.getName().equals("PortalRoom")) {
            return numRooms;
        }
        //we peeked into the room 1 past max depth, so doesn't take travel time
        if (depth > maxDepth)
            return -2;
        //ok it is a real room we are in
        numRooms++;
        ArrayList<Node> c = n.getChildren();
        //head to the portal room cuz I like that
        for (Node child:c) {
            if (child !=  null)
                if (child.getName().equals("PortalRoom"))
                    return exploreStrongholdBranchSetOrder(child, maxDepth, numRooms, depth + 1, fiveWayOrder, corridorOrder, squareRoomOrder);
        }

        switch (n.getName()) {
            case "FiveWayCrossing":
                for (int room = 0; room < 5; room++) {
                    int i = exploreStrongholdBranchSetOrder(c.get(fiveWayOrder[room]), maxDepth, numRooms, depth + 1, fiveWayOrder, corridorOrder, squareRoomOrder);
                    if (i == -1)
                        numRooms++;
                    else if (i > 0) return i;
                }
                break;
            case "SquareRoom":
                for (int room = 0; room < 3; room++) {
                    int i = exploreStrongholdBranchSetOrder(c.get(squareRoomOrder[room]), maxDepth, numRooms, depth + 1, fiveWayOrder, corridorOrder, squareRoomOrder);
                    if (i == -1)
                        numRooms++;
                    else if (i > 0) return i;
                }
                break;
            case "Corridor":
                for (int room = 0; room < 3; room++) {
                    int i = exploreStrongholdBranchSetOrder(c.get(corridorOrder[room]), maxDepth, numRooms, depth + 1, fiveWayOrder, corridorOrder, squareRoomOrder);
                    if (i == -1)
                        numRooms++;
                    else if (i > 0) return i;
                }
                break;
            default:
                for (Node child : c) {
                    int i = exploreStrongholdBranchSetOrder(child, maxDepth, numRooms, depth + 1, fiveWayOrder, corridorOrder, squareRoomOrder);
                    if (i == -1)
                        numRooms++;
                    else if (i > 0) return i;
                }
                break;
        }
        return -1;
    }
}
