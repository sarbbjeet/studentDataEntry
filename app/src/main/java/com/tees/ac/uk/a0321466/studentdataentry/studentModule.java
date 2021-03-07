package com.tees.ac.uk.a0321466.studentdataentry;

public class studentModule {
    private int id;
    private String name;
    private int age;
    private Long studentId;

    //constructor without parameters
    public studentModule() {
    }

    //constructor with all parameters
    public studentModule(int id, String name, int age, Long studentId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.studentId = studentId;
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

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "studentModule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", studentId=" + studentId +
                '}';
    }
}
