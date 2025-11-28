package i.mehesz.Interview.controller;

import i.mehesz.Interview.service.EvaluationService;
import i.mehesz.Interview.domain.QuestionRequest;
import i.mehesz.Interview.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping("api/evaluation")
    public Response finalAnswer(@RequestBody List<QuestionRequest> answer){
        return evaluationService.evaluateAllAnswers(answer);
    }
}
