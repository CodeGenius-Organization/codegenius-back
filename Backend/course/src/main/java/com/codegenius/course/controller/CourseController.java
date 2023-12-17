package com.codegenius.course.controller;

import com.codegenius.course.domain.dto.CourseCreationDTO;
import com.codegenius.course.domain.dto.CourseCsvDTO;
import com.codegenius.course.domain.model.CourseModel;
import com.codegenius.course.domain.service.CourseService;
import com.codegenius.course.domain.utils.Fila;
import com.codegenius.course.domain.utils.Pilha;
import com.codegenius.course.infra.exception.GlobalExceptionHandler;
import com.codegenius.course.utils.Arquivo;
import com.codegenius.course.utils.GerenciadorDeArquivosCourseCsv;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:8181"})
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //region GET
    @GetMapping("/")
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        return ResponseEntity.status(200).body(this.courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseModel> getCourseById(@PathVariable UUID courseId) {
        return ResponseEntity.status(200).body(this.courseService.getCourseById(courseId));
    }

    @GetMapping(value = "/image/{courseId}", produces = "image/**")
    public ResponseEntity<byte[]> getCourseImageById(@PathVariable UUID courseId) {
        return ResponseEntity.status(200).body(courseService.getCourseImageById(courseId));
    }


    @GetMapping("/available")
    public ResponseEntity<List<CourseModel>> getAvailableCourses() {
        return ResponseEntity.status(200).body(this.courseService.getAvailableCourses());
    }

    @GetMapping("/available/{keyword}")
    public ResponseEntity<List<CourseModel>> getCoursesAvailableByKeyword(@PathVariable String keyword) {
        return ResponseEntity.status(200).body(this.courseService.getCoursesAvailableByKeyword(keyword));
    }

    // @GetMapping("/popularity")
    // public ResponseEntity<List<Course>> getCoursesByPopularity() {
    //     return ResponseEntity.status(200).body(this.courseService.getCoursesByPopularity());
    // }

    @GetMapping("/language/{languageId}")
    public ResponseEntity<List<CourseModel>> getCoursesByLanguage(@PathVariable UUID languageId) {
        return ResponseEntity.status(200).body(this.courseService.getCoursesByLanguage(languageId));
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<CourseModel>> getCoursesByCategory(@PathVariable String categoryName) {
        return ResponseEntity.status(200).body(this.courseService.getCoursesByCategory(categoryName));
    }

    @GetMapping("/exportar-csv")
    public ResponseEntity<List<CourseCsvDTO>> getCourseCsv(){
        List<CourseCsvDTO> lista = courseService.getCourseCsv();

        if(lista.isEmpty()){
            return ResponseEntity.status(204).build();
        }
        GerenciadorDeArquivosCourseCsv.gravaArquivoCsv(lista, "Cursos");
        return ResponseEntity.status(200).build();
    }
    //endregion

    //region POST
    @PostMapping("/")
    public ResponseEntity<CourseModel> createCourse(@RequestBody @Valid CourseCreationDTO courseCreationDTO) {
        return ResponseEntity.status(201).body(this.courseService.createCourse(courseCreationDTO));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<List<CourseModel>> postCourseCadastrarCsv(@RequestBody Arquivo arquivo) {
        List<CourseModel> guardarArquivo = GerenciadorDeArquivosCourseCsv.importarArquivoCsv(arquivo.getNomeArq());

        return ResponseEntity.status(201).body(this.courseService.createCourses(guardarArquivo));
    }
    //endregion

    //region PUT
    @PutMapping("/{courseId}")
    public ResponseEntity<CourseModel> updateCourse(@RequestBody @Valid CourseCreationDTO course, @PathVariable UUID courseId) {
        if(!courseService.existsById(courseId)) {
            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(200).body(courseService.updateCourse(courseId, course));
    }
    //endregion

    //region DELETE
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable UUID courseId) {
        this.courseService.deleteCourseById(courseId);

        return ResponseEntity.status(200).build();
    }
    //endregion

    //region PATCH
    @PatchMapping(value = "/image/{courseId}", consumes = "image/**")
    public ResponseEntity<Integer> updateCourseImage(@PathVariable UUID courseId, @RequestBody byte[] image) {
        courseService.updateCourseImage(courseId, image);
        return ResponseEntity.status(200).build();
    }
    //endregion

    @GetMapping(value = "/ordem/{option}")
    public ResponseEntity<?> pilha(@PathVariable String option) {
        if (!option.equals("drs") && !option.equals("crs")) {
            throw new GlobalExceptionHandler.BadRequestException("invalid option");
        }

        Object result = courseService.pilhaOuFila(option);

        if (result instanceof Pilha) {
            Pilha<CourseModel> pilha = (Pilha<CourseModel>) result;
            return pilha.isEmpty()
                    ? ResponseEntity.status(204).build()
                    : ResponseEntity.status(200).body(pilha);
        } else if (result instanceof Fila) {
            Fila<CourseModel> fila = (Fila<CourseModel>) result;
            return fila.isEmpty()
                    ? ResponseEntity.status(204).build()
                    : ResponseEntity.status(200).body(fila);
        }

        return ResponseEntity.status(400).build();
    }
}
