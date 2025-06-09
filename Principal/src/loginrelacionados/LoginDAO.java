package loginrelacionados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import banco.Conexao;

public class LoginDAO {

    public boolean autenticar(String nome, int senha) {
        String sql = "SELECT * FROM login WHERE nome = ? AND senha = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // true se encontrou login

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean cadastrar(String nome, int senha) {
    String sql = "INSERT INTO login (nome, senha) VALUES (?, ?)";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setInt(2, senha);

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
