package br.com.DAO;

import br.com.model.Cliente;
import br.com.model.Conta;
import br.com.model.ContaCorrente;
import br.com.model.ContaPoupanca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaDAO {
    public static void save(Conta conta) {
        String queryUser = "SELECT id_usuario FROM Usuario WHERE cpf_usuario = ?";

        String queryCliente = "SELECT id_cliente FROM Cliente WHERE fk_usuario_id = ?";

        String queryConta = "INSERT INTO Conta(id_conta, numero_conta, agencia_conta, saldo, tipo_conta, fk_cliente_id)"
             + "values(null, ?,?,?,?,?)";

        try (Connection con = ConexaoBanco.conectar()) {
            con.setAutoCommit(false);

            PreparedStatement pst = con.prepareStatement(queryUser); //primeiro encontra o usuario atraves do CPF e pega o id
            Cliente cliente = conta.getCliente();	
            pst.setString(1, cliente.getCpf_usuario());

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int pk = rs.getInt(1); 

                PreparedStatement pstcl = con.prepareStatement(queryCliente); //atraves do id do usuaio, busca o cliente
                pstcl.setInt(1, pk);
                ResultSet rs2 = pstcl.executeQuery();
                if (rs2.next()) {
                    int id = rs2.getInt(1);

                    PreparedStatement pstc = con.prepareStatement(queryConta, Statement.RETURN_GENERATED_KEYS); //insere a conta e o id do cliente com fk na conta
                    pstc.setInt(1, conta.getNumero_conta());	
                    pstc.setString(2, conta.getAgencia_conta());
                    pstc.setDouble(3, conta.getSaldo_conta());
                    pstc.setString(4, conta.getTipo_conta());
                    pstc.setInt(5, id);
                    int rows = pstc.executeUpdate();
                    
                    if (rows > 0) {

                        ResultSet rs3 = pstc.getGeneratedKeys(); //insere agora na tabela conta corrente, passando o id gerado na conta associada como fk

                        if (rs3.next()) {
                            int pk_conta = rs3.getInt(1);

                            if (conta instanceof ContaCorrente) {
                                ContaCorrente cc = (ContaCorrente) conta;

                                String queryContaCorrente = "INSERT INTO Conta_Corrente(conta_corrente_id, limite, data_vencimento, fk_conta_id)"
                                + "values(null, ?,?,?)";

                                PreparedStatement pstcorrente = con.prepareStatement(queryContaCorrente);
                                pstcorrente.setDouble(1, cc.getLimite());
                                pstcorrente.setDate(2, Date.valueOf(cc.getDataVencimento()));
                                pstcorrente.setInt(3, pk_conta);
                                int rows2 = pstcorrente.executeUpdate();

                                if (rows2 > 0) {
                                    con.commit();
                                    System.out.println("Conta inserida!");
                                }

                            } else if (conta instanceof ContaPoupanca) {
                                ContaPoupanca cp = (ContaPoupanca) conta;

                                String queryContaPoupanca = "INSERT INTO Conta_Poupanca(conta_poupanca_id, taxa_rendimento, fk_conta_id)"
                                + "values(null, ?,?)";

                                PreparedStatement pstpoupanca = con.prepareStatement(queryContaPoupanca);
                                pstpoupanca.setDouble(1, cp.getTaxaRendimento());
                                pstpoupanca.setInt(2, pk_conta);
                                int rows2 = pstpoupanca.executeUpdate();

                                if (rows2 > 0) {
                                    con.commit();
                                    System.out.println("Conta inserida!");
                                }
                            } else {
                                System.out.println("Erro ao obter inserir conta");
                                con.rollback();
                            } 
                        } else {
                            System.out.println("Erro ao obter instancia da conta");
                            con.rollback();
                        }
                    } else {
                        System.out.println("Erro ao obter Id da conta");
                        con.rollback();
                    }
                } else {
                    System.out.println("Erro ao obter Id do cliente");
                    con.rollback();
                }
            } else {
                System.out.println("Erro ao obter Id do usuario");
                con.rollback();
            }
            
            ConexaoBanco.desconectar(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }  
    }
    
    public static Conta findByNumero(int numero) throws Exception {
        String queryConta = "SELECT * FROM Conta WHERE numero_conta = ?";
        try (Connection con = ConexaoBanco.conectar()) {

            PreparedStatement pst = con.prepareStatement(queryConta);
            pst.setInt(1, numero);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String tipo = rs.getString("tipo_conta");

                if (tipo.equals("CORRENTE")) {
                    ContaCorrente c = new ContaCorrente();
                	
                    c.setNumero_conta(rs.getInt("numero_conta"));
                    c.setAgencia_conta(rs.getString("agencia_conta"));
                    c.setSaldo_conta(rs.getDouble("saldo"));
                    c.setTipo_conta(rs.getString("tipo_conta"));
                    int fk_cliente = rs.getInt("fk_cliente_id");

                    Cliente cliente = ClienteDAO.findById(fk_cliente);
                    c.setCliente(cliente);
                    int pk = rs.getInt(1);
                    c.setId_conta(pk);

                    String queryCcorrente = "SELECT * FROM Conta_Corrente WHERE fk_conta_id = ?";
                    PreparedStatement pst2 = con.prepareStatement(queryCcorrente);
                    pst2.setInt(1, pk);
                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {
                        c.setLimite(rs2.getDouble("limite"));
                        c.setDataVencimento(LocalDate.parse(rs2.getString("data_vencimento")));	
                        ConexaoBanco.desconectar(con);
                        return c;
                    } else {
                        System.out.println("Erro ao obter fk da conta");
                        return null;
                    }

                } else if (tipo.equals("POUPANCA")) {
                    ContaPoupanca c = new ContaPoupanca();

                    c.setNumero_conta(rs.getInt("numero_conta"));
                    c.setAgencia_conta(rs.getString("agencia_conta"));
                    c.setSaldo_conta(rs.getDouble("saldo"));
                    c.setTipo_conta(rs.getString("tipo_conta"));
                    int fk_cliente = rs.getInt("fk_cliente_id");

                    Cliente cliente = ClienteDAO.findById(fk_cliente);
                    c.setCliente(cliente);
                    int pk = rs.getInt(1);
                    c.setId_conta(pk);
                    
                    String queryPoupanca = "SELECT * FROM Conta_Poupanca WHERE fk_conta_id = ?";
                    PreparedStatement pst2 = con.prepareStatement(queryPoupanca);
                    pst2.setInt(1, pk);
                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {
                        c.setTaxaRendimento(rs2.getDouble("taxa_rendimento"));
                        ConexaoBanco.desconectar(con);
                        return c;
                    } else {
                        System.out.println("Erro ao obter fk da conta tipo");
                        return null;
                    }
                } else {
                    System.out.println("Erro: tipo de conta invalido");
                    return null;
                }
            } else {
                System.out.println("Erro ao obter tipo de conta");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   public static Conta findByClienteId(int id) throws Exception {       
        String queryConta = "SELECT * FROM Conta WHERE fk_cliente_id = ?";
        try (Connection con = ConexaoBanco.conectar()) {

            PreparedStatement pst = con.prepareStatement(queryConta);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String tipo = rs.getString("tipo_conta");

                if (tipo.equals("CORRENTE")) {
                    ContaCorrente c = new ContaCorrente();

                    c.setAgencia_conta(rs.getString("agencia_conta"));
                    c.setSaldo_conta(rs.getDouble("saldo"));
                    c.setTipo_conta(tipo);
                    c.setNumero_conta(rs.getInt("numero_conta"));
                    int fk_cliente = rs.getInt("fk_cliente_id");

                    Cliente cliente = ClienteDAO.findById(fk_cliente);
                    c.setCliente(cliente);
                    int pk = rs.getInt(1);
                    c.setId_conta(pk);
                    
                    String queryCcorrente = "SELECT * FROM Conta_Corrente WHERE fk_conta_id = ?";
                    PreparedStatement pst2 = con.prepareStatement(queryCcorrente);
                    pst2.setInt(1, pk);
                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {
                        c.setLimite(rs2.getDouble("limite"));
                        c.setDataVencimento(LocalDate.parse(rs2.getString("data_vencimento")));	
                        ConexaoBanco.desconectar(con);
                        return c;
                    } else {
                        System.out.println("Erro ao obter fk da conta");
                        return null;
                    }

                } else if (tipo.equals("POUPANCA")) {
                    ContaPoupanca c = new ContaPoupanca();

                    c.setAgencia_conta(rs.getString("agencia_conta"));
                    c.setSaldo_conta(rs.getDouble("saldo"));
                    c.setTipo_conta(tipo);
                    c.setNumero_conta(rs.getInt("numero_conta"));
                    int fk_cliente = rs.getInt("fk_cliente_id");

                    Cliente cliente = ClienteDAO.findById(fk_cliente);
                    c.setCliente(cliente);
                    int pk = rs.getInt(1);
                    c.setId_conta(pk);
                    
                    String queryPoupanca = "SELECT * FROM Conta_Poupanca WHERE fk_conta_id = ?";
                    PreparedStatement pst2 = con.prepareStatement(queryPoupanca);
                    pst2.setInt(1, pk);
                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next()) {
                        c.setTaxaRendimento(rs2.getDouble("taxa_rendimento"));
                        ConexaoBanco.desconectar(con);
                        return c;
                    } else {
                        System.out.println("Erro ao obter fk da conta tipo");
                        return null;
                    }
                } else {
                    System.out.println("Erro: tipo de conta invalido");
                    return null;
                }
            } else {
                System.out.println("Erro ao obter a conta");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return null;
    }
   
   public static boolean delete(Conta conta) throws SQLException {
        boolean confirma = false;
       
        String QueryContaSelect = "SELECT * FROM Conta WHERE numero_conta = ?";
        String QueryContaDelete = "DELETE FROM Conta WHERE id_conta = ?";
        int key = conta.getNumero_conta();  
        
        try (Connection con = ConexaoBanco.conectar()) { 
            con.setAutoCommit(false);
            
            PreparedStatement pst = con.prepareStatement(QueryContaSelect);
            pst.setInt(1, key);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                String tipo = rs.getString("tipo_conta");
                int id = rs.getInt("id_conta");
                
                if (tipo.equals("CORRENTE")) {
                    String QueryTipo = "DELETE FROM Conta_Corrente WHERE fk_conta_id = ?";
                   
                    PreparedStatement pst1 = con.prepareStatement(QueryTipo);
                    pst1.setInt(1, id);
                    int rows = pst1.executeUpdate();
                    
                    if (rows > 0) {
                        PreparedStatement pst2 = con.prepareStatement(QueryContaDelete);
                        pst2.setInt(1, id);
                        int rows1 = pst2.executeUpdate();
                        
                        if (rows1 > 0) {
                            con.commit();
                            confirma = true;
                        } else {
                            System.out.println("Erro ao deletar a conta");
                            con.rollback();
                        }

                    } else {
                        System.out.println("Erro ao deletar conta corrente");
                        con.rollback();
                    }
                    
                } else if (tipo.equals("POUPANCA")) {
                    String QueryTipo = "DELETE FROM Conta_Poupanca WHERE fk_conta_id = ?";
                   
                    PreparedStatement pst1 = con.prepareStatement(QueryTipo);
                    pst1.setInt(1, id);
                    int rows = pst1.executeUpdate();
                    
                    if (rows > 0) {
                        PreparedStatement pst2 = con.prepareStatement(QueryContaDelete);
                        pst2.setInt(1, key);
                        int rows1 = pst2.executeUpdate();
                        
                        if (rows1 > 0) {
                            confirma = true;
                        } else {
                            System.out.println("Erro ao deletar a conta");
                            con.rollback();
                        }

                    } else {
                        System.out.println("Erro ao deletar conta poupanca");
                        con.rollback();
                    }
                }
                
            } else {
                System.out.println("Erro ao obter tipo da conta");
                return false;
            }
            
            ConexaoBanco.desconectar(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return confirma;
   }
   
   public static boolean updateSaldoAdd(int numero, double valor) throws Exception {
	   boolean confirma = false;
	   
       String queryUpadate = "UPDATE Conta"
               + " SET saldo = saldo + ?"
               + " WHERE numero_conta = ?";
              
       String queryTransacao = "INSERT INTO Transacao(id_transacao, tipo_transacao, valor, data_hora, fk_id_conta)"
       		+ "values(null, ?,?,?,?)";
       
       try (Connection con = ConexaoBanco.conectar()) {
           con.setAutoCommit(false);
                   
           PreparedStatement pst = con.prepareStatement(queryUpadate);
           pst.setDouble(1, valor);
           pst.setInt(2, numero);
           int rows = pst.executeUpdate();
           
           if (rows > 0) {    
           	Conta conta = ContaDAO.findByNumero(numero);
               PreparedStatement pst1 = con.prepareStatement(queryTransacao);
               pst1.setString(1, "DEPOSITO");
               pst1.setDouble(2, valor);
               pst1.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
               pst1.setInt(4, conta.getId_conta());
               int rows1 = pst1.executeUpdate();
               
               if (rows1 > 0){
               	 con.commit();
                    System.out.println("Saldo update add realizado!");
                    confirma = true;
               } else {
               	System.out.println("Erro ao inserir o deposito na transacao");
                   con.rollback();
               }
           } else {
               System.out.println("Erro ao realizar o update add");
               con.rollback();
           }
           
           ConexaoBanco.desconectar(con);
           
       } catch (SQLException e) {
           e.printStackTrace();
       }
       
       return confirma;
    }
   
    public static boolean updateSaldoMinus(int numero, double valor) throws Exception {
        boolean confirma = false;
        
        String queryUpadate = "UPDATE Conta"
                + " SET saldo = saldo - ?"
                + " WHERE numero_conta = ?";
                
        String queryTransacao = "INSERT INTO Transacao(id_transacao, tipo_transacao, valor, data_hora, fk_id_conta)"
        		+ "values(null, ?,?,?,?)";
        
        try (Connection con = ConexaoBanco.conectar()) {
            con.setAutoCommit(false);
                    
            PreparedStatement pst = con.prepareStatement(queryUpadate);
            pst.setDouble(1, valor);
            pst.setInt(2, numero);
            int rows = pst.executeUpdate();
            
            if (rows > 0) {    
            	Conta conta = ContaDAO.findByNumero(numero);
                PreparedStatement pst1 = con.prepareStatement(queryTransacao);
                pst1.setString(1, "SAQUE");
                pst1.setDouble(2, valor);
                pst1.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                pst1.setInt(4, conta.getId_conta());
                int rows1 = pst1.executeUpdate();
                
                if (rows1 > 0){
                	 con.commit();
                     System.out.println("Saldo update minus realizado!");
                     confirma = true;
                } else {
                	System.out.println("Erro ao inserir o saque na transacao");
                    con.rollback();
                }
            } else {
                System.out.println("Erro ao realizar o update minus");
                con.rollback();
            }
            
            ConexaoBanco.desconectar(con);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return confirma;
    }
}
        
