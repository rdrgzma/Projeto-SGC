/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Duda
 */
@Entity
@Table(name="servidor")
public class Servidor {
    
    @Id
    @GeneratedValue
    private int id;
    private String nomeServido;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeServido() {
        return nomeServido;
    }

    public void setNomeServido(String nomeServido) {
        this.nomeServido = nomeServido;
    }
    
    
   
    
}
