/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Nicolas Trindade
 */
public class TesteConexao {
    public static void main(String[] args) {
        try {
            Connection conn = (Connection) Conexao.getConnection();
            if (conn != null) {
                System.out.println("Conexão bem-sucedida!");
                conn.close(); // Fechar a conexão após o teste
            } else {
                System.out.println("Falha na conexão!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
