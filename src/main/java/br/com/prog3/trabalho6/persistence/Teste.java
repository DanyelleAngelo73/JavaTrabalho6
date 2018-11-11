package br.com.prog3.trabalho6.persistence;

import java.sql.Connection;

import br.com.prog3.trabalho6.model.Aluno;

public class Teste {

	public static void main(String[] args) {
		AlunoDaoImp ad = new AlunoDaoImp();
		Aluno aluno = ad.findByMatricula(2);
		if(aluno != null){
		System.out.println(aluno.getNome());
		}
	}

}
