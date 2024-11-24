package br.com.DAO;

import br.com.model.Funcionario;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.model.Cliente;
import br.com.model.Endereco;
import java.util.ArrayList;

//@author emanuelly
public class FuncionarioDAO {
	
    public static void save(Funcionario f) throws Exception {

    String queryUsuario = "INSERT INTO Usuario(id_usuario, nome_usuario, cpf_usuario, nascimento_usuario, telefone_usuario, tipo_usuario, senha_usuario, user_usuario)"
        + "values(null,?,?,?,?,?,?,?)"; //query string pra inserir na tabela usuario

    String queryFuncionario = "INSERT INTO Funcionario(id_funcionario, codigo_funcionario, cargo_funcionario, fk_usuario_id)"
        + "values(null,?,?,?)"; //query string pra inserir na tabela funcionario

    String queryEndereco = "INSERT INTO Endereco(id_endereco, cep, local, numero, bairro, cidade, uf, fk_usuario_id)"
        + "values(null,?,?,?,?,?,?,?)"; //query string pra inserir na tabela endereco

    try (Connection con = ConexaoBanco.conectar()) {
        con.setAutoCommit(false);

        PreparedStatement pst = con.prepareStatement(queryUsuario, Statement.RETURN_GENERATED_KEYS);
        pst.setString(1, f.getNome_usuario());
        pst.setString(2, f.getCpf_usuario());
        pst.setDate(3, Date.valueOf(f.getNascimento_usuario()));
        pst.setString(4, f.getTelefone_usuario());
        pst.setString(5, f.getTipo_usuario());
        pst.setString(6, f.getSenha_funcionario());
        pst.setString(7, f.getUser_usuario());
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys(); //busca a chave que foi gerada na tabela do usuario inserido			
        if (rs.next()) { //se tiver conseguido a chave, pega e coloca em idUsuario
            int idUsuario = rs.getInt(1); 
            System.out.print("Aqui");

            Endereco e = f.getEndereco_usuario();
            if (e != null) {
                PreparedStatement pste = con.prepareStatement(queryEndereco);
                pste.setString(1, e.getCep());
                pste.setString(2, e.getLocal());
                pste.setInt(3, e.getNumeroCasa());
                pste.setString(4, e.getBairro());
                pste.setString(5, e.getCidade());
                pste.setString(6, e.getUf());
                pste.setInt(7, idUsuario);
                pste.executeUpdate();

                PreparedStatement pstf = con.prepareStatement(queryFuncionario);
                pstf.setString(1, f.getCodigo_funcionario());
                pstf.setString(2, f.getCargo());
                pstf.setInt(3, idUsuario);

                int rowsFuncionario = pstf.executeUpdate(); //da o update

            if (rowsFuncionario > 0) { //se o resultado do update for > 0 entao foi possivel inserir o funcionario
                con.commit(); //tudo certo, então commita
                ConexaoBanco.desconectar(con);
                System.out.println("Funcionario inserido com sucesso!");
            }

            } else { //qualquer erro resulta rollback
                con.rollback();
                System.out.println("Erro ao obter id do usuario. Transação revertida.");
            }

        } else {
            con.rollback();
        System.out.println("Erro ao obter a chave gerada para o usuario. Transação revertida.");
    }

    } catch (SQLException e){
        e.printStackTrace();
    }
}
    
    public static ArrayList<Funcionario> findAll() throws Exception {
    ArrayList<Funcionario> funcionarios = new ArrayList<>();

    String queryCliente = "SELECT * FROM Funcionario";
    String queryUser = "SELECT * FROM Usuario WHERE id_usuario = ?";
    
    try (Connection con = ConexaoBanco.conectar()) {
        PreparedStatement pst = con.prepareStatement(queryCliente);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) { // Use um loop para adicionar clientes à lista
            Funcionario func = new Funcionario();
            
            int id = rs.getInt("fk_usuario_id");
            func.setCargo(rs.getString("cargo_funcionario"));
            func.setCodigo_funcionario(rs.getString("codigo_funcionario"));
            
            PreparedStatement pst1 = con.prepareStatement(queryUser);
            pst1.setInt(1, id);
            ResultSet rs1 = pst1.executeQuery(); 
            
            while (rs1.next()) {
                func.setId_usuario(rs1.getInt("id_usuario"));
                func.setNome_usuario(rs1.getString("nome_usuario"));
                func.setCpf_usuario(rs1.getString("cpf_usuario"));
                func.setNascimento_usuario(LocalDate.parse(String.valueOf((rs1.getDate("nascimento_usuario")))));
                func.setTelefone_usuario(rs1.getString("telefone_usuario"));
                func.setTipo_usuario(rs1.getString("tipo_usuario"));
                func.setSenha_funcionario(rs1.getString("senha_usuario"));
                func.setUser_usuario(rs1.getString("user_usuario"));

                funcionarios.add(func); // Adicione o cliente à lista
            }
        }
        
        ConexaoBanco.desconectar(con);

        if (funcionarios.isEmpty()) {
            System.out.print("Erro: Nenhum cliente encontrado");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return funcionarios;
}
    
    public static Funcionario findById(int id) throws Exception {		
        Funcionario f = new Funcionario();

        String queryFuncionario = "SELECT * FROM Funcionario WHERE id_funcionario= ?";
        String queryU = "SELECT * FROM Usuario WHERE id_usuario = ?";
        String queryEnd = "SELECT * FROM Endereco WHERE fk_usuario_id = ?";

        try (Connection con = ConexaoBanco.conectar()) {
            PreparedStatement pst = con.prepareStatement(queryFuncionario);
            pst.setInt(1, id);
            ResultSet rs0 = pst.executeQuery();
            
            if(rs0.next()) {
                int key = rs0.getInt("fk_usuario_id");
                f.setId_usuario(rs0.getInt("id_funcionario"));
                f.setCargo(rs0.getString("cargo_funcionario"));
                f.setCodigo_funcionario(rs0.getString("codigo_funcionario"));
                
                PreparedStatement pst1 = con.prepareStatement(queryU);
                pst1.setInt(1, key);
                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    int id_usuario = rs.getInt("id_usuario");
                    f.setNome_usuario(rs.getString("nome_usuario"));
                    f.setCpf_usuario(rs.getString("cpf_usuario"));
                    f.setNascimento_usuario(LocalDate.parse(rs.getString("nascimento_usuario")));
                    f.setTelefone_usuario(rs.getString("telefone_usuario"));
                    f.setTipo_usuario(rs.getString("tipo_usuario"));
                    f.setSenha_funcionario("senha_usuario");
                    f.setUser_usuario(rs.getString("user_usuario"));

                    PreparedStatement pst3 = con.prepareStatement(queryEnd);
                    pst3.setInt(1, id_usuario);
                    ResultSet rs3 = pst3.executeQuery();

                    if (rs3.next()) {
                        Endereco end = new Endereco();

                        end.setCep(rs3.getString("cep"));
                        end.setLocal(rs3.getString("local"));
                        end.setBairro(rs3.getString("bairro"));
                        end.setNumeroCasa(rs3.getInt("numero"));
                        end.setCidade(rs3.getString("cidade"));
                        end.setUf(rs3.getString("uf"));

                        f.setEndereco_usuario(end);

                        ConexaoBanco.desconectar(con);	
                    } else {
                        System.out.println("Erro ao obter o endereco do usuario");
                        return null;
                    }
                } else {
                    System.out.println("Erro ao obter o usuario (findbyid");
                    return null;
                }
            } else {
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return f;		
    }

    public static Funcionario findByUser(String user) throws Exception {
        Funcionario f = new Funcionario();

        String queryUser = "SELECT * FROM Usuario WHERE user_usuario = ?";		
        String queryFuncionario = "SELECT * FROM Funcionario WHERE fk_usuario_id = ?";

        try (Connection con = ConexaoBanco.conectar()) {
        PreparedStatement pst = con.prepareStatement(queryUser);
        pst.setString(1, user);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            f.setId_usuario(rs.getInt("id_usuario"));
            f.setNome_usuario(rs.getString("nome_usuario"));
            f.setCpf_usuario(rs.getString("cpf_usuario"));
            f.setNascimento_usuario(LocalDate.parse(String.valueOf((rs.getDate("nascimento_usuario")))));
            f.setTelefone_usuario(rs.getString("telefone_usuario"));
            f.setTipo_usuario(rs.getString("tipo_usuario"));
            f.setSenha_funcionario(rs.getString("senha_usuario"));
            f.setUser_usuario(rs.getString("user_usuario"));

            int pk = f.getId_usuario();

            PreparedStatement pstf = con.prepareStatement(queryFuncionario);
            pstf.setInt(1, pk);
            ResultSet rsf = pstf.executeQuery();

            if (rsf.next()) {
                f.setCodigo_funcionario(rsf.getString("codigo_funcionario"));
                f.setCargo(rsf.getString("cargo_funcionario"));

            } else {
                System.out.print("Erro a");
            }
        } else {
            System.out.print("Nenhum funcionário");
        }
        
        ConexaoBanco.desconectar(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return f;
    }
}
