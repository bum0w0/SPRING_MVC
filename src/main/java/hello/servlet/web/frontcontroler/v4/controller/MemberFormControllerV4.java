package hello.servlet.web.frontcontroler.v4.controller;

import hello.servlet.web.frontcontroler.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return
     */

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}

