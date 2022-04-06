package com.scholarships.service;

import com.scholarships.exception.StudentNotFoundException;
import com.scholarships.model.Student;
import com.scholarships.repository.StudentRepository;
import com.scholarships.representation.StudentRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentService {
    private StudentRepository repository;
    private Random rand = new Random();
    @Autowired
    public StudentService(StudentRepository repository)
    {
        this.repository = repository;
    }

    public void saveStudent(StudentRO studentRO)
    {
        Student student = repository.getStudentByStudentId(studentRO.getStudentId());
        if(student == null)
        {
            student = new Student(studentRO);
        }
        student.setDiscount(rand.nextInt(101));

        repository.save(student);
    }

    public int getStudentDiscount(long studentId) throws Exception {
        Student student = repository.getStudentByStudentId(studentId);
        if(student == null)
        {
            throw new StudentNotFoundException();
        }

        return student.getDiscount();
    }

    public String getStudentDiscountWithCSVFormat(long studentId) throws Exception {
        Student student = repository.getStudentByStudentId(studentId);
        if(student == null)
        {
            throw new StudentNotFoundException();
        }

        return studentId + "," + student.getDiscount();
    }
    public Student updateStudent(Student student){
        // TODO: Pending to update student entity with RO info
        //System.out.println(student);
        student.setStudentId(student.getId());
        student.setDiscount(rand.nextInt(101));
        return repository.save(student);
    }
}
