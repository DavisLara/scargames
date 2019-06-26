package br.com.scargames.controller;

import br.com.scargames.domain.Endereco;
import br.com.scargames.services.EnderecoService;
import br.com.scargames.util.UtilMessages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "enderecoMB")
@SessionScoped
public class EnderecoMB implements Serializable{

    private Endereco endereco = new Endereco();
    private List<Endereco> enderecos;
    private EnderecoService service;
   
    public void novo(){
        this.endereco = new Endereco();
    }
    
    public void listar(){
        EnderecoService service = new EnderecoService();
        enderecos = service.listar();
    }
        
    public void addArray(Endereco endereco){
        enderecos.add(endereco);
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('gestaoEndereco').hide();");
        
    }
	
    public void inserir(){
        if (service.inserir(endereco)){
            UtilMessages.messageInfo("Endereço cadastrado com sucesso!");
            enderecos = service.listar();
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('gestaoEndereco').hide();");
        }else{
            UtilMessages.messageError("Ocorreu um erro ao cadastrar o endereço!");
        }
    }
    
    public void carregarDados(Endereco endereco){
        this.endereco = endereco;
    }
    
    public void excluir(Endereco endereco){
        if (service.excluir(endereco)){
            UtilMessages.messageInfo("Endereço excluído com sucesso!");
            enderecos = service.listar();
        }else{
            UtilMessages.messageError("Ocorreu um erro ao excluir o endereço!");
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
