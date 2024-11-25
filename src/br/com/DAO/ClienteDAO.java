package br.com.DAO;

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

public class ClienteDAO {
	
    public static void save(Cliente c) throws Exception {
		
        String queryUsuario = "INSERT INTO Usuario(id_usuario, nome_usuario, cpf_usuario, nascimento_usuario, telefone_usuario, tipo_usuario, senha_usuario, user_usuario)"
            + "values(null,?,?,?,?,?,?,?)"; //query string pra inserir na tabela usuario

        String queryEndereco = "INSERT INTO Endereco(id_endereco, cep, local, numero, bairro, cidade, uf, fk_usuario_id)"
            + "values(null,?,?,?,?,?,?,?)"; //query string pra inserir na tabela endereco

        String queryCliente = "INSERT INTO Cliente(id_cliente, fk_usuario_id)"
            + "values(null, ?)"; //query string pra inserir na tabela funcionario

        try (Connection con = ConexaoBanco.conectar()) {
            con.setAutoCommit(false);
            PreparedStatement pst = con.prepareStatement(queryUsuario, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getNome_usuario());
            pst.setString(2, c.getCpf_usuario());
            pst.setDate(3, Date.valueOf(c.getNascimento_usuario()));
            pst.setString(4, c.getTelefone_usuario());
            pst.setString(5, c.getTipo_usuario());
            pst.setString(6, c.getSenha_cliente());
            pst.setString(7, c.getUser_usuario());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys(); //busca a chave que foi gerada na tabela do usuario inserido

            if (rs.next()) { //se tiver conseguido a chave, pega e coloca em idUsuario
                int pk = rs.getInt(1); 

                if (pk > 0) { //se o id for > 0 entao foi possivel busca-la
                        Endereco e = c.getEndereco_usuario();
                        if (e != null) {
                            PreparedStatement pste = con.prepareStatement(queryEndereco);
                            pste.setString(1, e.getCep());
                            pste.setString(2, e.getLocal());
                            pste.setInt(3, e.getNumeroCasa());
                            pste.setString(4, e.getBairro());
                            pste.setString(5, e.getCidade());
                            pste.setString(6, e.getUf());
                            pste.setInt(7, pk);
                            int rows_endereco = pste.executeUpdate();

                            if (rows_endereco > 0) {
                            	
                            	PreparedStatement pst2 = con.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS);
                                pst2.setInt(1, pk);
                                
                                int rowsCliente= pst2.executeUpdate(); //da o update
                                ResultSet rs1 = pst2.getGeneratedKeys(); 
                                if (rs1.next()) {
                                	int key = rs1.getInt(1);
                                	c.setId_usuario(key);
                                	
                                	if (rowsCliente > 0) { //se o resultado do update for > 0 entao foi possivel inserir o funcionario
                                        con.commit(); //tudo certo, então commita
                                        System.out.println("Cliente inserido com sucesso!");
                                    } else {
                                    	con.rollback();
                                        System.out.println("Erro. Transação revertida.");
                                    }
                                	
                                } else {
                                	con.rollback();
                                    System.out.println("Erro. Transação revertida.");
                                }
                             
                            } else {
                                con.rollback();
                                System.out.println("Erro ao obter o endereco do usuario. Transação revertida.");
                            }
                        } else {
                            con.rollback();
                            System.out.println("Erro ao obter id do usuario. Transação revertida.");
                        }
                } else { //qualquer erro resulta rollback
                    con.rollback();
                    System.out.println("Erro ao obter id do usuario. Transação revertida.");
                }
        } else {
            con.rollback();
            System.out.println("Erro ao obter a chave gerada para o usuario. Transação revertida.");
        }

            ConexaoBanco.desconectar(con);

        } catch (SQLException e){
                e.printStackTrace();
        }
    }
    public static ArrayList<Cliente> findAll() throws Exception {
    ArrayList<Cliente> clientes = new ArrayList<>();

    String queryCliente = "SELECT * FROM Cliente";
    String queryUser = "SELECT * FROM Usuario WHERE id_usuario = ?";
    
    try (Connection con = ConexaoBanco.conectar()) {
        PreparedStatement pst = con.prepareStatement(queryCliente);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) { // Use um loop para adicionar clientes à lista
            Cliente c = new Cliente();
            int id = rs.getInt("fk_usuario_id");
            c.setId_usuario(rs.getInt("id_cliente"));
            
            PreparedStatement pst1 = con.prepareStatement(queryUser);
            pst1.setInt(1, id);
            ResultSet rs1 = pst1.executeQuery();  
            
            while (rs1.next()) {
                c.setNome_usuario(rs1.getString("nome_usuario"));
                c.setCpf_usuario(rs1.getString("cpf_usuario"));
                c.setNascimento_usuario(LocalDate.parse(String.valueOf((rs1.getDate("nascimento_usuario")))));
                c.setTelefone_usuario(rs1.getString("telefone_usuario"));
                c.setTipo_usuario(rs1.getString("tipo_usuario"));
                c.setSenha_cliente(rs1.getString("senha_usuario"));
                c.setUser_usuario(rs1.getString("user_usuario"));

                clientes.add(c); // Adicione o cliente à lista
            }
        }
        
        ConexaoBanco.desconectar(con);

        if (clientes.isEmpty()) {
            System.out.print("Erro: Nenhum cliente encontrado");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return clientes;
}

    public static Cliente findByUser(String user) throws Exception {
        Cliente c = new Cliente();

        String queryUser = "SELECT * FROM Usuario WHERE user_usuario = ?";		
        String queryCliente = "SELECT * FROM Cliente WHERE fk_usuario_id = ?";

        try (Connection con = ConexaoBanco.conectar()) {
            PreparedStatement pst = con.prepareStatement(queryUser);
            pst.setString(1, user);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int fk = rs.getInt("id_usuario");
                c.setNome_usuario(rs.getString("nome_usuario"));
                c.setCpf_usuario(rs.getString("cpf_usuario"));
                c.setNascimento_usuario(LocalDate.parse(String.valueOf((rs.getDate("nascimento_usuario")))));
                c.setTelefone_usuario(rs.getString("telefone_usuario"));
                c.setTipo_usuario(rs.getString("tipo_usuario"));
                c.setSenha_cliente(rs.getString("senha_usuario"));
                c.setUser_usuario(rs.getString("user_usuario"));
                
                PreparedStatement pst1 = con.prepareStatement(queryCliente);
                pst1.setInt(1, fk);
                ResultSet rs1 = pst1.executeQuery();
                
                if (rs1.next()) {
                    c.setId_usuario(rs1.getInt("id_cliente"));
                } else {
                    System.out.print("Erro: ao obter cliente");
                }
                
                ConexaoBanco.desconectar(con);
                
            } else {
                System.out.print("Erro: ao obter usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return c;
}

    public static Cliente findById(int id) throws Exception {		
        Cliente cliente = new Cliente();

        String queryCliente = "SELECT * FROM Cliente WHERE id_cliente = ?";
        String queryU = "SELECT * FROM Usuario WHERE id_usuario = ?";
        String queryEnd = "SELECT * FROM Endereco WHERE fk_usuario_id = ?";

        try (Connection con = ConexaoBanco.conectar()) {
            PreparedStatement pst = con.prepareStatement(queryCliente);
            pst.setInt(1, id);
            ResultSet rs0 = pst.executeQuery();
            
            if(rs0.next()) {
                int key = rs0.getInt("fk_usuario_id");
                cliente.setId_usuario(rs0.getInt("id_cliente"));
                
                PreparedStatement pst1 = con.prepareStatement(queryU);
                pst1.setInt(1, key);
                ResultSet rs = pst1.executeQuery();

                if (rs.next()) {
                    int id_usuario = rs.getInt("id_usuario");
                    cliente.setNome_usuario(rs.getString("nome_usuario"));
                    cliente.setCpf_usuario(rs.getString("cpf_usuario"));
                    cliente.setNascimento_usuario(LocalDate.parse(rs.getString("nascimento_usuario")));
                    cliente.setTelefone_usuario(rs.getString("telefone_usuario"));
                    cliente.setTipo_usuario(rs.getString("tipo_usuario"));
                    cliente.setSenha_cliente(rs.getString("senha_usuario"));
                    cliente.setUser_usuario(rs.getString("user_usuario"));

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

                        cliente.setEndereco_usuario(end);

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

        return cliente;		
    }
    
    public static Cliente findByCpf(String cpf) throws Exception {		
	String query = "SELECT * FROM Usuario WHERE cpf_usuario = ?";
        String queryCliente = "SELECT * FROM Cliente WHERE fk_usuario_id = ?";

        Cliente cliente = new Cliente();
        
	try (Connection con = ConexaoBanco.conectar()) {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id_usuario");
                
                PreparedStatement pst1 = con.prepareStatement(queryCliente);
                pst1.setInt(1, id);
                ResultSet rs1 = pst1.executeQuery();

                if (rs1.next()) {
                    int key = rs1.getInt("id_cliente");
                    
                    cliente = ClienteDAO.findById(key);
                    ConexaoBanco.desconectar(con);
                    
                } else {
                    System.out.println("Erro ao obter o cliente (findbycpf)");
                    return null;
                }
              
                return cliente;
            } else {
                System.out.println("Erro ao obter id do usuario (findbycpf)");
                return null;
            }
	} catch (SQLException e) {
            e.printStackTrace();
            return null;
	}
    }
    
    //OBS aquii: ainda precisa tratar a excecao para o caso do cliente ter mais de uma conta,
    //nesse caso, ele precisa deletar todas as contas vinculadas antes de deletar o usuario
    public static void delete(String cpf) throws Exception {
        //query select
        String queryUser = "SELECT * FROM Usuario WHERE cpf_usuario = ?";
        String queryCliente = "SELECT * FROM Cliente WHERE fk_usuario_id = ?";
        String queryConta = "SELECT * FROM Conta WHERE fk_cliente_id = ?";
        //query delete
        String queryDelete_Conta = "DELETE FROM Conta WHERE id_conta = ?";
        String queryDelete_Cliente = "DELETE FROM Cliente WHERE id_cliente = ?";
        String queryDelete_Endereco = "DELETE FROM Endereco WHERE fk_usuario_id = ?";
        String queryDelete_User = "DELETE FROM Usuario WHERE id_usuario = ?";

        try (Connection con = ConexaoBanco.conectar()) {
            con.setAutoCommit(false);

            //encontrar
            PreparedStatement pst = con.prepareStatement(queryUser);
            pst.setString(1, cpf);
            ResultSet rsUser = pst.executeQuery();

            if (rsUser.next()) {
                int id_user = rsUser.getInt("id_usuario");

                PreparedStatement pst1 = con.prepareStatement(queryCliente);
                pst1.setInt(1, id_user);
                ResultSet rsCliente = pst1.executeQuery();

                if (rsCliente.next()) {
                    int id_cliente = rsCliente.getInt("id_cliente");

                    PreparedStatement pst3 = con.prepareStatement(queryConta);
                    pst3.setInt(1, id_cliente);
                    ResultSet rsConta = pst3.executeQuery();

                    if (rsConta.next()) {

                        int id_conta = rsConta.getInt("id_conta");
                        String tipo_conta = rsConta.getString("tipo_conta");

                        if (tipo_conta.equals("CORRENTE")) {
                            String queryDelete_ContaTipo = "DELETE FROM Conta_Corrente WHERE fk_conta_id = ?";
                            PreparedStatement pst4 = con.prepareStatement(queryDelete_ContaTipo);
                            pst4.setInt(1, id_conta);
                            int rows = pst4.executeUpdate();

                            if (rows <= 0) {
                                System.out.println("Erro ao deletar o tipo da conta.");
                                con.rollback();
                                return;
                            }

                        } else if (tipo_conta.equals("POUPANCA")) {	
                            String queryDelete_ContaTipo = "DELETE FROM Conta_Poupanca WHERE fk_conta_id = ?";
                            PreparedStatement pst4 = con.prepareStatement(queryDelete_ContaTipo);
                            pst4.setInt(1, id_conta);
                            int rows = pst4.executeUpdate();

                            if (rows <= 0) {
                                System.out.println("Erro ao deletar o tipo da conta.");
                                con.rollback();
                                return;
                            }
                        } else {
                            System.out.println("Erro ao obter o tipo da conta.");
                            return;
                        }

                        PreparedStatement pst5 = con.prepareStatement(queryDelete_Conta);
                        pst5.setInt(1, id_conta);
                        int rows_conta = pst5.executeUpdate();

                        if (rows_conta > 0) {
                            PreparedStatement pst6 = con.prepareStatement(queryDelete_Cliente);
                            pst6.setInt(1, id_cliente);
                            int rows_cliente = pst6.executeUpdate();

                            if (rows_cliente > 0) {
                                PreparedStatement pst7 = con.prepareStatement(queryDelete_Endereco);
                                pst7.setInt(1, id_user);
                                int rows_endereco = pst7.executeUpdate();

                                System.out.print("" +rows_endereco);

                                if (rows_endereco > 0) {
                                    PreparedStatement pst8 = con.prepareStatement(queryDelete_User);
                                    pst8.setInt(1, id_user);
                                    int rows_usuario = pst8.executeUpdate();

                                    if (rows_usuario > 0) {
                                        con.commit();
                                        System.out.println("Usuario cliente, e contas associadas foram deletados com sucesso!");
                                    } else {
                                        System.out.println("Erro ao deletar o usuario.");
                                        con.rollback();
                                    }
                                } else {
                                    System.out.println("Erro ao deletar tabela endereco do usuario.");
                                    con.rollback();
                                }			
                            } else {
                                System.out.println("Erro ao deletar tabela cliente do usuario.");
                                con.rollback();
                            }					
                        } else {
                            System.out.println("Erro ao deletar conta do usuario.");
                            con.rollback();
                        }
                    } else {
                        System.out.println("Erro ao obter id da conta.");
                    }					
                } else {
                    System.out.println("Erro ao obter id do cliente.");
                }
            } else {
                System.out.println("Erro ao obter id do usuario.");
            }	
            
            ConexaoBanco.desconectar(con);

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public static void alter(Cliente c) throws Exception {
    	String querySelect = "SELECT * FROM Cliente WHERE id_cliente = ?";
    	
    	String queryCliente = "UPDATE Usuario"
                + " SET telefone_usuario = ?"
                + " WHERE id_usuario = ?";
    	
    	String queryEnd = "UPDATE Endereco"
                + " SET cep = ?, local = ?, numero = ?, bairro = ?, cidade = ?, uf = ?"
                + " WHERE fk_usuario_id = ?";
    	
    	 try (Connection con = ConexaoBanco.conectar()) {
             con.setAutoCommit(false);
             
             PreparedStatement pst = con.prepareStatement(querySelect);
             pst.setInt(1, c.getId_usuario());
             ResultSet rsUser = pst.executeQuery();
             
             if (rsUser.next()) {
            	 int id = rsUser.getInt("fk_usuario_id");
            	 
            	 PreparedStatement pst1 = con.prepareStatement(queryCliente);
                 pst1.setString(1, c.getTelefone_usuario());
                 pst1.setInt(2, id);
                 int rows = pst1.executeUpdate();
                 
                 if (rows > 0) {
                	 Endereco e = c.getEndereco_usuario();
                	 
                	 PreparedStatement pste = con.prepareStatement(queryEnd);
                     pste.setString(1, e.getCep());
                     pste.setString(2, e.getLocal());
                     pste.setInt(3, e.getNumeroCasa());
                     pste.setString(4, e.getBairro());
                     pste.setString(5, e.getCidade());
                     pste.setString(6, e.getUf());
                     pste.setInt(7, id);
                     int rows_endereco = pste.executeUpdate();
            	 		
                     if (rows_endereco > 0) {
                    	 con.commit();
                    	 System.out.print("Endereco alterado!");
                     } else {
                    	 System.out.print("Erro ao alterar endereco");
                    	 con.rollback();
                     }
                 } else {
                	 System.out.print("Erro ao alterar telefone");
                	 con.rollback();
                 }
            	 
             } else {
            	 System.out.print("Erro ao obter usuario");
            	 con.rollback();
             }
             
             ConexaoBanco.desconectar(con);
             
    	 } catch (SQLException e) {
    		 e.printStackTrace();
    	 }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}