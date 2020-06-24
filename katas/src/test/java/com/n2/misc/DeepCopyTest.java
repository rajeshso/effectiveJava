package com.n2.misc;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class DeepCopyTest {

}

//TODO: Equals and Hashcode does not work
class NodeTest {

  @Test
  public void testThreeNodesDeepCopy() {

    Node node1 = new Node(1, new HashSet<Node>());
    Node node2 = new Node(2, new HashSet<Node>());
    Node node3 = new Node(3, new HashSet<Node>());
    node1.neighbours.add(node2);
    node1.neighbours.add(node3);

    node2.neighbours.add(node1);
    node3.neighbours.add(node1);

    Node deepCopiedNode1 = new Node(1, new HashSet<Node>());
    Node deepCopiedNode2 = new Node(2, new HashSet<Node>());
    Node deepCopiedNode3 = new Node(3, new HashSet<Node>());
    deepCopiedNode1.neighbours.add(node2);
    deepCopiedNode1.neighbours.add(node3);

    deepCopiedNode2.neighbours.add(deepCopiedNode1);
    deepCopiedNode3.neighbours.add(deepCopiedNode1);

    Node deepCopiedNode = node1.deepCopy();
    assertThat(deepCopiedNode.value).isOne();
    assertThat(deepCopiedNode.neighbours).hasSize(2);
    assertThat(deepCopiedNode.neighbours).containsExactly(deepCopiedNode2, deepCopiedNode3);
    assertThat(
        deepCopiedNode.neighbours.stream().filter(n -> n.value == 2).collect(Collectors.toList())
            .size()).isEqualTo(1);
    assertThat(
        deepCopiedNode.neighbours.stream().filter(n -> n.value == 3).collect(Collectors.toList())
            .size()).isEqualTo(1);
  }
}
