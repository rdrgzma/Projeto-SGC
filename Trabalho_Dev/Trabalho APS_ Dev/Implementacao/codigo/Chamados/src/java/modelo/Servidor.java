/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Duda
 */
@Entity
@Table(name="servidor")
public class Servidor implements Serializable {
 //implements  Serializable 
    
    @Id
    @GeneratedValue
    private int id;
    private String nome;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    
    @OneToOne
    @JoinColumn(name="id_setor")
    private Setor setor;
    
    //@OneToOne(mappedBy="servidor")
    //private List<Atendimento> atendimentos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    public boolean isAdministrador(){
        return "Administrador".equals(this.cargo);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servidor other = (Servidor) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

  //  public List<Atendimento> getAtendimentos() {
  //      return atendimentos;
  //  }

   // public void setAtendimentos(ArrayList<Atendimento> atendimentos) {
   //     this.atendimentos = atendimentos;
   // }
  
}
