package com.skyeyeh.thread;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Test.
 */
@WebServlet("/thread")
public class ThreadServlet extends HttpServlet {
    static Thread thread;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (thread == null || thread.getState() == Thread.State.TERMINATED) {
            initThread();
        }

        System.out.println(thread.getState());
        PrintWriter out = resp.getWriter();
        if (thread.getState() == Thread.State.NEW) {
            // Thread start.
            out.print("Thread start.");
            thread.start();
        } else {
            // Thread started.
            out.print("Thread has been starting.");
        }
        out.close();
        return;
    }

    /**
     * Initial thread.
     */
    private void initThread() {
        thread = new Thread(() -> {
            System.out.println("Thread start!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread end!");
        });
    }
}
