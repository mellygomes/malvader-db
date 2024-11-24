package testes;

//@author emanu

import br.com.model.Cliente;
import br.com.model.Funcionario;
import br.com.model.Usuario;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TesteSessao {
    private Usuario usuario;
    private final String path = "./temp/sessao.csv";

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
      
    public void salvarSessao() throws IOException {
        FileWriter fw = null;
        
        try {
            fw = new FileWriter(path, StandardCharsets.ISO_8859_1, false);
            
            if (this.usuario instanceof Cliente) {
                fw.write(this.usuario.getNome_usuario() + "," + this.usuario.getUser_usuario() + ",CLIENTE\n");

            } else if (this.usuario instanceof Funcionario) {
                fw.write(this.usuario.getNome_usuario() + "," + this.usuario.getUser_usuario() + ",FUNCIONARIO\n");
            }
                   
            //escreve os dados no buffer
            //fw.flush();

        } catch (IOException e){                              
            e.printStackTrace();
            
        } finally { 
            if (fw != null) {
                fw.close();
            }
        }
        
    }
    
    public Usuario buscarSessao() throws FileNotFoundException, IOException {        
        BufferedReader br = new BufferedReader(new FileReader(path));
        String linha;
        
        while ((linha = br.readLine()) != null) {
            String[] elemento = linha.split(",");
            String nome = elemento[0];
            String user = elemento[1];
            String tipo = elemento[2];
            
            if (tipo.equals("CLIENTE")) {
                Funcionario f = new Funcionario();
                f.setNome_usuario(nome);
                f.setUser_usuario(user);
                f.setTipo_usuario(tipo);
                return f;
            } else if (tipo.equals("FUNCIONARIO")) {
                Cliente c = new Cliente();
                c.setNome_usuario(nome);
                c.setUser_usuario(user);
                c.setTipo_usuario(tipo);
                return (Cliente) usuario;
            }
            
            br.close();
        }
        
        return null;
    }
}
