/**
 * Сущность, представляющая подразделение (отдел).
 */
public class Department {
    private final int id;
    private final String name;

    /**
     * Создает новое подразделение.
     *
     * @param id   уникальный идентификатор (генерируется программой)
     * @param name название подразделения (читается из файла)
     */
    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}