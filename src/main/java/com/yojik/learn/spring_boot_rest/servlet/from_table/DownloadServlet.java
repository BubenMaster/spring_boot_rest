package com.yojik.learn.spring_boot_rest.servlet.from_table;



import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;



@WebServlet(urlPatterns = {"/servlet"},
        loadOnStartup = 1,
        asyncSupported = true)
public class DownloadServlet extends HttpServlet {
    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig){
        System.out.println("Servlet initialized");
        this.servletConfig = servletConfig;
    }

    @Override
    protected void service(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        // reads input file from an absolute path
        String filePath = "table.json";
        File downloadFile = new File(filePath);
        FileInputStream inStream = new FileInputStream(downloadFile);

//        // if you want to use a relative path to context root:
//        String relativePath = getServletContext().getRealPath("");
//        System.out.println("relativePath = " + relativePath);
//
//        // obtains ServletContext
//        ServletContext context = getServletContext();
//
//        // gets MIME type of the file
//        String mimeType = context.getMimeType(filePath);
//        if (mimeType == null) {
//            // set to binary type if MIME mapping not found
//            mimeType = "application/json";
//        }

        String mimeType = "application/json";
        System.out.println("MIME type: " + mimeType);

        // modifies response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inStream.close();
        outStream.close();
    }
}
