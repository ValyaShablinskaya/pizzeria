package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.MenuDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.MenuService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.MenuStorage;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.storage.entity.MenuRow;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
//public class MenuServlet extends HttpServlet {
//
//    MenuStorage storage = new MenuStorage();
//    MenuService service = new MenuService(storage);
//    ObjectMapper mapper = new ObjectMapper();
//    Converter converter = new Converter();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        List<MenuRow> menuRows = service.findAll();
//        PrintWriter writer = resp.getWriter();
//        writer.write(mapper.writeValueAsString(menuRows));
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//    }
//}
