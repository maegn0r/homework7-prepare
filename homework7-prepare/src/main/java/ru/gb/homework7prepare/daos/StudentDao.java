package ru.gb.homework7prepare.daos;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.homework7prepare.entities.Student;

@Repository
public interface StudentDao extends JpaRepository<Student, Long> {
}