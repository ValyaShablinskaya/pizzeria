package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.PizzaInfoDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.PizzaInfoService;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "PizzaInformationServlet", urlPatterns = "/pizzaInformation")
public class PizzaInformationServlet extends HttpServlet {
    private static final String BDPROPERTY = "/BDProperty.properties";
    private final BDConnector bdConnector = new BDConnector(BDPROPERTY);
    private final PizzaInfoDao dao = new PizzaInfoDao(bdConnector);
    private final PizzaInfoService service = new PizzaInfoService(dao);
    private final ObjectMapper mapper = new ObjectMapper();
    private final Converter converter = new Converter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String param = req.getParameter("id");
        if (param == null) {
            List<PizzaInfo> pizzaInfoList = service.findAll();
            writer.write(mapper.writeValueAsString(pizzaInfoList));
        } else {
            Long id = Long.parseLong(param);
            PizzaInfo pizzaInfo = service.findById(id);
            writer.write(mapper.writeValueAsString(pizzaInfo));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToPizzaInfo(jsonToString));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime updateDate = service.findById(id).getUpdateDate();
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.update(convertToPizzaInfo(jsonToString), id, updateDate);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime updateDate = service.findById(id).getUpdateDate();
        service.deleteById(id, updateDate);
    }

    private PizzaInfo convertToPizzaInfo(String pizzaInfoJson) {
        PizzaInfo info;
        try {
            info = mapper.readValue(pizzaInfoJson, PizzaInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return info;
    }
}