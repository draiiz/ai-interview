package i.mehesz.Interview.domain;

import java.util.List;

public class Response {

    private String result;
    private List<ResultResponse> questions;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ResultResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ResultResponse> questions) {
        this.questions = questions;
    }
}
