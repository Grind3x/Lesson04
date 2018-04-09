import java.util.Comparator;

public class ZachComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        } else {
            Long zchOne = o1.getZach();
            Long zchTwo = o2.getZach();
            return zchOne.compareTo(zchTwo);
        }
    }
}
