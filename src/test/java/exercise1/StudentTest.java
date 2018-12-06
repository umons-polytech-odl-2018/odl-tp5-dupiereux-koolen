package exercise1;

import org.junit.Test;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentTest {
    @Test
    public void constructor_with_valid_parameters() {
        Student student = new Student("Stannis Baratheon", "012345");
        assertThat(student.getName()).isEqualTo("Stannis Baratheon");
        assertThat(student.getRegistrationNumber()).isEqualTo("012345");
    }

    @Test
    public void constructor_with_null_name() {
        assertThatThrownBy(() -> new Student(null, "012335"))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void constructor_with_null_registration_number() {
        assertThatThrownBy(() -> new Student("Thyrion Lannister", null))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void setScore_with_less_than_0() {
        Student jonSnow = new Student("Jon Snow", "998877");
        assertThatThrownBy(() -> jonSnow.setScore("temperance", -5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void setScore_with_more_than_20() {
        Student tywinLannister = new Student("Tywin Lannister", "002345");
        assertThatThrownBy(() -> tywinLannister.setScore("accountancy", 23))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void setScore_with_null_course() {
        Student gendry = new Student("Gendry", "225533");
        assertThatThrownBy(() -> gendry.setScore(null, 18))
            .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void getScore_with_scored_course() {
        Student theonGreyjoy = new Student("Theon Greyjoy", "123654");
        theonGreyjoy.setScore("loyalty", 2);
        assertThat(theonGreyjoy.getScore("loyalty")).hasValue(2);
    }

    @Test
    public void getScore_with_unknown_course() {
        Student sansaStark = new Student("Sansa Stark", "425163");
        sansaStark.setScore("sewing", 20);
        assertThat(sansaStark.getScore("politics")).isEmpty();
    }

    @Test
    public void averageScore_with_many_courses() {
        Student daenarysTargaryen = new Student("Daenarys Targaryen", "028743");
        daenarysTargaryen.setScore("leadership", 18);
        daenarysTargaryen.setScore("test",15);
        assertThat(daenarysTargaryen.averageScore()).isEqualTo(16.5);

    }

    @Test
    public void averageScore_with_no_course() {
        Student lorasTyrell = new Student("Loras Tyrell", "102039");
        assertThat(lorasTyrell.averageScore()).isEqualTo(0.0);
    }

    @Test
    public void bestCourse() {
        Student cerseiLannister = new Student("Cersei Lannister", "928657");
        cerseiLannister.setScore("parenting", 5);
        cerseiLannister.setScore("politics", 12);
        cerseiLannister.setScore("poisoning", 19);
        assertThat(cerseiLannister.bestCourse()).hasValue("poisoning");
    }

    @Test
    public void bestScore() {
        Student aryaStark = new Student("Arya Stark", "987654");
        aryaStark.setScore("fencing", 14);
        aryaStark.setScore("sewing", 1);
        aryaStark.setScore("surviving", 19);
        assertThat(aryaStark.bestScore()).isEqualTo(19);
    }

    @Test
    public void failedCourses() {
        Student joffreyBaratheon = new Student("Joffrey Baratheon", "208733");
        joffreyBaratheon.setScore("ruling", 5);
        joffreyBaratheon.setScore("fighting", 2);
        joffreyBaratheon.setScore("shouting", 18);
        assertThat(joffreyBaratheon.failedCourses()).containsExactly("ruling", "fighting");
    }

    @Test
    public void isSuccessful_with_average_score_12_and_2_failures() {
        Student davosSeaworth = new Student("Davos Seaworth", "123123");
        davosSeaworth.setScore("smuggling", 17);
        davosSeaworth.setScore("loyalty", 19);
        davosSeaworth.setScore("following rules", 8);
        davosSeaworth.setScore("swimming", 4);
        assertThat(davosSeaworth.isSuccessful()).isTrue();
    }

    @Test
    public void isSuccessful_with_average_score_10_and_1_failure() {
        Student jeorMormont = new Student("Jeor Mormont", "123123");
        jeorMormont.setScore("parenting", 0);
        jeorMormont.setScore("hunting", 18);
        jeorMormont.setScore("sharing", 12);
        assertThat(jeorMormont.isSuccessful()).isFalse();
    }

    @Test
    public void isSuccessful_with_average_score_14_and_3_failures() {
        Student samwellTarly = new Student("Samwell Tarly", "334455");
        samwellTarly.setScore("fighting", 11);
        samwellTarly.setScore("reading", 19);
        samwellTarly.setScore("fencing", 11);
        samwellTarly.setScore("loyalty", 20);
        samwellTarly.setScore("running", 9);
        assertThat(samwellTarly.isSuccessful()).isFalse();
    }

    @Test
    public void attendedCourses() {
        Student melisandre = new Student("Melisandre", "666999");
        melisandre.setScore("magic", 16);
        melisandre.setScore("dressing", 12);
        melisandre.setScore("prophetic visions", 13);
        assertThat(melisandre.attendedCourses()).containsExactly("dressing", "magic", "prophetic visions");
    }

    @Test
    public void attendedCourses_with_no_course() {
        Student gilly = new Student("Gilly", "666999");
        assertThat(gilly.attendedCourses()).isEmpty();
    }

    @Test
    public void getName() {
        Student shae = new Student("Shae", "554499");
        assertThat(shae.getName()).isEqualTo("Shae");
    }

    @Test
    public void getRegistrationNumber() {
        Student hodor = new Student("Hodor", "000321");
        assertThat(hodor.getRegistrationNumber()).isEqualTo("000321");
    }

    @Test
    public void equals_and_hashCode_with_same_name() {
        Student student1 = new Student("student", "123456");
        Student student2 = new Student("student", "456213");
        assertThat(student1).isNotEqualTo(student2);
        assertThat(student1.hashCode()).isNotEqualTo(student2.hashCode());
    }

    @Test
    public void equals_and_hashCode_with_same_registration_number() {
        Student student1 = new Student("student1", "123456");
        Student student2 = new Student("student1", "123456");
        assertThat(student1).isEqualTo(student2);
        assertThat(student1.hashCode()).isEqualTo(student2.hashCode());
    }

}
