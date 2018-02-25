package tutor;

import java.util.Map;

import aluno.Alunado;
import aluno.Aluno;

import java.util.HashMap;

/**
 * 
 * [DESCRIÇÃO DO QUE A CLASSE É, REPRESENTA E OUTRAS INFORMAÇÕES IMPORTANTES]
 * 
 * @authors 
 * <ol> 
 *  <i> Diego Gama </i>
 *  <i> Jessé Souza </i>
 *  <i> Lucas Medeiros </i>
 *  <i> Mikael Amaral </i>
 * </ol>
 * @since Parte 1
 */
public class TutorController {

	/*
	 * Representa uma lista de tutores.
	 */
	private Map<String, Aluno> tutores;
	
	/*
	 * Representa o construtor do Controller de Tutor.
	 */
	public TutorController() {
		this.tutores = new HashMap<String, Aluno>();
	}
	
	/*
	 * Metodo responsavel por assumir a fun��o de Tutor para um aluno.
	 * 
	 * @param Aluno
	 * 			Aluno que assumir� a fun��o de tutor.
	 * 
	 * @param disciplina
	 * 			A disciplina que o aluno virar� um tutor.
	 * 
	 * @param proficiencia
	 * 			A proficiencia do aluno na disciplina.
	 */
	public void tornaTutor(Aluno aluno, String disciplina, int proficiencia) {
		tutores.put(aluno.getMatricula(), aluno);
		aluno.tornarTutor(disciplina, proficiencia);
	}
	
	/*
	 * Metodo responsavel por retornar a descricao textual de um Tutor.
	 * 
	 * A descri��o segue o modelo:
	 * 
	 * @param matricula
	 * 			Matricula do tutor em quest�o.
	 * 
	 * @return String A descri��o textual do tutor.
	 */
	public String recuperaTutor(String matricula) {
		return this.tutores.get(matricula).gerarDetalhes();
	}
	
	/*
	 * M�todo respons�vel por retornar uma lista de todos os tutores.
	 * 
	 * @return tutores
	 * 		Uma lista com todos os Tutores registrados.
	 */
	public String listarTutores() {
		String lista = "";
		
		for(Aluno aluno : tutores.values()) {
			lista += aluno.toString() + "\n";
		}
		
		return lista;
	}
	
	 
}
