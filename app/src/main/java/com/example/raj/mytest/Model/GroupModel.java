
package com.example.raj.mytest.Model;


public class GroupModel {
    private RestResponse RestResponse;

    public RestResponse getRestResponse() {
        return RestResponse;
    }

    public void setRestResponse(RestResponse RestResponse) {
        this.RestResponse = RestResponse;
    }

    @Override
    public String toString() {
        return "ClassPojo [RestResponse = " + RestResponse + "]";
    }
}