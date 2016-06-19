package beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Servidor;
import persistencia.ServidorDao;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Servidor servidor = new Servidor();
    private boolean log;
    private int id;
    private String cargo;

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String login() {

        log = false;
        ServidorDao dao = new ServidorDao();
        servidor = dao.autentica(servidor.getLogin(), servidor.getSenha());
        if (servidor == null) {
            log = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos.", ""));
            servidor = new Servidor();
            return "index";
        } else {
            cargo = servidor.getCargo();
            log = true;
            if (servidor.getCargo().equalsIgnoreCase("adm")) {
                return "menu";
            } else {
                return "menu2";
            }
        }
    }

    public String verificaLogin() {

        if (log == true && cargo.equalsIgnoreCase("adm")) {
            return "menu";
        } else if (log == true) {
            return "menu2";
        } else {
            return "index";
        }
    }

    public String verificaLoginChamado() {

        if (log == true) {
            return "listaChamados";
        } else {
            return "index";
        }
    }

    public String sair() {
        
        log = false;
        cargo = "";

        return "index";

    }

    public boolean verificaSessao() {
        boolean estado;

        if (log == true) {

            estado = true;
        } else {
            estado = false;
        }

        return estado;
    }

}
