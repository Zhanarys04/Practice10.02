import java.util.ArrayList;
import java.util.List;

abstract class OrganizationComponent {
    protected String name;

    public OrganizationComponent(String name) {
        this.name = name;
    }

    public abstract void displayInfo();
    public abstract double getBudget();
    public abstract int getEmployeeCount();
}

class Employee extends OrganizationComponent {
    private String position;
    private double salary;

    public Employee(String name, String position, double salary) {
        super(name);
        this.position = position;
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println("Сотрудник: " + name + ", Должность: " + position + ", Зарплата: " + salary);
    }

    @Override
    public double getBudget() {
        return salary;
    }

    @Override
    public int getEmployeeCount() {
        return 1;
    }

    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }
}


class Department extends OrganizationComponent {
    private List<OrganizationComponent> components = new ArrayList<>();

    public Department(String name) {
        super(name);
    }

    public void addComponent(OrganizationComponent component) {
        components.add(component);
    }

    public void removeComponent(OrganizationComponent component) {
        components.remove(component);
    }

    @Override
    public void displayInfo() {
        System.out.println("Отдел: " + name);
        for (OrganizationComponent component : components) {
            component.displayInfo();
        }
    }

    @Override
    public double getBudget() {
        double totalBudget = 0;
        for (OrganizationComponent component : components) {
            totalBudget += component.getBudget();
        }
        return totalBudget;
    }

    @Override
    public int getEmployeeCount() {
        int totalCount = 0;
        for (OrganizationComponent component : components) {
            totalCount += component.getEmployeeCount();
        }
        return totalCount;
    }

    public List<OrganizationComponent> getComponents() {
        return components;
    }
}

public class OrganizationManagement {
    public static void main(String[] args) {
        // Создаем сотрудников
        Employee emp1 = new Employee("Иван Иванов", "Менеджер", 50000);
        Employee emp2 = new Employee("Анна Петрова", "Разработчик", 60000);
        Employee emp3 = new Employee("Сергей Сидоров", "Аналитик", 55000);

        Department devDepartment = new Department("Отдел разработки");
        devDepartment.addComponent(emp2);
        devDepartment.addComponent(emp3);

        Department hrDepartment = new Department("Отдел кадров");
        hrDepartment.addComponent(emp1);

        Department mainDepartment = new Department("Главный офис");
        mainDepartment.addComponent(devDepartment);
        mainDepartment.addComponent(hrDepartment);

        mainDepartment.displayInfo();

        System.out.println("Общий бюджет отдела разработки: " + devDepartment.getBudget());
        System.out.println("Общий бюджет отдела кадров: " + hrDepartment.getBudget());
        System.out.println("Общий бюджет главного офиса: " + mainDepartment.getBudget());

        System.out.println("Общее количество сотрудников в главном офисе: " + mainDepartment.getEmployeeCount());
    }
}
