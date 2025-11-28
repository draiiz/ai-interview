package i.mehesz.Interview.service;

import i.mehesz.Interview.domain.QuestionRequest;
import i.mehesz.Interview.domain.Response;
import i.mehesz.Interview.domain.ResultResponse;
import i.mehesz.Interview.ai.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EvaluationService {

    private static final String PROMPT = "Java álláinterjún vagyunk, elemezd ki, hogy a következő kérdésre mennyire jó az adott válasz, "
            + "fínomíts rajta ha lehet és értékeld 1-től 10-ig. "
            + "Kérdés: [question] ? "
            + "Válasz: [answer].";

    private static final String PROMPTPOINT = "Nem kérek szöveges választ, csak add vissza hány pontot érne ez a válasz 1-től 10-es skálán";

    @Autowired
    private GeminiService geminiService;

    public String evaluateTextCorrection(String question, String answer) {
        String finalPrompt = PROMPT.replace("[question]", question).replace("[answer]",answer);
        return geminiService.generateText(finalPrompt);
    }

    public Integer evaluatePoint(String question, String answer) {
        String finalPrompt = PROMPT.replace("[question]", question).replace("[answer]",answer) + " " + PROMPTPOINT;
        return Integer.parseInt(geminiService.generateText(finalPrompt));
    }

    public Response evaluateAllAnswers(List<QuestionRequest> allAnswers) {
        Response response = new Response();
        int resultPoint =0;
        List<ResultResponse> temp = new ArrayList<>();
        for (int i = 0; i < allAnswers.size(); i++) {
            ResultResponse result = new ResultResponse();
            result.setQuestion(allAnswers.get(i).getQuestion());
            result.setCorrection(evaluateTextCorrection(allAnswers.get(i).getQuestion(), allAnswers.get(i).getAnswer()));
            result.setPoints(evaluatePoint(allAnswers.get(i).getQuestion(), allAnswers.get(i).getAnswer()));
            temp.add(result);
            resultPoint += result.getPoints();
        }
        if(resultPoint/temp.size() >= 7 )
            response.setResult("sikerült");
        else
            response.setResult("nem sikerült");

        response.setQuestions(temp);

        return response;
    }
}
