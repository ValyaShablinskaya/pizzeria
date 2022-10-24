//package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;
//
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaInfoService;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.PizzaInfoStorage;
//import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;
//import org.codehaus.jackson.map.ObjectMapper;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//@WebServlet(name = "PizzaInformationServlet", urlPatterns = "/pizzaInformation")
//public class PizzaInformationServlet extends HttpServlet {
//    private final PizzaInfoStorage pizzaInfoStorage = new PizzaInfoStorage();
//    private final PizzaInfoService service = new PizzaInfoService(pizzaInfoStorage);
//    private final ObjectMapper mapper = new ObjectMapper();
//    private final Converter converter = new Converter();
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        List<PizzaInfo> pizzaInfo = service.findAll();
//        PrintWriter writer = resp.getWriter();
//        writer.write(mapper.writeValueAsString(pizzaInfo));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        BufferedReader bufferedReader = req.getReader();
//        String jsonToString = converter.convertToString(bufferedReader);
//        PizzaInfo pizzaInfo = service.add(convertToPizzaInfo(jsonToString));
//        resp.getWriter().write(mapper.writeValueAsString(pizzaInfo));
//    }
//
//    private PizzaInfo convertToPizzaInfo(String pizzaInfoJson) {
//        PizzaInfo info;
//        try {
//            info = mapper.readValue(pizzaInfoJson, PizzaInfo.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return info;
//    }
//}