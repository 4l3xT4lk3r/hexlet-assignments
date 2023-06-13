package exercise.servlet;

import exercise.Data;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = Data.getCompanies();
        String search = request.getParameter("search");
        PrintWriter pw = response.getWriter();
        if ( search == null || search.equals("")){
            companies.forEach(pw::println);
        }else {
            if ( companies.stream().anyMatch(s->s.contains(search))){
                companies.stream().filter(s->s.contains(search)).forEach(pw::println);
            }else{
                pw.println("Companies not found");
            }
        }
        // END
    }
}
