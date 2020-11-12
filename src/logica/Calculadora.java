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
		//ruta= new File("./bin/plugins");
		ruta= new File("./plugins");
	}
	
	public void getPlugins() {
		ClassLoader cl;
		Class clase;
		Class[] interfaces;
		plugins= new ArrayList();
		PluginFunction pf;
		
		try {
			cl = new PluginClassLoader(ruta);
			archivos = ruta.list();
			cant_plugs=0;
			
			if(archivos!=null) {
				for(int i=0; i<archivos.length; i++) {
					
					if(archivos[i].endsWith(".class")) {
						//cargo la clase, la instacio y la agrego a la lista de plugins
						clase = cl.loadClass(archivos[i].substring(0, archivos[i].indexOf(".")));
						interfaces= clase.getInterfaces();
						
						for(Class intf: interfaces) {
							//Si la clase implementa PluginFunction la agrego a la lista de plugisn
							if(intf.getName().contentEquals("logica.PluginFunction")) {
								//JOptionPane.showMessageDialog(null,"instancio: "+archivos[i]);
								pf = (PluginFunction) clase.getDeclaredConstructor().newInstance();
								//JOptionPane.showMessageDialog(null,"Agrego plugin a la lista: "+archivos[i]);
								plugins.add(pf);
								//JOptionPane.showMessageDialog(null,"Aumento cantidad: "+archivos[i]);
								cant_plugs++;
								break;
							}
						}
						
					}
					
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Error en la ruta.");;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
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



