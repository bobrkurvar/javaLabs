import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс, представляющий сущность человека (сотрудника).
 * Содержит базовые личные данные, а также информацию о месте работы и зарплате.
 * Структура класса является "плоской", где подразделение хранится в виде строки.
 */
public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final String department;
    private final BigDecimal salary;
    private final LocalDate birthDate;

    /**
     * Создает новый объект Person с заданными параметрами.
     *
     * @param id         уникальный идентификатор человека
     * @param name       имя (и фамилия) человека
     * @param gender     пол (например, "Male" или "Female")
     * @param department название подразделения/отдела, в котором работает человек
     * @param salary     размер заработной платы
     * @param birthDate  дата рождения человека
     */
    public Person(int id, String name, String gender, String department, BigDecimal salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * @return уникальный идентификатор человека
     */
    public int getId() { return id; }

    /**
     * @return имя человека
     */
    public String getName() { return name; }

    /**
     * @return пол человека
     */
    public String getGender() { return gender; }

    /**
     * @return название подразделения
     */
    public String getDepartment() { return department; }

    /**
     * @return заработная плата
     */
    public BigDecimal getSalary() { return salary; }

    /**
     * @return дата рождения
     */
    public LocalDate getBirthDate() { return birthDate; }

    /**
     * Возвращает строковое представление объекта Person.
     * Удобно для вывода информации в консоль или логирования.
     *
     * @return строка с перечислением всех полей объекта
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                '}';
    }
}