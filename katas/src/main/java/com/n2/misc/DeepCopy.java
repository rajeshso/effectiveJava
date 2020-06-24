package com.n2.misc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DeepCopy {

}

//TODO: Equals and Hashcode does not work
class Node {

  public int value;
  public Set<Node> neighbours;

  public Node(int value, Set<Node> neighbours) {
    this.value = value;
    this.neighbours = neighbours;
  }

  public static void main(String[] args) {
/*    Node node1 = new Node(1, new HashSet<Node>());
    Node node2 = new Node(2, new HashSet<Node>());
    Node node3 = new Node(3, new HashSet<Node>());
    node1.neighbours.add(node2);
    node1.neighbours.add(node3);

    node2.neighbours.add(node1);
    node3.neighbours.add(node1);
    final Node deepCopied = node1.deepCopy();
    System.out.println(deepCopied.value);
    for (Node neighbour : deepCopied.neighbours) {
      for (Node innerNeighbour : neighbour.neighbours) {
        System.out.println(
            "Outer Node = " + neighbour.value + " : innerNeighbour = " + innerNeighbour.value);
      }
      System.out.println(neighbour.value);
    }*/

    Node node1 = new Node(1, new HashSet<Node>());
    Node node2 = new Node(2, new HashSet<Node>());
    Node node3 = new Node(3, new HashSet<Node>());
    node1.neighbours.add(node2);
    node1.neighbours.add(node3);

    node2.neighbours.add(node3);
    node2.neighbours.add(node1);
    node3.neighbours.add(node1);
    final Node deepCopied = node1.deepCopy();
    System.out.println(deepCopied.value);
    for (Node neighbour : deepCopied.neighbours) {
      for (Node innerNeighbour : neighbour.neighbours) {
        System.out.println(
            "Outer Node = " + neighbour.value + " : innerNeighbour = " + innerNeighbour.value);
      }
      System.out.println(neighbour.value);
    }


  }

  public Node deepCopy() {
    final Node deepCopiedDynamic = this.deepCopyDynamic(this, new HashMap<>());
    return deepCopiedDynamic;
  }

  private Node deepCopyDynamic(Node n, Map<Integer, Node> clonedNodes) {
    if (clonedNodes.containsKey(n.value)) {
      return clonedNodes.get(n.value);
    }
    Node newNode = new Node(n.value, new HashSet<>(n.neighbours.size()));
    clonedNodes.put(n.value, newNode);
    final Iterator<Node> thisNeighbours = n.neighbours.iterator();
    while (thisNeighbours.hasNext()) {
      final Node neighbourNode = thisNeighbours.next();
      final Node innerNode = deepCopyDynamic(neighbourNode, clonedNodes);
      newNode.neighbours.add(innerNode);
    }
    return newNode;
  }
}

//Find logs between two dates
