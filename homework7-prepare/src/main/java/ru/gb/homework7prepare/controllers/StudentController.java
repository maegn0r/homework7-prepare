package ru.gb.homework7prepare.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.homework7prepare.entities.Student;
import ru.gb.homework7prepare.services.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @GetMapping
    public String getAllStudents(Model model){
        List<Student> students = service.getAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/{id}")
    public String deleteById(Model model, @PathVariable Long id) {
        ResponseEntity<String> response = service.deleteById(id);

        if(response.getStatusCode().equals(HttpStatus.OK)) return "redirect:";
        String message = response.getBody();
        model.addAttribute("error", message);
        System.out.println(response.getBody());

        return "error";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Student changeStudent = service.findById(id).orElseThrow(() -> new RuntimeException("Student with id: " + id + " doesn't exists (for edit)"));
            model.addAttribute("change_student", changeStudent);
            return "edit_student";
        }catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/confirm_edit")
    public String confirmEditForm(@ModelAttribute Student changeStudent) {
        service.saveOrUpdate(changeStudent);
        return "redirect:/";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("new_student", new Student());
        return "create_student";
    }

    @PostMapping("/confirm_create")
    public String confirmCreateForm(@ModelAttribute Student newStudent) {
        newStudent.setId(null);
        service.saveOrUpdate(newStudent);
        return "redirect:/";
    }

}
