import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Group implements Commissar {
    private Student[] studentArr = new Student[10];
    private int stIndex = -1;

    public Group() {
    }

    @Override
    public Student[] eighteenAgeOld() {
        Student[] stArr = Arrays.copyOf(studentArr, 10);
        for (int i = 0; i < stArr.length; i++) {
            if (stArr[i] != null) {
                if ((!stArr[i].isSex()) || (stArr[i].getAge() < 18)) {
                    stArr[i] = null;
                }
            }
        }
        Arrays.sort(stArr, Comparator.nullsLast(new LastNameComparator()));
        return stArr;
    }

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (!isStudentInGroup(student)) {
            try {
                if (stIndex == 9) {
                    throw new OutOfStudentIndexException();
                }
                studentArr[++stIndex] = student;
                student.setGroup(this);
            } catch (OutOfStudentIndexException e) {
                e.printStackTrace();
            }
        }
    }

    public void delStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        if (stIndex >= 0) {
            for (int i = 0; i < studentArr.length; i++) {
                if (student.equals(studentArr[i])) {
                    student.setGroup(null);
                    int k = i;
                    while (k < studentArr.length - 1) {
                        studentArr[k] = studentArr[k + 1];
                        studentArr[k + 1] = null;
                        k++;
                    }
                    break;
                }
            }
        }
    }

    public Student findStudentbySecondName(String secondName) {
        Student tmp = null;
        for (Student st : studentArr) {
            if (st != null) {
                if (secondName.equals(st.getSecondName())) {
                    tmp = st;
                    break;
                }
            }
        }
        return tmp;
    }

    public Student findStudentByZachNumber (Long zachNumber) {
        Student tmp = null;
        for (Student st : studentArr) {
            if (st != null) {
                if (zachNumber.equals(st.getZach())) {
                    tmp = st;
                    break;
                }
            }
        }
        return tmp;
    }

    public boolean isStudentInGroup(Student student) {
        if (student == null) {
            throw new IllegalArgumentException();
        }
        boolean retVal = false;
        for (Student st : studentArr) {
            if (student.equals(st)) {
                retVal = true;
                break;
            }
        }
        return retVal;
    }

    public void interactiveAdd() {
        try {
            Scanner sc = new Scanner(System.in);
            String choise;

            do {
                System.out.println(this);
                System.out.println("Программа работы с базой студентов");
                System.out.println("Выберите нужное действие:");
                System.out.println();
                System.out.println("1. Добавить студента");
                System.out.println("2. Удалить студента");

                choise = sc.nextLine();

                switch (choise) {
                    case "1":

                        System.out.println("Введите имя студента");
                        String fName = sc.nextLine();

                        System.out.println("Введите фамилию студента");
                        String sName = sc.nextLine();

                        System.out.println("Введите пол студента");
                        String sSex = sc.nextLine();
                        boolean sex = false;
                        switch (sSex) {
                            case "муж":
                                sex = true;
                                break;

                            case "жен":
                                sex = false;
                                break;
                        }

                        System.out.println("Введите возраст студента");
                        int age = sc.nextInt();

                        System.out.println("Введите номер зачетки");
                        long zach = sc.nextLong();

                        Student student = new Student(sName, fName, age, sex, zach);
                        addStudent(student);
                        break;

                    case "2":
                        System.out.println("Введите номер зачетной нижки студента:");
                        this.delStudent(findStudentByZachNumber(sc.nextLong()));
                }
            } while (!sc.nextLine().equals("Exit"));
        } catch (InputMismatchException e) {
            System.out.println("Вы ввели неверное значение!");
        }
    }

    public void sortByLastName() {
        Arrays.sort(studentArr, Comparator.nullsLast(new LastNameComparator()));
    }

    public void sortByZachNumber() {
        Arrays.sort(studentArr, Comparator.nullsLast(new ZachComparator()));
    }

    public void sortByFirstName() {
        Arrays.sort(studentArr, Comparator.nullsLast(new FirstNameComparator()));
    }

    public Student[] getStudentArr() {
        return studentArr;
    }

    public void setStudentArr(Student[] studentArr) {
        this.studentArr = studentArr;
    }

    @Override
    public String toString() {
        String retVal = "Список студентов:" + '\n';
        for (Student st : studentArr) {
            retVal += (st + "\n");
        }
        return retVal;
    }
}
