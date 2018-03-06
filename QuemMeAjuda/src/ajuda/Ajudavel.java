package ajuda;

/**
 * Interface com métodos que deverão ser utilizados pelas ajudas pedidas pelos
 * alunos.
 *
 * @authors
 *          <ol>
 *          <i> Diego Gama </i> <i> Jessé Souza </i> <i> Lucas Medeiros </i> <i>
 *          Mikael Amaral </i>
 *          </ol>
 * @since Parte 2
 */

public interface Ajudavel {

	/**
	 * Metodo para pegar o tutor requisitado na ajuda.
	 * 
	 * @return matricula do tutor requisitado.
	 * @since Parte 2
	 */
	public String getTutor();

	/**
	 * Metodo para pegar informaçao da ajuda.
	 * 
	 * @param atributo
	 *            string que define que tipo de informaçao sobre ajuda deve ser
	 *            retornada.
	 * @return informaçao sobre ajuda.
	 * @since Parte 2
	 */
	public String getInfo(String atributo);

	/**
	 * Metodo para pegar o id de uma ajuda.
	 * 
	 * @return id da ajuda.
	 * @since Parte 2
	 */
	public int getId();

	/**
	 * Metodo que retorna a matricula do tutor
	 * 
	 * @return String representante da matricula do tutor
	 */
	public String getMatriculaTutor();
	
	/**
	 * Metodo que retorna o estado da ajuda relacionado a sua avaliacao
	 * 
	 * @return Boolean que representa o estado avaliativo da ajuda
	 */
	public boolean getFoiAvaliada();
}
