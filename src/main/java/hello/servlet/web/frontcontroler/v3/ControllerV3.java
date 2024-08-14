package hello.servlet.web.frontcontroler.v3;

import hello.servlet.web.frontcontroler.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> ParamMap);
}
