package somepackage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Использование конфигурации по умолчанию (из файла)");
        Injector injector = new Injector();

        SomeBean bean1 = injector.inject(new SomeBean());
        System.out.print("Результат bean1.foo(): ");
        bean1.foo(); // Если в файле SomeImpl и SODoer, выведет: AC


        System.out.println("Динамическая смена реализации (Имитация изменения конфига)");
        injector.setProperty("somepackage.SomeInterface", "somepackage.OtherImpl");

        SomeBean bean2 = new SomeBean();
        injector.inject(bean2);

        System.out.print("Результат bean2.foo(): ");
        bean2.foo();
    }
}