import java.math.BigDecimal;
import java.time.LocalDate;

public class Person {
    private final int id;
    private final String name;
    private final String gender;
    private final String department;
    private final BigDecimal salary;
    private final LocalDate birthDate;

    public Person(int id, String name, String gender, String department, BigDecimal salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getGender() { return gender; }
    public String getDepartment() { return department; }
    public BigDecimal getSalary() { return salary; }
    public LocalDate getBirthDate() { return birthDate; }

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