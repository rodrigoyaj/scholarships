package com.scholarships.resource;

import com.scholarships.model.Student;
import com.scholarships.repository.StudentRepository;
import com.scholarships.representation.DiscountRO;
import com.scholarships.representation.StudentRO;
import com.scholarships.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/student")
public class StudentResource{

    private StudentService service;

    @Autowired
    public StudentResource(StudentService service)
    {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> saveStudent(@RequestBody StudentRO student)
    {
        service.saveStudent(student);
        return new ResponseEntity<>("Student saved in scholarship system.", HttpStatus.OK);
    }

    @GetMapping("{studentId}/discount")
    public ResponseEntity<DiscountRO> getStudentDiscount(@PathVariable long studentId) throws Exception {
        int discount = service.getStudentDiscount(studentId);

        DiscountRO discountRO = new DiscountRO();
        discountRO.setStudentId(studentId);
        discountRO.setDiscount(discount);

        return new ResponseEntity<DiscountRO>(discountRO, HttpStatus.OK);
    }

    @GetMapping(value = "{studentId}/discountwithcsvformat", produces = "text/customcsv")
    public String getStudentDiscountWithCSVFormat(@PathVariable long studentId) throws Exception {
        return service.getStudentDiscountWithCSVFormat(studentId);
    }
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student studentRO){
        service.updateStudent(studentRO);
        return new ResponseEntity<Student>(studentRO,HttpStatus.OK);
    }
}