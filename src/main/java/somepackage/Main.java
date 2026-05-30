package somepackage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Использование конфигурации по умолчанию (из файла)");
        Injector injector = new Injector();

        SomeBean bean1 = injector.inject(new SomeBean());
        System.out.print("Результат bean1.foo(): ");
        /*
         * Ожидаемый результат: AC
         * Почему: В config.properties прописано:
         * somepackage.SomeInterface=somepackage.SomeImpl (метод doSomething выводит "A")
         * somepackage.SomeOtherInterface=somepackage.SODoer (метод doSomeOther выводит "C")
         */
        bean1.foo();


        System.out.println("\nДинамическая смена реализации (Имитация изменения конфига)");
        // Переопределяется настройка: теперь для SomeInterface будет использоваться OtherImpl
        injector.setProperty("somepackage.SomeInterface", "somepackage.OtherImpl");

        SomeBean bean2 = new SomeBean();
        injector.inject(bean2);

        System.out.print("Результат bean2.foo(): ");
        /*
         * Ожидаемый результат: BC
         * Почему: динамически подменяется класс для SomeInterface на OtherImpl.
         * Метод doSomething() у OtherImpl выводит "B".
         * Привязка для SomeOtherInterface не менялась, поэтому там остался SODoer ("C").
         */
        bean2.foo();
    }
}