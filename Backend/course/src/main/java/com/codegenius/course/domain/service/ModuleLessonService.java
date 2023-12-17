package com.codegenius.course.domain.service;

import com.codegenius.course.domain.dto.ModuleLessonUpdateDTO;
import com.codegenius.course.domain.model.CourseModuleModel;
import com.codegenius.course.domain.model.ModuleLessonModel;
import com.codegenius.course.domain.repository.CourseModuleRepository;
import com.codegenius.course.domain.repository.ModuleLessonRepository;
import com.codegenius.course.infra.exception.GlobalExceptionHandler;
import com.codegenius.course.utils.ListaObj;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleLessonService {

    @Autowired
    private ModuleLessonRepository moduleLessonRepository;

    @Autowired
    private CourseModuleRepository courseModuleRepository;

    public ModuleLessonModel getModuleLessonById(UUID moduleLessonId) {
        return moduleLessonRepository.findById(moduleLessonId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lição do módulo não encontrada."));
    }

    public ModuleLessonModel createModuleLesson(UUID moduleId, ModuleLessonModel moduleLesson) {
        Optional<CourseModuleModel> courseModule = courseModuleRepository.findById(moduleId);

        if (courseModule.isEmpty()) {
            // throw exception módulo do curso não encontrado
            return null;
        }

        moduleLesson.setModule(courseModule.get());
        return moduleLessonRepository.save(moduleLesson);
    }

    public List<ModuleLessonModel> getAllModuleLessonByModuleId(UUID moduleId) {
        List<ModuleLessonModel> lessons = moduleLessonRepository.findAllByModule_Id(moduleId);

        return lessons;
    }

    public ListaObj<ModuleLessonModel> getAllModuleLessonByModuleIdCsv(UUID moduleId) {
        List<ModuleLessonModel> moduleLessons = moduleLessonRepository.findAllByModule_Id(moduleId);
        return sortModuleLessonList(moduleLessons, moduleLessons.size());
    }

    public ModuleLessonModel updateModuleLesson(UUID lessonId, ModuleLessonModel newModuleLesson) {
        Optional<ModuleLessonModel> moduleLesson = moduleLessonRepository.findById(lessonId);

        if (moduleLesson.isEmpty()) {
            return null;
        }
        newModuleLesson.setId(lessonId);
        return moduleLessonRepository.save(newModuleLesson);
    }

    public boolean deleteModuleLesson(UUID lessonId) {
        boolean lessonExists = moduleLessonRepository.existsById(lessonId);

        if (lessonExists) {
            moduleLessonRepository.deleteById(lessonId);
            return true;
        }

        return false;
    }

    public List<ModuleLessonUpdateDTO> updateModuleLessons(List<ModuleLessonUpdateDTO> moduleLessonList) {
        if (!moduleLessonList.isEmpty()) {
            for (ModuleLessonUpdateDTO lesson : moduleLessonList) {
                if (lesson.getContentDescription() == null || lesson.getContentDescription().equals("")) {
                    throw new GlobalExceptionHandler.BadRequestException("The content description must not be null or blank.");
                }
                if (lesson.getLessonTitle() == null || lesson.getLessonTitle().equals("")) {
                    throw new GlobalExceptionHandler.BadRequestException("The lesson title must not be null or blank.");
                }

                moduleLessonRepository.update(lesson.getId(), lesson.getLessonOrder(), lesson.getContentDescription(), lesson.getLessonTitle());
            }
            return moduleLessonList;
        }

        return null;
    }

    public ModuleLessonModel getModuleLessonByLessonOrder(UUID moduleId, Integer lessonOrder) {
        List<ModuleLessonModel> lessons = this.moduleLessonRepository.findAllByModule_Id(moduleId);
        ListaObj<ModuleLessonModel> moduleLessonsOrdered = sortModuleLessonList(lessons, lessons.size());

        return binarySearch(moduleLessonsOrdered, lessonOrder);
    }

    public List<ModuleLessonModel> getModuleLessonsOrdered(UUID moduleId) {
        List<ModuleLessonModel> lessons = this.moduleLessonRepository.findAllByModule_Id(moduleId);
        ListaObj<ModuleLessonModel> listaObjOrdered = sortModuleLessonList(lessons, lessons.size());
        List<ModuleLessonModel> moduleLessonsOrdered = new ArrayList<>();

        for (int i = 0; i < listaObjOrdered.getTamanho(); i++) {
            moduleLessonsOrdered.add(listaObjOrdered.getElemento(i));
        }

        return moduleLessonsOrdered;
    }


    // Método para ordenar
    // retorna a ListaObj ordenada
    public ListaObj<ModuleLessonModel> sortModuleLessonList(List<ModuleLessonModel> list, Integer size) {
        ListaObj<ModuleLessonModel> moduleLessonsOrdered = new ListaObj<>(size);

        for (ModuleLessonModel module : list) {
            moduleLessonsOrdered.adiciona(module);
        }

        ModuleLessonModel m = null;
        int indiceMenor = 0;
        for (int i = 0; i < moduleLessonsOrdered.getTamanho() - 1; i++) {
            indiceMenor = i;
            for (int j = i + 1; j < moduleLessonsOrdered.getTamanho(); j++) {
                m = moduleLessonsOrdered.getElemento(j);
                if (m.getLessonOrder() < moduleLessonsOrdered.getElemento(indiceMenor).getLessonOrder()) {
                    indiceMenor = j;
                }
            }
            ModuleLessonModel aux = moduleLessonsOrdered.getElemento(i);
            moduleLessonsOrdered.setElemento(moduleLessonsOrdered.getElemento(indiceMenor), i);
            moduleLessonsOrdered.setElemento(aux, indiceMenor);
        }

        return moduleLessonsOrdered;
    }

    // Método pesquisa binária
    // retorna um ModuleLessonModel caso encontre ou null
    public ModuleLessonModel binarySearch(ListaObj<ModuleLessonModel> list, Integer lessonOrder) {
        int indInf = 0;
        int indSup = list.getTamanho() - 1;
        int meio;

        while (indInf <= indSup) {
            meio = (indInf + indSup) / 2;
            if (list.getElemento(meio).getLessonOrder().equals(lessonOrder)) {
                return list.getElemento(meio);
            } else if (lessonOrder < list.getElemento(meio).getLessonOrder()) {
                indSup = meio - 1;
            } else {
                indInf = meio + 1;
            }
        }
        return null;
    }
}