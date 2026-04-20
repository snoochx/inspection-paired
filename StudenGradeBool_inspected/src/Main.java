import java.util.ArrayList;
import java.util.List;

public class Main {

    //Неинформативное имя класса. "Main" ничего не говорит о предназначении
    private String courseName;
    private List<Student> students = new ArrayList<>();

    public Main(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    public double calculateAverageScore() {
        int sum = 0;
        // Нарушение инкапсуляции. Прямой доступ к полю score.
        // Надо использовать геттеры
        for (Student s : students) {
            sum += s.score;
        }

        // Лучше не сравнивать с 0 через .size() == 0, а использовать students.isEmpty()
        if (students.size() == 0) {
            return 0;
        }

        // Здесь происходит целочисленное деление.
        // Несмотря на тип double у метода, результат может быть неточным.
        return sum / students.size();
    }

    public String findTopStudent() {
        Student best = null;
        // Прямой доступ к полю score
        for (Student s : students) {
            if (best == null) {
                best = s;
            } else if (s.score > best.score) {
                best = s;
            }
        }

        if (best == null) {
            // Если изменить сообщение, придется менять во всех местах.
            // Лучше вынести в константу private static final String NO_STUDENTS_MSG = "No students";
            return "No students";
        }

        // Прямой доступ к полям name и score
        return best.name + " (" + best.score + ")";
    }

    public void printReport() {
        System.out.println("Course: " + courseName);
        System.out.println("Students: " + students.size());
        System.out.println("Average score: " + calculateAverageScore());
        System.out.println("Best student: " + findTopStudent());

        // Магическое число 60. Что это за порог? Непонятно.
        // Лучше вынести в константу,
        // например PASS_MARK.
        for (Student s : students) {
            if (s.score >= 60) {
                System.out.println(s.name + " passed");
            } else {
                System.out.println(s.name + " failed");
            }
        }
    }

    public static void main(String[] args) {
        // Неинформативное имя переменной. Что такое "book" для курса? Переименуй
        Main book = new Main("Programming 101");

        //конструктора без проверок.
        book.addStudent(new Student("Anna", 78));
        book.addStudent(new Student("Ivan", 59));
        book.addStudent(new Student("Maria", 91));
        book.addStudent(new Student("Pavel", 67));
        book.printReport();
    }
}

class Student {
    // Публичные поля. Любой код может менять их напрямую. Сделать приватнимы
    String name;
    int score;

    Student(String name, int score) {
        // Нет валидации входных параметров
        //  Можно создать студента с пустым именем или некорректным баллом.
        this.name = name;
        this.score = score;
    }
}