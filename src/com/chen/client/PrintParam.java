package com.chen.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2016/9/14.
 */
@WebServlet(name="PrintParam",urlPatterns={"/param_return"},initParams={@WebInitParam(name="",value="")},loadOnStartup=1)
public class PrintParam extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type","text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        System.out.println(name);
        Writer w = resp.getWriter();
        w.append("ok!!!");
        w.flush();
        w.close();
//

    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("content-type","text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        InputStream in =new BufferedInputStream( req.getInputStream());
        int len = 0;
        byte[] buffer = new byte[2048];
        ByteArrayOutputStream out = new ByteArrayOutputStream();


        while ((len = in.read(buffer)) != -1) {
            out.write(buffer,0,len);

        }

        System.out.println(new String(out.toByteArray(),"utf-8"));
        resp.setContentType("text/xml;charset=UTF-8");
        Writer w = resp.getWriter();
        String buffer_out = new String(out.toByteArray(), "utf-8");
        System.out.println(buffer_out);
        w.append(buffer_out);
        w.flush();
        w.close();
    }
}
