package hello.servlet.web.frontcontroler.v1;

import hello.servlet.web.frontcontroler.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroler.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroler.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // URL 경로와 그 경로를 처리할 컨트롤러 객체(~ControllerV1)를 맵핑
    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();

        // URL 매핑 정보에서 컨트롤러 조회
        // 다형성으로 MemberListControllerV1, MemberSaveControllerV1, MemberFormControllerV1의 부모 타입 ControllerV1으로 담을 수 있다.
        ControllerV1 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 오버라이드 된 메소드 호출 (MemberListControllerV1, MemberSaveControllerV1, MemberFormControllerV1의 process 메소드 (기존 MVC 패턴과 동일)
        controller.process(request, response);
    }
}
