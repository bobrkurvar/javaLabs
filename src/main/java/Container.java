public class Container {
    private int[] data;       // массив для хранения элементов
    private int size;         // текущее количество элементов

    private static final int DEFAULT_LENGTH = 5; // начальная ёмкость по умолчанию

    public Container() {
        data = new int[DEFAULT_LENGTH];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length)
            increaseCapacity();
        data[size++] = value;
    }

    public int get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        size--;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    // Увеличивает ёмкость массива (в 1.5 раза, но не меньше 1)
    private void increaseCapacity() {
        int newCapacity = data.length == 0 ? 1 : data.length * 3 / 2 + 1;
        int[] newData = new int[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    // Проверяет, допустим ли индекс; если нет — бросает исключение
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}