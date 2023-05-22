import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameEventsLinkedListTest {
    @Test
    void isEmpty() {
        GameEvent event = new GameEvent(1, "health", "100");
        GameEventNode node = new GameEventNode(event);
        GameEventsLinkedList empty = new GameEventsLinkedList();
        GameEventsLinkedList nonEmpty = new GameEventsLinkedList();

        assertTrue(empty.isEmpty());
        assertTrue(nonEmpty.isEmpty());
        
        nonEmpty.push(node);
        assertTrue(empty.isEmpty());
        assertFalse(nonEmpty.isEmpty());
    }
    @Test
    void push() {
        GameEvent eventOne = new GameEvent(1, "health", "100");
        GameEvent eventTwo = new GameEvent(1, "health", "90");
        GameEvent eventThree = new GameEvent(1, "health", "80");
        GameEventNode node = new GameEventNode(eventOne);
        GameEventNode nodeNext = new GameEventNode(eventTwo);
        GameEventNode nodeAfterNext = new GameEventNode(eventThree);
        GameEventsLinkedList empty = new GameEventsLinkedList();
        GameEventsLinkedList nonEmpty = new GameEventsLinkedList();
        assertTrue(empty.size == 0);
        assertTrue(nonEmpty.size == 0);

        nonEmpty.push(node);
        assertTrue(nonEmpty.size == 1);
        nonEmpty.push(nodeNext);
        assertTrue(nonEmpty.size == 2);
        nonEmpty.push(nodeAfterNext);
        assertTrue(nonEmpty.size == 3);

        assertEquals(nonEmpty.head.getGameState(), nodeAfterNext.getGameState());
        assertEquals(nonEmpty.head.getNext().getGameState(), nodeNext.getGameState());
        assertEquals(nonEmpty.head.getNext().getNext().getGameState(), node.getGameState());
    }
    @Test
    void pop() {
        GameEvent eventOne = new GameEvent(1, "health", "100");
        GameEvent eventTwo = new GameEvent(1, "health", "90");
        GameEvent eventThree = new GameEvent(1, "health", "80");
        GameEventNode node = new GameEventNode(eventOne);
        GameEventNode nodeNext = new GameEventNode(eventTwo);
        GameEventNode nodeAfterNext = new GameEventNode(eventThree);
        GameEventsLinkedList nonEmpty = new GameEventsLinkedList();

        assertEquals(nonEmpty.size, 0);
        nonEmpty.push(node);
        assertEquals(nonEmpty.size, 1);
        nonEmpty.push(node);
        assertEquals(nonEmpty.size, 2);
        nonEmpty.push(node);
        assertEquals(nonEmpty.size, 3);

        // should remove the first node and its following nodes
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 2);
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 1);
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 0);

        nonEmpty.push(node);
        assertEquals(nonEmpty.size, 1);
        nonEmpty.push(nodeNext);
        assertEquals(nonEmpty.size, 2);
        nonEmpty.push(nodeAfterNext);
        assertEquals(nonEmpty.size, 3);
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 2);
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 1);
        nonEmpty.pop();
        assertEquals(nonEmpty.size, 0);
    }
    @Test
    void extract() {
        GameEventNode healthNode = new GameEventNode(new GameEvent(1, "health", "100"));
        GameEventNode speedNode = new GameEventNode(new GameEvent(1, "speed", "100"));
        GameEventsLinkedList empty = new GameEventsLinkedList();
        GameEventsLinkedList nonEmpty = new GameEventsLinkedList();

        nonEmpty.push(healthNode);  // 1
        nonEmpty.push(healthNode);  // 2
        nonEmpty.push(speedNode);   // 3
        nonEmpty.push(speedNode);   // 4
        assertEquals(empty.size, 0);
        assertEquals(nonEmpty.size, 4);

        assertEquals(nonEmpty.extract("health").size, 2);
        assertEquals(nonEmpty.extract("speed").size, 2);
        assertEquals(nonEmpty.extract("damage").size, 4);

        nonEmpty.push(speedNode);   // 5
        nonEmpty.push(speedNode);   // 6
        assertEquals(nonEmpty.extract("health").size, 4);
        assertEquals(nonEmpty.extract("speed").size, 2);
        assertEquals(empty.size, 0);
        assertEquals(nonEmpty.size, 6);
        System.out.print(nonEmpty.pop().getGameState().getEventType());
        System.out.print(nonEmpty.extract("").pop().getGameState().getEventType());
    }
}
