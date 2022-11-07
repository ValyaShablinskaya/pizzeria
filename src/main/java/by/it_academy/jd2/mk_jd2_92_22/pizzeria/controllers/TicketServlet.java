package by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers;

import by.it_academy.jd2.mk_jd2_92_22.pizzeria.controllers.util.Converter;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.BDConnector;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.TicketDao;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.dao.entity.Ticket;
import by.it_academy.jd2.mk_jd2_92_22.pizzeria.services.TicketService;
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

@WebServlet(name = "TicketServlet", urlPatterns = "/ticket")
public class TicketServlet extends HttpServlet {
    private static final String BDPROPERTY = "/BDProperty.properties";
    private final BDConnector bdConnector = new BDConnector(BDPROPERTY);
    private final TicketDao dao = new TicketDao(bdConnector);
    private final TicketService service = new TicketService(dao);
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
            List<Ticket> ticketList = service.findAll();
            writer.write(mapper.writeValueAsString(ticketList));
        } else {
            Long id = Long.parseLong(param);
            Ticket ticket = service.findById(id);
            writer.write(mapper.writeValueAsString(ticket));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        BufferedReader bufferedReader = req.getReader();
        String jsonToString = converter.convertToString(bufferedReader);
        service.add(convertToTicket(jsonToString));
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
        service.update(convertToTicket(jsonToString), id, updateDate);
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

    private Ticket convertToTicket(String ticketJson) {
        Ticket ticket;
        try {
            ticket = mapper.readValue(ticketJson, Ticket.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }
}
