import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    public void add(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(OrganizationComponent component) {
        throw new UnsupportedOperationException();
    }

    public abstract String getName();
    public abstract double getBudget();
    public abstract int getEmployeeCount();
    public abstract void display(int indent);
    public abstract OrganizationComponent findEmployee(String name); // Изменено на OrganizationComponent
}

class Employee extends OrganizationComponent {
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return salary;
    }

    public int getEmployeeCount() {
        return 1;
    }

    public void display(int indent) {
        System.out.println("  ".repeat(indent) + "Сотрудник: " + name + ", Должность: " + position + ", Зарплата: " + salary);
    }

    public OrganizationComponent findEmployee(String name) { // Изменено на OrganizationComponent
        return this.name.equals(name) ? this : null;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class Contractor extends OrganizationComponent {
    private String name;
    private String position;
    private double salary;

    public Contractor(String name, String position, double salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getBudget() {
        return 0;
    }

    public int getEmployeeCount() {
        return 1;
    }

    public void display(int indent) {
        System.out.println("  ".repeat(indent) + "Контрактор: " + name + ", Должность: " + position + ", Зарплата: " + salary);
    }

    public OrganizationComponent findEmployee(String name) {
        return this.name.equals(name) ? this : null;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class Department extends OrganizationComponent {
    private String name;
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(OrganizationComponent component) {
        components.add(component);
    }

    public void remove(OrganizationComponent component) {
        components.remove(component);
    }

    public double getBudget() {
        double budget = 0;
        for (OrganizationComponent component : components) {
            budget += component.getBudget();
        }
        return budget;
    }

    public int getEmployeeCount() {
        int count = 0;
        for (OrganizationComponent component : components) {
            count += component.getEmployeeCount();
        }
        return count;
    }

    public void display(int indent) {
        System.out.println("  ".repeat(indent) + "Отдел: " + name);
        for (OrganizationComponent component : components) {
            component.display(indent + 1);
        }
    }

    public OrganizationComponent findEmployee(String name) {
        for (OrganizationComponent component : components) {
            OrganizationComponent employee = component.findEmployee(name);
            if (employee != null) {
                return employee;
            }
        }
        return null;
    }
}

public class Main2 {
    public static void main(String[] args) {
        Department salesDepartment = new Department("Отдел продаж");
        Department marketingDepartment = new Department("Отдел маркетинга");

        Employee alice = new Employee("Алиса", "Менеджер по продажам", 50000);
        Employee bob = new Employee("Боб", "Специалист по маркетингу", 45000);
        Contractor charlie = new Contractor("Чарли", "Временный помощник", 20000);

        salesDepartment.add(alice);
        marketingDepartment.add(bob);
        marketingDepartment.add(charlie);

        Department headOffice = new Department("Головной офис");
        headOffice.add(salesDepartment);
        headOffice.add(marketingDepartment);

        headOffice.display(0);

        System.out.println("Общий бюджет отдела продаж: " + salesDepartment.getBudget());
        System.out.println("Общее количество сотрудников в отделе маркетинга: " + marketingDepartment.getEmployeeCount());

        alice.setSalary(55000);
        System.out.println("Обновленный бюджет отдела продаж: " + salesDepartment.getBudget());

        String searchName = "Боб";
        OrganizationComponent foundEmployee = headOffice.findEmployee(searchName);
        if (foundEmployee != null) {
            System.out.println("Найден сотрудник: " + foundEmployee.getName());
        } else {
            System.out.println("Сотрудник " + searchName + " не найден.");
        }

        System.out.println("Список сотрудников отдела маркетинга:");
        marketingDepartment.display(1);
    }
}
