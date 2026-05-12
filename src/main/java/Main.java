import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvPersonReader readerService = new CsvPersonReader();
        String fileName = "foreign_names.csv";

        try {
            List<Person> people = readerService.readPersons(fileName, ';');

            System.out.println("Успешно считано людей: " + people.size());

            for (int i = 0; i < Math.min(5, people.size()); i++) {
                System.out.println(people.get(i));
            }

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}