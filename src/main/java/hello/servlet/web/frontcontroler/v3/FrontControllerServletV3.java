package hello.servlet.web.frontcontroler.v3;

import hello.servlet.web.frontcontroler.ModelView;
import hello.servlet.web.frontcontroler.MyView;
import hello.servlet.web.frontcontroler.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroler.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroler.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        // 컨트롤러에서 받은 논리 이름으로 실제 물리 위치의 이름을 만들고 MyView를 반환하는 로직 -> 이후에는 render

        //paramMap으로 뷰의 논리 이름 얻음
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 뷰의 논리 이름 (Ex. new-form)
        // 실제 물리 뷰 경로로 변경, 그리고 실제 물리 경로가 있는 MyView 객체를 반환
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // HttpServletRequest에 있는 파라미터를 전부 paramMap에 저장
    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
