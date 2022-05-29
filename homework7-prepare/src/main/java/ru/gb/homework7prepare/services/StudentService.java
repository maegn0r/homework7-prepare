package ru.gb.homework7prepare.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gb.homework7prepare.daos.StudentDao;
import ru.gb.homework7prepare.entities.Student;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentDao repository;

    public List<Student> getAll(){
        return repository.findAll();
    }

    public ResponseEntity<String> deleteById(Long id) {
        try{
            repository.deleteById(id);
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public void saveOrUpdate(Student changeStudent) {
        repository.save(changeStudent);
    }
}
