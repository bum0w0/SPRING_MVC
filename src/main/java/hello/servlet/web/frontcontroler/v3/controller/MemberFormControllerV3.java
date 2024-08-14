package hello.servlet.web.frontcontroler.v3.controller;

import hello.servlet.web.frontcontroler.ModelView;
import hello.servlet.web.frontcontroler.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 뷰의 논리 이름 반환 (실제 물리 위치의 이름은 프론트 컨트롤러에서 처리)
        return new ModelView("new-form");
    }
}
