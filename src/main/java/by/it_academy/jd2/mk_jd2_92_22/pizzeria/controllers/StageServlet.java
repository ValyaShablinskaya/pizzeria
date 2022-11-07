package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.StageDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.PizzaInfo;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Stage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.StageService;
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

@WebServlet(name = "StageServlet", urlPatterns = "/stage")
public class StageServlet extends HttpServlet {
    private static final String BDPROPERTY = "/BDProperty.properties";
    private final BDConnector bdConnector = new BDConnector(BDPROPERTY);
    private final StageDao dao = new StageDao(bdConnector);
    private final StageService service = new StageService(dao);
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
            List<Stage> stageList = service.findAll();
            writer.write(mapper.writeValueAsString(stageList));
        } else {
            Long id = Long.parseLong(param);
            Stage stage = service.findById(id);
            writer.write(mapper.writeValueAsString(stage));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToStage(jsonToString));
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
        service.update(convertToStage(jsonToString), id, updateDate);
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
    private Stage convertToStage(String stageJson) {
        Stage stage;
        try {
            stage = mapper.readValue(stageJson, Stage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stage;
    }
}
