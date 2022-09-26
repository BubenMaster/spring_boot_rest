package com.yojik.learn.spring_boot_rest.servlet.from_table;

import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Route("/download")
@WebServlet(urlPatterns = {"/download"},
            loadOnStartup = 1,
            asyncSupported = true)
public class LocalHttpServlet extends HttpServlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("Servlet initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getOutputStream().write("Response is Here".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
