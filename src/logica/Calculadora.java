package logica;
import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

public class Calculadora {
	private File ruta;
	private List plugins;
	private String[] archivos;
	private int cant_plugs;
	
	public Calculadora() {
		ruta= new File("./bin/plugins");
		//ruta= new File("./plugins");
	}
	
	/**
	 * Obtiene la lista de plugins y los carga.
	 * @throws Exception 
	 */
	public void getPlugins() throws Exception {
		ClassLoader cl;
		Class clase;
		Class[] interfaces;
		plugins= new ArrayList();
		PluginFunction pf;
		
		cl = new PluginClassLoader(ruta);
		archivos = ruta.list();
		cant_plugs=0;
		
		if(archivos!=null) {
			for(int i=0; i<archivos.length; i++) {
				
				if(archivos[i].endsWith(".class")) {
					//cargo la clase
					clase = cl.loadClass("logica."+archivos[i].substring(0, archivos[i].indexOf(".")));
					interfaces= clase.getInterfaces();
					
					for(Class intf: interfaces) {
						//Si la clase implementa PluginFunction la instancio y agrego a la lista de plugins
						if(intf.getName().contentEquals("logica.PluginFunction")) {
							pf = (PluginFunction) clase.getDeclaredConstructor().newInstance();
							plugins.add(pf);
							cant_plugs++;
							break;
						}
					}
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Obtiene los nombres de los plugins.
	 * @return Arreglo de strings.
	 */
	public String[] getPluginsNames() {
		String[] nombres=new String[cant_plugs];
		Iterator it = plugins.iterator();
		PluginFunction plug;
		int i=0;
		
		while(it.hasNext()) {
			plug=(PluginFunction) it.next();
			nombres[i]=plug.getPluginName();
			i++;
		}
		
		return nombres;
	}
	
	/**
	 * Corre el plugin.
	 * @param num1 Numero 1.
	 * @param num2 Numero 2.
	 * @param operacion Tipo de operacion.
	 * @return Resultado de la operacion.
	 */
	public float runPlugin(int num1, int num2, String operacion) {
		Iterator it = plugins.iterator();
		PluginFunction plug;
		String nombre_plug;
		float res=0;
		
		try {
			//Busco la clase de la operacion en la lista
			while(it.hasNext()) {
				plug=(PluginFunction) it.next();
				nombre_plug=plug.getPluginName();
				
				//Si encontre la clase de la operacion
				if(operacion.contentEquals(nombre_plug)) {
					res=plug.doOperation(num1,num2);
				}
				
			}
		}catch(ArithmeticException e) {
			throw new ArithmeticException();
		}
		
		return res;
	}
	
}



