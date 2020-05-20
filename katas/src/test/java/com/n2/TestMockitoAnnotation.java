package com.n2;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestMockitoAnnotation {
  
  @Mock
  private List list;

  @Test
  public void testList() {
    list.add(100);
  }

  @Test
  public void testListBehaviour() {
    List mockedList = mock(List.class);

    //using mock object
    mockedList.add("one");
    mockedList.clear();

    //verification
    verify(mockedList).add("one");
    verify(mockedList).clear();
  }

  @Test
  public void testStub() {
    //You can mock concrete classes, not just interfaces
    LinkedList mockedList = mock(LinkedList.class);

    //stubbing
    when(mockedList.get(0)).thenReturn("first");
    when(mockedList.get(1)).thenThrow(new RuntimeException("Sorry"));

    //following prints "first"
    System.out.println(mockedList.get(0));

    //following throws runtime exception
    assertThatThrownBy(() -> {
      mockedList.get(1);
    }).isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Sorry");


    //following prints "null" because get(999) was not stubbed
    System.out.println(mockedList.get(999));

    //Although it is possible to verify a stubbed invocation, usually it's just redundant
    //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
    //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
    verify(mockedList).get(0);
  }
}
