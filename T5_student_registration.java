import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int id;
    private String name;
    private List<Course> registeredCourses;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (registeredCourses.size() < 5) { // Limit to 5 courses per student
            registeredCourses.add(course);
            System.out.println(name + " registered for " + course.getTitle());
        } else {
            System.out.println(name + " has reached the maximum number of registered courses.");
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.contains(course)) {
            registeredCourses.remove(course);
            System.out.println(name + " dropped " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }
}

public class T5_student_registration {
    public static void main(String[] args) {
        Map<String, Course> courses = new HashMap<>();
        courses.put("CS101", new Course("CS101", "Introduction to Programming", "Basic programming concepts", 30, "Mon/Wed 9:00 AM"));
        courses.put("MATH202", new Course("MATH202", "Linear Algebra", "Vector spaces and matrices", 25, "Tue/Thu 11:00 AM"));

        List<Student> students = new ArrayList<>();
        students.add(new Student(1001, "Alice"));
        students.add(new Student(1002, "Bob"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Registration System");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Available Courses:");
                    for (Course course : courses.values()) {
                        System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getSchedule() + ")");
                        System.out.println("Capacity: " + course.getCapacity() + " / Registered: " + getRegisteredCount(course, students));
                        System.out.println("Description: " + course.getDescription());
                        System.out.println();
                    }
                    break;
                case 2:
                    System.out.print("Enter your student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Student student = findStudent(studentId, students);
                    if (student != null) {
                        System.out.println("Available Courses:");
                        for (Course course : courses.values()) {
                            if (!student.getRegisteredCourses().contains(course)) {
                                System.out.println(course.getCode() + " - " + course.getTitle());
                            }
                        }
                        System.out.print("Enter the course code to register: ");
                        String courseCode = scanner.nextLine();
                        Course selectedCourse = courses.get(courseCode);
                        if (selectedCourse != null) {
                            student.registerForCourse(selectedCourse);
                        } else {
                            System.out.println("Invalid course code.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter your student ID: ");
                    int studentIdDrop = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    Student studentDrop = findStudent(studentIdDrop, students);
                    if (studentDrop != null) {
                        System.out.println("Registered Courses:");
                        List<Course> registeredCourses = studentDrop.getRegisteredCourses();
                        for (Course course : registeredCourses) {
                            System.out.println(course.getCode() + " - " + course.getTitle());
                        }
                        System.out.print("Enter the course code to drop: ");
                        String courseCodeDrop = scanner.nextLine();
                        Course selectedCourseDrop = courses.get(courseCodeDrop);
                        if (selectedCourseDrop != null) {
                            studentDrop.dropCourse(selectedCourseDrop);
                        } else {
                            System.out.println("Invalid course code.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static Student findStudent(int studentId, List<Student> students) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static int getRegisteredCount(Course course, List<Student> students) {
        int count = 0;
        for (Student student : students) {
            if (student.getRegisteredCourses().contains(course)) {
                count++;
            }
        }
        return count;
    }
}
