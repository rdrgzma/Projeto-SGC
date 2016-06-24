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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marcio
 */
public class FiltroLogin implements Filter{

    @Override
    public void init(FilterConfig fc) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
          HttpServletRequest req = (HttpServletRequest) sr;
        
        boolean redirecionar = false;
        if(!req.getRequestURI().endsWith("/index.xhtml")) {
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
            ((HttpServletResponse) sr1).sendRedirect(req.getContextPath()+"/faces/index.xhtml");
        else
            fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   }
 
    

