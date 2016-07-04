/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Atendimento;
import modelo.Chamado;
import persistencia.AtendimentoDao;

/**
 *
 * @author marcio
 */
@ManagedBean(name="atendimentoBean")
@SessionScoped
public class AtendimentoBean {
    private AtendimentoDao dao = new AtendimentoDao();
    private ChamadoBean chamadoBean;
    private Chamado chamado;
    
    private Atendimento atendimento =new Atendimento();
    private List<Atendimento> atendimentos;
    
      public void salvar() {
        dao.salvar(atendimento);
        chamadoBean.salvar();
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Atendimento salvo com sucesso!!!");
        this.atendimentos= dao.listar();
       
    }
    
     public void carregar(int id) {
        atendimento = dao.carregar(id);
    }
     public List listarAtendimentos(Chamado c){
         return dao.listarAtendimento(c);
     }
     public void remover(int id) {
         
        dao.remover(id);
        atendimentos = dao.listar(); 
        enviarMensagem(FacesMessage.SEVERITY_INFO, "Atendimento removido com sucesso");

    }
      private void enviarMensagem(FacesMessage.Severity sev, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(sev, msg, ""));
    }
      @PreDestroy
    public void encerrar() {
        dao.encerrar();
    }

    public ChamadoBean getChamadoBean() {
        return chamadoBean;
    }

    public void setChamadoBean(ChamadoBean chamadoBean) {
        this.chamadoBean = chamadoBean;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Chamado getChamado() {
        return chamado;
    }

    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }
    
}
