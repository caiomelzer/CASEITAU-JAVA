package com.melzer.caseitau.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;
import java.time.ZonedDateTime;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String nome_completo;
    @Email
    private String email;
    private String cpf;
    @Column(name = "data_cadastro")
	private ZonedDateTime  data_cadastro;

	public Usuario(){
        super();
    }
	public Usuario(int id, String nome_completo, String email, String cpf, ZonedDateTime  data_cadastro) {
        super();
        this.id = id;
        this.nome_completo = nome_completo;
        this.email = email;
        this.cpf=cpf;
        this.data_cadastro=data_cadastro;
    }
	public int getId() {
        return id;
    }
	public void setId(int id) {
	    this.id = id;
	}
	public String getNome_completo() {
	    return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
	    this.nome_completo = nome_completo;
	}
	public String getEmail() {
	    return email;
	}
	public void setEmail(String email) {
	    this.email = email;
	}
	public String getCpf() {
	    return cpf;
	}
	public void setCpf(String cpf) {
	    this.cpf = cpf;
	}
	public ZonedDateTime getData_cadastro() {
		return data_cadastro;
	}
}