import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContainerTest {
    private Container container;

    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @Test
    void testAddAndSize() {
        assertEquals(0, container.size());
        container.add(10);
        assertEquals(1, container.size());
        container.add(20);
        assertEquals(2, container.size());
    }

    @Test
    void testGet() {
        container.add(5);
        container.add(15);
        container.add(25);
        assertEquals(5, container.get(0));
        assertEquals(15, container.get(1));
        assertEquals(25, container.get(2));
    }

    @Test
    void testRemove() {
        container.add(1);
        container.add(2);
        container.add(3);
        container.remove(1);
        assertEquals(2, container.size());
        assertEquals(1, container.get(0));
        assertEquals(3, container.get(1));
    }

    @Test
    void testIsEmpty() {
        assertTrue(container.isEmpty());
        container.add(42);
        assertFalse(container.isEmpty());
    }

    @Test
    void testDynamicExpansion() {
        int initialCapacity = 5;
        for (int i = 0; i < initialCapacity; i++)
            container.add(i);
        assertEquals(initialCapacity, container.size());
        container.add(100); // должен расшириться
        assertEquals(initialCapacity + 1, container.size());
        assertEquals(100, container.get(initialCapacity));
    }

    @Test
    void testGetIndexOutOfBounds() {
        container.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1));
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        container.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(1));
    }

    @Test
    void testAddAfterRemove() {
        container.add(1);
        container.add(2);
        container.remove(0);
        container.add(3);
        assertEquals(2, container.size());
        assertEquals(2, container.get(0));
        assertEquals(3, container.get(1));
    }

    @Test
    void testRemoveLastElement() {
        container.add(10);
        container.add(20);
        container.remove(1); // удаляем последний
        assertEquals(1, container.size());
        assertEquals(10, container.get(0));
        container.add(30);
        assertEquals(2, container.size());
        assertEquals(10, container.get(0));
        assertEquals(30, container.get(1));
    }
}