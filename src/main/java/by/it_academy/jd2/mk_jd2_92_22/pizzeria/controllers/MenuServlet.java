package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuRowDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuRowService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuService;
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

@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {
    private static final String BDPROPERTY = "/BDProperty.properties";
    BDConnector bdConnector = new BDConnector(BDPROPERTY);
    MenuDao menuDao = new MenuDao(bdConnector);
    MenuRowDao menuRowDao = new MenuRowDao(bdConnector);
    MenuRowService menuRowService = new MenuRowService(menuRowDao, menuDao);
    MenuService service = new MenuService(menuDao);
    ObjectMapper mapper = new ObjectMapper();
    Converter converter = new Converter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String param = req.getParameter("id");
        if (param == null) {
            List<Menu> menuList = service.findAll();
            writer.write(mapper.writeValueAsString(menuList));
        } else {
            Long id = Long.parseLong(param);
            List<MenuRow> menuRows = menuRowService.findAllByIdMenu(id);
            writer.write(mapper.writeValueAsString(menuRows));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToMenu(jsonToString));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime updateDate = menuDao.findById(id).get().getUpdateDate();
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
            service.update(convertToMenu(jsonToString), id, updateDate);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.parseLong(req.getParameter("id"));
        LocalDateTime updateDate = menuDao.findById(id).get().getUpdateDate();
        service.deleteById(id, updateDate);
    }

    private Menu convertToMenu(String menuJson) {
        Menu menu;
        try {
            menu = mapper.readValue(menuJson, Menu.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return menu;
    }
}
