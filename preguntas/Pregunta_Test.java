package preguntas;


public abstract class Pregunta_Test extends Pregunta
{
	//ATRIBUTOS
	protected float menos;
	
	//MÉTODOS
	
	//Constructor
	
	/**
	 * Constructor.
	 * @param preg_txto
	 * @param aclar_txt
	 * @param menoss
	 */
	public Pregunta_Test(String preg_txto, String aclar_txt, float menoss) 
	{
		super(preg_txto, aclar_txt);
		this.menos = menoss;
	}
}
