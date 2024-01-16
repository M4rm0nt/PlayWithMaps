import java.util.*;

class Student {
    private String name;
    private int alter;
    private String studiengang;

    public Student(String name, int alter, String studiengang) {
        this.name = name;
        this.alter = alter;
        this.studiengang = studiengang;
    }

    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public String getStudiengang() {
        return studiengang;
    }
}

public class StudentGruppierungBeispiel {
    public static void main(String[] args) {
        List<Student> studenten = new ArrayList<>();
        studenten.add(new Student("Alice", 20, "Informatik"));
        studenten.add(new Student("Bob", 22, "Biologie"));
        studenten.add(new Student("Charlie", 20, "Informatik"));
        studenten.add(new Student("David", 23, "Biologie"));
        studenten.add(new Student("Erich", 22, "Informatik"));
        studenten.add(new Student("Felix", 23, "Biologie"));

        Map<Integer, List<Student>> gruppierteStudentenNachAlter = gruppiereNachAlter(studenten);
        druckeGruppierteStudenten(gruppierteStudentenNachAlter);

        System.out.println("Durchschnittsalter: " + (int) Math.floor(berechneDurchschnittsalter(studenten)));
    }

    private static Map<Integer, List<Student>> gruppiereNachAlter(List<Student> studenten) {
        Map<Integer, List<Student>> gruppierteStudenten = new HashMap<>();
        for (Student student : studenten) {
            gruppierteStudenten.computeIfAbsent(student.getAlter(), k -> new ArrayList<>()).add(student);
        }
        return gruppierteStudenten;
    }

    private static void druckeGruppierteStudenten(Map<Integer, List<Student>> gruppierteStudenten) {
        for (Map.Entry<Integer, List<Student>> eintrag : gruppierteStudenten.entrySet()) {
            System.out.println("Alter: " + eintrag.getKey());
            for (Student student : eintrag.getValue()) {
                System.out.println("Name: " + student.getName() + ", Studiengang: " + student.getStudiengang());
            }
            System.out.println();
        }
    }

    private static double berechneDurchschnittsalter(List<Student> studenten) {
        int gesamtAlter = studenten.stream().mapToInt(Student::getAlter).sum();
        return (double) gesamtAlter / studenten.size();
    }
}
