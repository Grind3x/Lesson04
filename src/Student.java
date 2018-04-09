import java.util.Comparator;
import java.util.Objects;

public class Student extends Human {
        private Group group;
    private long zach;

    public Student() {

    }

    public Student(String secondName, String firstName, int age, boolean sex, long zach) {
        super(secondName, firstName, age, sex);
        this.zach = zach;
    }

    public long getZach() {
        return zach;
    }

    public void setZach(long zach) {
        this.zach = zach;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;


        return Objects.equals(this.getFirstName(), student.getFirstName()) &
                Objects.equals(this.getSecondName(), student.getSecondName()) &
                Objects.equals(this.getAge(), student.getAge()) &
                Objects.equals(zach, student.zach) &
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(zach, group);
    }

    @Override
    public String toString() {
        return super.getSecondName() + " " + super.getFirstName() + ", зч №" + this.getZach() ;
    }
}
