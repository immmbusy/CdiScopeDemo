package io.github.immmbusy.web;

import io.github.immmbusy.cdi.AppStats;
import io.github.immmbusy.cdi.CartBean;
import io.github.immmbusy.cdi.RequestInfo;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cdi/cart")
public class CdiCartServlet extends HttpServlet {

    @Inject CartBean cart;       // Session-scoped state
    @Inject RequestInfo reqInfo; // Request-scoped state
    @Inject AppStats stats;      // Application-scoped state

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("add".equalsIgnoreCase(action)) cart.addItem();
        if ("remove".equalsIgnoreCase(action)) cart.removeItem();

        // This line implements the Mini-Exercise (Reset Action)
        if ("reset".equalsIgnoreCase(action)) cart.setOrderList(0);

        int hitNumber = stats.hit();

        resp.setContentType("text/plain");
        resp.getWriter().println("CDI Demo: Injection + Scopes");
        resp.getWriter().println("-------------------------");
        resp.getWriter().println("requestId = " + reqInfo.getRequestId());
        resp.getWriter().println("requestStarted = " + reqInfo.getStartedAt());
        resp.getWriter().println("cart.orderList = " + cart.getOrderList());
        resp.getWriter().println("app.totalHits = " + hitNumber);
        resp.getWriter().println();
        resp.getWriter().println("Try: /cdi/cart?action=add and /cdi/cart?action=remove");
    }
}