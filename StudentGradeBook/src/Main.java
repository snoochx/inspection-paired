import java.util.ArrayList;
import java.util.List;

public class Main {

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
        for (Student s : students) {
            sum += s.score;
        }

        if (students.size() == 0) {
            return 0;
        }

        return sum / students.size();
    }

    public String findTopStudent() {
        Student best = null;
        for (Student s : students) {
            if (best == null) {
                best = s;
            } else if (s.score > best.score) {
                best = s;
            }
        }

        if (best == null) {
            return "No students";
        }

        return best.name + " (" + best.score + ")";
    }

    public void printReport() {
        System.out.println("Course: " + courseName);
        System.out.println("Students: " + students.size());
        System.out.println("Average score: " + calculateAverageScore());
        System.out.println("Best student: " + findTopStudent());

        for (Student s : students) {
            if (s.score >= 60) {
                System.out.println(s.name + " passed");
            } else {
                System.out.println(s.name + " failed");
            }
        }
    }

    public static void main(String[] args) {
        Main book = new Main("Programming 101");
        book.addStudent(new Student("Anna", 78));
        book.addStudent(new Student("Ivan", 59));
        book.addStudent(new Student("Maria", 91));
        book.addStudent(new Student("Pavel", 67));
        book.printReport();
    }
}

class Student {
    String name;
    int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
}