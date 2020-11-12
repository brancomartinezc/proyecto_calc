package logica;

public interface PluginFunction {
	
	/**
	 * Obtiene el nombre del plugin.
	 * @return nombre del plugin.
	 */
	public String getPluginName();
	
	/**
	 * Obtiene el resultado de la operacion.
	 * @return resultado.
	 */
	public float doOperation(int n1, int n2);
	
}
