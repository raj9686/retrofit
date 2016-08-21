
package com.example.raj.mytest.Model;

public class RestResponse {
    private Result result;

    private String[] messages;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String[] getMessages() {
        return messages;
    }

    public void setMessages(String[] messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ClassPojo [result = " + result + ", messages = " + messages + "]";
    }
}