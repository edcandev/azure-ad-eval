package com.edic.azureadeval.models;

public class Answer {
    private Integer question;
    private Integer section;
    private String answer;
    private String correctAnswer;

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion(Integer question) {
        this.question = question;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Answer() { }

    public Answer(Integer question, Integer section, String answer, String correctAnswer) {
        this.question = question;
        this.section = section;
        this.answer = answer;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "question=" + question +
                ", section=" + section +
                ", answer='" + answer + '\'' +
                ", 'correctAnswer'" + correctAnswer + '\'' +
                '}';
    }
}

/*
{
"answers":[{"question":1,"section":1,"answer":"Completamente Deacuerdo"
 */
