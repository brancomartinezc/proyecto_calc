package logica;

public interface PluginFunction {
	
	/**
	 * Obtiene el nombre del plugin.
	 * @return nombre del plugin.
	 */
	public String getPluginName();
	
	/**
	 * Setea los parametros de la operacion.
	 * @param n1 Numero 1.
	 * @param n2 Numero 2
	 */
	public void setParameter(int n1, int n2);
	
	/**
	 * Obtiene el resultado de la operacion.
	 * @return resultado.
	 */
	public int getResult();
	
	/**
	 * 
	 * @return verdaderio si hay algun error, falso en caso contrario.
	 */
	public boolean hasError();
	
	/**
	 * Describe el tipo de error ocurrido.
	 * @return string del error.
	 */
	public String descripcionError();
}
