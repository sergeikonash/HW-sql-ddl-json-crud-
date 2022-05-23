package dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * 	1.1 Порядковый номер (int)
 * 	1.2 Имя (Строка размером от 3 до 10 русских символов)
 * 	1.3 возраст (7-17)
 * 	1.4 оценка(0.0-10.0)
 * 	1.5 признак участия в олимпиадах (bool).
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 2L;

    private int id;
    private String name;
    private int age;
    private double score;
    private boolean olympicGamer;

    public Student(int id, String name, int age, double score, boolean olympicGamer) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isOlympicGamer() {
        return olympicGamer;
    }

    public void setOlympicGamer(boolean olympicGamer) {
        this.olympicGamer = olympicGamer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;
        return age == student.age &&
                olympicGamer == student.olympicGamer &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, olympicGamer);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", olympicGamer=" + olympicGamer +
                '}';
    }

    public static class Builder {
        private int id;
        private String name;
        private int age;
        private double score;
        private boolean olympicGamer;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setOlympicGamer(boolean olympicGamer) {
            this.olympicGamer = olympicGamer;
        }

        public static Builder create() {
            return new Builder();
        }

        public Student build() {
            return new Student(id, name, age, score, olympicGamer);
        }
    }
}
