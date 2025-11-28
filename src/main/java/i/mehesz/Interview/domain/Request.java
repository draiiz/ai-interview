package i.mehesz.Interview.domain;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private List<QuestionRequest> questions = new ArrayList<>();

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionRequest> questions) {
        this.questions = questions;
    }

    private void addToList(QuestionRequest questionRequest){
        questions.add(questionRequest);
    }
}
