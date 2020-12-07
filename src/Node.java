import java.util.ArrayList;
import java.util.Arrays;

public class Node {

    private String name;
    protected Node parent;
    protected ArrayList<Node> children;

    public Node(String name) {
        this.name = name;
        children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
        if (child != null)
            child.setParent(this);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public Node getParent() {
        return parent;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        int numChildren = children.size();
        String s = name;
        for (Node n : children) {
            s += "\n";
            if (n == null)
                s += "|  DeadEnd";
            else {
                s += n.toString().replaceAll("(?m)^", "|  ");
            }
        }
        return s;
    }
}
