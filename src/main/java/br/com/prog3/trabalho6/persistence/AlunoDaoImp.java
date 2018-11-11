package br.com.prog3.trabalho6.persistence;

import java.util.ArrayList;
import java.util.List;

import br.com.prog3.trabalho6.model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlunoDaoImp implements AlunoDao {

	public String save(Aluno aluno) {
		String sql = "insert into aluno values(?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, aluno.getMatricula());
			pst.setString(2, aluno.getNome());
			pst.setString(3, aluno.getRa());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Inserido com sucesso.";
			} else {
				return "Erro ao inserir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String delete(int matricula) {
		String sql = "delete from aluno where matricula=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, matricula);
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			} else {
				return "Erro ao excluir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public String update(Aluno aluno) {
		String sql = "update aluno set nome=?,ra=? where matricula=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, aluno.getNome());
			pst.setString(2, aluno.getRa());
			pst.setInt(3, aluno.getMatricula());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Alterado com sucesso.";
			} else {
				return "Erro ao alterar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	public List<Aluno> list() {
		String sql = "select * from aluno";
		List<Aluno> alunos = new ArrayList<Aluno>();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Aluno a = new Aluno();
					a.setMatricula(rs.getInt(1));
					a.setNome(rs.getString(2));
					a.setRa(rs.getString(3));
					alunos.add(a);
				}
			} else {
				alunos = null;
			}
		} catch (SQLException e) {
			alunos = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return alunos;
	}

	public Aluno findByMatricula(int matricula) {
		String sql = "select * from aluno where matricula=?";
		Aluno al = new Aluno();
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, matricula);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				al.setMatricula(rs.getInt(1));
				al.setNome(rs.getString(2));
				al.setRa(rs.getString(3));
			} else {
				al = null;
			}
		} catch (SQLException e) {
			al = null;
		} finally {
			ConnectionFactory.close(con);
		}
		return al;
	}	
}
