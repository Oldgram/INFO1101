import java.util.function.Predicate;


/**
 * Question:
 *
 * You are asked to complete the two TODO belows:
 *
 * - the add method appending an element at the end of the linked-list
 * - the filter method that returns a new LinkedListWithFilter containing
 *   the same elements in the same order but only the ones satisfying the predicate (condition)
 *   are kept.
 *
 *   Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 */
public class LinkedListWithFilter {

    Node first = null;
    Node last = null;

    /**
     * Add the element v at the end of the linked list
     * @param v the element to add at the end
     */
    public void add(int v) {
        Node newNode = new Node(v, null); // Initialise a new node with the given value
        if (this.first == null) { // If the LinkedList is empty, adds this node at the top
            this.first = newNode;
            this.last = first;
        } else { // else, loop through the list
            Node node = this.first;
            while(node != this.last) {
                node = node.next;
            }
            node.next = newNode;
            this.last = newNode; // the new node becomes the last one of the list
        }
    }

    public void add(int ... values) {
        for (int v: values) {
            add(v);
        }
    }

    class Node {
        int v;
        Node next;
        Node(int v, Node next) {
            this.v = v;
            this.next = next;
        }
    }

    /**
     * Compute a new version of the list in the same order
     * but only keeping the ones satisfying
     * the predicate (condition) in argument
     * Read the unit test to make sure you understand
     * @param p the predicate specifying the elements to keep
     * @return a new version of the list, same order but
     *         with only the elements satisfying the predicate
     */
    public LinkedListWithFilter filter(Predicate<Integer> p) {
        LinkedListWithFilter filtered = new LinkedListWithFilter(); // Initialise a new empty LinkedListWithFilter
        if (this.first != null) { // if this list is not null, loop through it (otherwise return the new empty list)
            Node node = this.first;
            while (node != this.last) { // loop through the list
                if (p.test(node.v)) { // if the node satisfy the predicate, add it to the new list
                    filtered.add(node.v);
                }
                node = node.next;
            }
            if (p.test(node.v)) {
                filtered.add(node.v); // same for the last node
            }
        }
        return filtered; // return the new (filtered) list
    }
}

