import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервисный класс для чтения данных из CSV-файла и преобразования их в список объектов {@link Person}.
 * Использует библиотеку OpenCSV для корректного парсинга строк.
 */
public class CsvPersonReader {

    /**
     * Форматтер для парсинга даты рождения из строки.
     * Ожидаемый формат в файле: день.месяц.год (например, 15.05.1970).
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Читает CSV-файл из папки ресурсов (src/main/resources) и преобразует его строки
     * в список объектов {@link Person}. Первая строка файла (заголовки) игнорируется.
     * Неполные строки (содержащие менее 6 колонок) пропускаются.
     *
     * @param csvFilePath имя или путь к CSV-файлу внутри папки ресурсов (например, "foreign_names.csv")
     * @param separator   символ-разделитель колонок в файле (например, ';')
     * @return            список объектов {@link Person}, успешно считанных из файла
     * @throws Exception  если файл не найден, содержит некорректные данные (например, ошибка парсинга чисел/дат)
     * или возникает ошибка ввода-вывода при чтении
     */
    public List<Person> readPersons(String csvFilePath, char separator) throws Exception {
        List<Person> persons = new ArrayList<>();

        // Открываем поток чтения файла из ресурсов проекта
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath)) {
            if (in == null) {
                throw new FileNotFoundException("Файл не найден в ресурсах: " + csvFilePath);
            }

            // Настраиваем парсер OpenCSV с указанным разделителем
            CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();

            // Создаем ридер, пропуская первую строку (заголовок)
            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build()) {

                String[] nextLine;
                // Читаем файл построчно, пока строки не закончатся
                while ((nextLine = reader.readNext()) != null) {
                    // Пропускаем "битые" или пустые строки, в которых не хватает данных
                    if (nextLine.length < 6) continue;

                    // Извлекаем и преобразуем данные по их индексам колонок
                    int id = Integer.parseInt(nextLine[0].trim());
                    String name = nextLine[1].trim();
                    String gender = nextLine[2].trim();
                    LocalDate birthDate = LocalDate.parse(nextLine[3].trim(), DATE_FORMATTER);
                    String department = nextLine[4].trim();
                    BigDecimal salary = new BigDecimal(nextLine[5].trim());

                    // Создаем объект Person и добавляем его в итоговый список
                    Person person = new Person(id, name, gender, department, salary, birthDate);
                    persons.add(person);
                }
            }
        }
        return persons;
    }
}