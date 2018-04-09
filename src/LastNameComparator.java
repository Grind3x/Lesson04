import java.util.Comparator;

public class LastNameComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            return o1.getSecondName().compareTo(o2.getSecondName());
        }
    }


}


