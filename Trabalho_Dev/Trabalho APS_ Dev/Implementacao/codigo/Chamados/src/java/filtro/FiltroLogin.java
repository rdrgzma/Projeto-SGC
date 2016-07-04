/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import beans.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Servidor;

/**
 *
 * @author marcio
 */

public class FiltroLogin implements Filter{

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
          
        
       HttpServletRequest req = (HttpServletRequest) request;
        
        boolean redirecionar = false;
        if(!req.getRequestURI().contains("/Chamados") || !req.getRequestURI().contains("consultaChamado1.xhtml")) {
            HttpSession sessao = req.getSession(false); 
            if(sessao == null) {
                redirecionar = true;
            } else {
                LoginBean bean = (LoginBean)sessao.getAttribute("loginBean");
                if(bean == null)
                    redirecionar = true;
                else if(bean.getServidor()==null)
                        redirecionar = true;
            }
        }
        if(redirecionar)
            ((HttpServletResponse)response).sendRedirect("Chamados/index.xhtml");
        else
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
       
    }
   }
 
    

