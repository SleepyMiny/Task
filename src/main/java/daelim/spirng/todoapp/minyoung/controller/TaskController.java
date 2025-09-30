package daelim.spirng.todoapp.minyoung.controller;

import daelim.spirng.todoapp.minyoung.dto.TaskRequest;
import daelim.spirng.todoapp.minyoung.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    @GetMapping
    public String list(Model model){
        model.addAttribute("tasks", service.findAll());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newForm(Model model){
        model.addAttribute("task", new TaskRequest());
        model.addAttribute("mode", "create");
        return "tasks/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("task") TaskRequest form,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("mode", "create");
            return "tasks/form";
        }
        service.create(form);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        var t = service.findById(id);
        var form = new TaskRequest();
        form.setTitle(t.getTitle());
        form.setDescription(t.getDescription());
        form.setCompleted(t.isCompleted());
        model.addAttribute("task", form);
        model.addAttribute("taskId", id);
        model.addAttribute("mode", "edit");
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("task") TaskRequest form,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("mode", "edit");
            model.addAttribute("taskId", id);
            return "tasks/form";
        }
        service.update(id, form);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/tasks";
    }
}
