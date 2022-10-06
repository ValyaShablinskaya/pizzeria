package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.MenuRowStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.PizzaInfo;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "MenuRowServlet", urlPatterns = "/menuRow")
public class MenuRowServlet extends HttpServlet {
    private final MenuRowStorage storage = new MenuRowStorage();
    private final MenuRowService service = new MenuRowService(storage);
    private final ObjectMapper mapper = new ObjectMapper();
    private final Converter converter = new Converter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToMenuRow(jsonToString));
    }

    private MenuRow convertToMenuRow(String menuRowJson) {
        MenuRow menuRow;
        try {
           menuRow = mapper.readValue(menuRowJson, MenuRow.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menuRow;
    }
}
