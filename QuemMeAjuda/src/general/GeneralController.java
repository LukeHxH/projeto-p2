package general;

import ajuda.AjudaController;
import aluno.Aluno;
import aluno.AlunoController;
import ordenacao.Ordenador;
import ordenacao.OrdenaEmail;
import ordenacao.OrdenaNome;
import tutor.TutorController;

/**
 * Classe mediadora entre os controladores do sistema.
 *
 * @authors
 *          <ol>
 *          <i> Diego Gama </i> <i> Jessé Souza </i> <i> Lucas Medeiros </i> <i>
 *          Mikael Amaral </i>
 *          </ol>
 * @since Parte 1
 */

public class GeneralController {

	private AlunoController alunoController;
	private TutorController tutorController;
	private AjudaController ajudaController;
	private Validador val;
	private Ordenador ordem;

	private Caixa caixa;

	public GeneralController() {
		this.alunoController = new AlunoController();
		this.tutorController = new TutorController();
		this.ajudaController = new AjudaController();
		this.val = new Validador();
		this.caixa = new Caixa();
		this.ordem = new OrdenaNome();
	}

	public void cadastrarAluno(String nome, String matricula, int idCurso, String telefone, String email) {
		this.alunoController.cadastrarAluno(nome, matricula, email, idCurso, telefone);
	}

	public String recuperarAluno(String matricula) {
		return this.alunoController.recuperarAluno(matricula);
	}
	
	public void configurarOrdem(String atributo) {
		switch (atributo){
			case "NOME":
				this.ordem = new OrdenaNome();
			case "EMAIL":
				this.ordem = new OrdenaEmail();
			case "MATRICULA":
				this.ordem = null;	
			default:
				throw new IllegalArgumentException("Ordenacao invalida");
		}	
	}

	public String listarAlunos() { 
		Ordenador ordem = new OrdenaNome();
		return this.alunoController.listarAlunos(ordem); 
		}
	 
	public String getInfoAluno(String matricula, String atributo) {
		return this.alunoController.getInfoAluno(matricula, atributo);
	}

	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		Aluno aluno = this.alunoController.disponivelParaTutor(matricula);
		this.tutorController.tornaTutor(aluno, disciplina, proficiencia);
	}

	public String recuperaTutor(String matricula) {
		return this.tutorController.recuperaTutor(matricula);
	}

	public String listarTutores() {
		return this.tutorController.listarTutores(this.ordem);
	}

	public void cadastrarHorario(String email, String horario, String dia) {
		this.tutorController.cadastraHorario(email, horario, dia);
	}

	public void cadastrarLocalDeAtendimento(String email, String local) {
		this.tutorController.cadastraLocal(email, local);
	}

	public boolean consultaHorario(String email, String horario, String dia) {
		return this.tutorController.consultaHorario(email, horario, dia);
	}

	public boolean consultaLocal(String email, String local) {
		return this.tutorController.consultaLocal(email, local);
	}

	public void doar(int doacao, String matriculaTutor) {
		val.validaNumeroEmIntervalo(doacao, 0, Integer.MAX_VALUE, "",
				"Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		int valor = this.caixa.doar(doacao, tutorController.getTutor(matriculaTutor, "Erro na doacao para tutor: Tutor nao encontrado").getTipo().getNotaTutor());
		this.tutorController.getTutor(matriculaTutor, "Erro na busca por tutor: Tutor nao encontrado").getTipo()
				.adicionarDinheiro(valor);
	}

	public int totalDinheiroSistema() {
		return caixa.TotalDinheiroSistema();
	}

	public int pedirAjudaOnline(String matAluno, String disciplina) {
		val.validaString(disciplina, "Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
		val.validaString(matAluno,
				"Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		String matTutor = tutorParaAjuda(disciplina);
		return this.ajudaController.pedirAjudaOnline(matTutor, disciplina);
	}

	public int pedirAjudaPresencial(String matAluno, String disciplina, String horario, String dia, String local) {
		val.validaString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		val.validaString(matAluno,
				"Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		val.validaString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		val.validaString(dia, "Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		val.validaString(local,
				"Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
		String matTutor = tutorParaAjuda(disciplina, horario, dia, local);
		return this.ajudaController.pedirAjudaPresencial(matTutor, disciplina, horario, dia, local);
	}

	public String getInfoAjuda(String atributo, int idAjuda) {
		return this.ajudaController.getInfoAjuda(atributo, idAjuda);
	}

	private String tutorParaAjuda(String disciplina) {
		return this.tutorController.tutorParaAjuda(disciplina);
	}

	private String tutorParaAjuda(String disciplina, String horario, String dia, String local) {
		return this.tutorController.tutorParaAjuda(disciplina, horario, dia, local);
	}

	public void avaliarTutor(int idAjuda, int nota) {
		val.validaNumeroEmIntervalo(nota, 0, 5, "Erro na avaliacao de tutor: nota nao pode ser maior que 5",
				"Erro na avaliacao de tutor: nota nao pode ser menor que 0");

		String matriculaTutor = this.ajudaController.pegarTutor(idAjuda);
		this.tutorController.avaliar(matriculaTutor, nota);
	}

	public String pegarTutor(int idAjuda) {
		return this.ajudaController.pegarInfoTutor(idAjuda);
	}

	public String pegarNivel(String matTutor) {
		return this.tutorController.pegarNivelTutor(matTutor);
	}

	public String pegarNota(String matTutor) {
		return this.tutorController.pegarNota(matTutor);
	}

	public int totalDinheiroTutor(String email) {
		return this.tutorController.getDinheiro(email);
	}

	public void salvar() {
		this.alunoController.salvar();
		this.tutorController.salvar();
		this.ajudaController.salvar();
		
	}

	public void carregar() {
		this.alunoController.carregar();
		this.tutorController.carregar();
		this.ajudaController.carregar();
		
	}

	public void limpar() {
		this.alunoController.limpar();
		this.tutorController.limpar();
		this.ajudaController.salvar();
		
	}
}
