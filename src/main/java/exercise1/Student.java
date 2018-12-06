package exercise1;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */

    private String name;
    private String registrationNumber;
    private HashMap<String,Integer> listOfCourses = new HashMap<>();
    private Set<String> listRegis;

    public Student(String name, String registrationNumber){
        if(name == null || registrationNumber == null)
        {
            throw new NullPointerException();
        }
        else
        {
            /*boolean ok = true;
            for (String registre : listRegis)
            {
                if (registre == registrationNumber)
                {
                    ok = false;
                }
            }
            if (ok==false)
            {
                throw new DuplicateStudentException(registrationNumber);
            }
            else
            {*/
                this.name = name;
                this.registrationNumber = registrationNumber;
                //this.listRegis.add(registrationNumber);
            //}
        }

    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {
        if (score <0||score>20)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            if (course == null)
            {
                throw new NullPointerException();
            }
            else
            {
                this.listOfCourses.put(course,score);
            }
        }


    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public OptionalInt getScore(String course) {
        Integer nullableScore = this.listOfCourses.get(course);
        return nullableScore != null ? OptionalInt.of(nullableScore) : OptionalInt.empty();
    }

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {
        Collection<Integer> listOfScores = this.listOfCourses.values();
        double averageS = 0;
        int i = 0;
        for (int scores : listOfScores )
        {
            averageS = averageS + scores;
            i = i+1;
        }
        if (i == 0)
        {
           return 0;
        }
        else {
            return averageS / i;
        }

        /*
        return listOfCourses.values().stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0)
         */
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {
        return listOfCourses.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//prend le couple K,V et trie par valeur décroissante
            .map(Map.Entry::getKey) // on prend la clé
            .findFirst(); //prend le premier, car c'est trié par ordre décroissant et on aura le meilleur
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        return listOfCourses.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .mapToInt(Map.Entry::getValue)
            .findFirst()
            .orElse(0);
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
        return listOfCourses.entrySet().stream()
            .filter(map -> map.getValue() <= 12)
            .map(Map.Entry::getKey)
            .collect(Collectors.toSet());
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        double average = this.averageScore();
        Set<String> failed = this.failedCourses();
        int numberFailed = failed.size();

        if(average >= 12 && numberFailed < 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses()
    {
        return listOfCourses.keySet().stream()
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public String getName() {
        return this.name;
    }

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
