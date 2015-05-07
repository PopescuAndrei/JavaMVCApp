package ro.teamnet.zth.web;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.ParameterAnnotation;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;
import ro.teamnet.zth.web.exceptions.DispatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrei on 5/6/2015.
 */
public class MyDispatcherServlet extends HttpServlet {
    Map<String, MethodAttributes> allowedMethods = new HashMap<String, MethodAttributes>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Delegate to application controller */
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*Delegate to application controller */
        dispatchReply("POST", req, resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.teamnet.zth.app.controller");
            allowedMethods = getAllowedRequestMethods(classes);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, MethodAttributes> getAllowedRequestMethods(Iterable<Class> classes) {
        for (Class controller : classes) {
            if (controller.isAnnotationPresent(MyController.class)) {
                MyController myControllerAnnotation = (MyController) controller.getAnnotation(MyController.class);
                String controllerUrlPath = myControllerAnnotation.urlPath();
                Method[] controllerMethods = controller.getMethods();
                for (Method controllerMethod : controllerMethods) {
                    if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                        MyRequestMethod myMethodAnnotation = (MyRequestMethod) controllerMethod.getAnnotation(MyRequestMethod.class);
                        String key = controllerUrlPath + myMethodAnnotation.urlPath();
                        MethodAttributes methodAttributes = new MethodAttributes();
                        methodAttributes.setControllerClass(controller.getName());

                        methodAttributes.setMethodType(myMethodAnnotation.methodType());
                        methodAttributes.setMethodName(controllerMethod.getName());
                        if (controllerMethod.getParameterTypes().length > 0) {
                            methodAttributes.setMethodParams(controllerMethod.getParameterTypes());
                        }
                        allowedMethods.put(key, methodAttributes);
                    }
                }
            }
        }

        return allowedMethods;
    }

    protected void dispatchReply(String method, HttpServletRequest req, HttpServletResponse resp) {
        try {
            Object response = dispatch(req, resp);
            reply(response, req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DispatchException de) {
            try {
                sendException(de, resp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    protected Object dispatch(HttpServletRequest req, HttpServletResponse resp) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String pathInfo = req.getPathInfo();
        MethodAttributes methodAttributes = allowedMethods.get(pathInfo);
        if (methodAttributes != null) {
            /*Apel application Controller*/
            Class<?> controller = Class.forName(methodAttributes.getControllerClass());
            Object o = controller.newInstance();
            Method method = controller.getMethod(methodAttributes.getMethodName(), methodAttributes.getMethodParams());
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            if (parameterAnnotations.length > 0) {
                List<String> paramValues = new ArrayList<String>();
                ParameterAnnotation annotation = (ParameterAnnotation) parameterAnnotations[0][0];
                paramValues.add(req.getParameter(annotation.parameterName()));
                return method.invoke(o, (String[]) paramValues.toArray(new String[0]));
            } else {
                return method.invoke(o);
            }
        }
        throw new DispatchException();
    }

    protected void reply(Object obj, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.write(mapper.writeValueAsString(obj));
    }

    private void sendException(DispatchException de, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("The URL does not exists");
    }


}
