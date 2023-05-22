class GameEvent {
    private int playerNumber;
    private String eventType;
    private String eventDetails;
    public GameEvent(int playerNumber, String eventType, String eventDetails) {
        this.playerNumber = playerNumber;
        this.eventType = eventType;
        this.eventDetails = eventDetails;
    }
    public int getPlayerNumber() { return this.playerNumber; }
    public void setPlayerNumber(int playerNumber) { this.playerNumber = playerNumber; }
    public String getEventType() { return this.eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getEventDetails() { return this.eventDetails; }
    public void setEventDetails(String eventDetails) { this.eventDetails = eventDetails;}
}

class GameEventNode {
    private GameEvent gameState;
    private GameEventNode next;
    public GameEventNode(GameEvent gameState) {
        this.gameState = gameState;
        this.next = null;
    }
    public GameEvent getGameState() { return this.gameState; }
    public void setGameState(GameEvent gameState) { this.gameState = gameState; }
    public GameEventNode getNext() { return this.next; }
    public void setNext(GameEventNode next) { this.next = next; }
}

public class GameEventsLinkedList {
    int size;
    GameEventNode head;
    public GameEventsLinkedList() {
        this.size = 0;
        this.head = null;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public void push(GameEventNode node) {
        if (node == null) return;
        GameEventNode newNode = new GameEventNode(node.getGameState());
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
    }
    public GameEventNode pop() {
        GameEventNode newNode = this.head;
        if (this.head != null) this.head = this.head.getNext(); 
        if (this.size > 0) this.size--;
        return newNode;
    }
    public GameEventsLinkedList extract(String eventType) {
        if (eventType == null) return null;
        GameEventsLinkedList container = new GameEventsLinkedList();
        GameEventsLinkedList filter = new GameEventsLinkedList();
        GameEventNode current = this.pop();
        while (current != null) {
            container.push(current);
            if (!eventType.equals(current.getGameState().getEventType()))
                filter.push(current);
            current = this.pop();
        }
        GameEventsLinkedList newList = new GameEventsLinkedList();
        while (!filter.isEmpty()) newList.push(filter.pop());
        while (!container.isEmpty()) this.push(container.pop());
        return newList;
    }
    public void display() {
        GameEventNode cycle = this.head;
        while (cycle != null) {
            System.out.println(cycle.getGameState().getEventDetails());
            cycle = cycle.getNext();
        }
    }

    @Override
    public String toString() {
        String information = "";
        return information;
    }
}