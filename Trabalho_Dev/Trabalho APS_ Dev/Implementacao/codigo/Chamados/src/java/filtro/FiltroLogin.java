/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.Servidor;

/**
 *
 * @author marcio
 */
@WebFilter(filterName = "FiltroLogin", urlPatterns = {"*.xhtml"})
public class FiltroLogin implements Filter{

    @Override
    public void init(FilterConfig fc) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
          HttpServletRequest req = (HttpServletRequest) sr;
        
        //boolean redirecionar = false;
       // if(!req.getRequestURI().endsWith("/index.xhtml")||!req.getRequestURI().endsWith("listachamados.xhtml")||!req.getRequestURI().endsWith("consultaChamado1.xhtml")||!req.getRequestURI().endsWith("cadastroChamado.xhtml")) {
            HttpSession sessao = req.getSession(false);
           
            Servidor usuario = (Servidor)sessao.getAttribute("usuario");
            if(usuario==null){
                  System.out.print("Usuario Logado.");
                   fc.doFilter(sr, sr1);
            } else{
                System.out.print("Não está logado");
            }
          //  if(sessao == null) {
           //     redirecionar = true;
         //   } else {
           //     LoginBean bean = (LoginBean)sessao.getAttribute("loginBean");
             //   if(bean == null)
               //     redirecionar = true;
                //else if(bean.getServidor()==null)
                  //      redirecionar = true;
          //  }
    //    }
    //    if(redirecionar)
      //      ((HttpServletResponse) sr1).sendRedirect(req.getContextPath()+"/faces/index.xhtml");
       // else
         //   fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
       
    }
   }
 
    

