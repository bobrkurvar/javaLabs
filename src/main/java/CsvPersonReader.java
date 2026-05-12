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

public class CsvPersonReader {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public List<Person> readPersons(String csvFilePath, char separator) throws Exception {
        List<Person> persons = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath)) {
            if (in == null) {
                throw new FileNotFoundException("Файл не найден: " + csvFilePath);
            }

            CSVParser parser = new CSVParserBuilder().withSeparator(separator).build();
            try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in))
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build()) {

                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    if (nextLine.length < 6) continue;

                    int id = Integer.parseInt(nextLine[0].trim());
                    String name = nextLine[1].trim();
                    String gender = nextLine[2].trim();
                    LocalDate birthDate = LocalDate.parse(nextLine[3].trim(), DATE_FORMATTER);
                    String department = nextLine[4].trim();
                    BigDecimal salary = new BigDecimal(nextLine[5].trim());

                    Person person = new Person(id, name, gender, department, salary, birthDate);
                    persons.add(person);
                }
            }
        }
        return persons;
    }
}