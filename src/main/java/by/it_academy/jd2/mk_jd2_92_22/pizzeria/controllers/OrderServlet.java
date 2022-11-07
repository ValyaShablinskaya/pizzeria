package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.OrderDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.SelectedItemDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Menu;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.MenuRow;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Order;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.SelectedItem;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.OrderService;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.SelectedItemService;
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

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final String BDPROPERTY = "/BDProperty.properties";
    private final BDConnector bdConnector = new BDConnector(BDPROPERTY);
    private final OrderDao dao = new OrderDao(bdConnector);
    private final OrderService service = new OrderService(dao);
    private final SelectedItemDao selectedItemDao = new SelectedItemDao(bdConnector);
    private final SelectedItemService selectedItemService = new SelectedItemService(selectedItemDao, dao);
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
            List<Order> orderList = service.findAll();
            writer.write(mapper.writeValueAsString(orderList));
        } else {
            Long id = Long.parseLong(param);
            List<SelectedItem> selectedItems = selectedItemService.findAllByIdOrder(id);
            writer.write(mapper.writeValueAsString(selectedItems));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToOrder(jsonToString));
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
        service.update(convertToOrder(jsonToString), id, updateDate);
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

    private Order convertToOrder(String orderJson) {
        Order order;
        try {
            order = mapper.readValue(orderJson, Order.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return order;
    }
}
