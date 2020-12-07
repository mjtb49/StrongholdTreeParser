import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StrongholdParser {

    final private ArrayList<String> currentNodeLines;
    private String currentStrongholdName;
    Scanner scanner;

    public StrongholdParser(File file) {
        currentNodeLines = new ArrayList<>();
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (FileNotFoundException f) {
            System.err.println("File not found");
        }
    }

    public boolean hasNextStronghold() {
        return scanner.hasNextLine();
    }

    private Node parseNode(String nodeLine) {
        String[] components = nodeLine.split(" ");
        Node node = new Node(components[0]);
        //System.out.println(components[0]);
        //System.out.println(components.length - 1);
        for (int i = 1; i < components.length; i++) {
            int index = Integer.parseInt(components[i]);
            if (index < 0) {
                node.addChild(null);
            } else {
                node.addChild(parseNode(currentNodeLines.get(index)));
            }
        }
        return node;
    }

    public Node getNextStronghold() {
        while (scanner != null && scanner.hasNextLine()) {
            if (scanner.nextLine().contains("START")) {
                currentNodeLines.clear();
                boolean foundEND = false;
                currentStrongholdName = scanner.nextLine(); //currently not doing anything with the name.
                while (scanner.hasNextLine() && !foundEND) {
                    String nodeLine = scanner.nextLine();
                    if (nodeLine.contains("END"))
                        foundEND = true;
                    else
                        currentNodeLines.add(nodeLine);
                }
                if (!foundEND) {
                    System.err.println("Didn't find END string where expected");
                }
                return parseNode(currentNodeLines.get(currentNodeLines.size() - 1));
            }
        }
        System.err.println("Did not find a START where expected");
        return null;
    }

    public String getCurrentStrongholdName() {
        return currentStrongholdName;
    }
}
