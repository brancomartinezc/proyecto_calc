package logica;
import java.io.File;
import java.util.*;

public class Calculadora {
	//private File[] archivos;
	private File ruta;
	private List plugins;
	private String[] archivos;
	private int ultimo_resultado;
	private boolean ultima_operacion_error;
	private String descrip_error;
	
	public Calculadora() {
		//IMPORTANTE: antes de exportar el jar cambiar la ruta a "./plugins"
		//ya que el jar va a estar en la misma carpeta bin
		ruta= new File("./bin/plugins");
		plugins= new ArrayList();
	}
	
	public void getPlugins() {
		ClassLoader cl;
		//String[] archivos;
		Class c;
		plugins= new ArrayList();
		try {
			cl = new PluginClassLoader(ruta);
			archivos = ruta.list();
			
			if(archivos!=null) {
				for(int i=0; i<archivos.length; i++) {
					
					if(archivos[i].endsWith(".class")) {
						c = cl.loadClass(archivos[i].substring(0, archivos[i].indexOf(".")));
						
						PluginFunction pf = (PluginFunction) c.getDeclaredConstructor().newInstance();
						plugins.add(pf);
						
						System.out.println("nombre clase: "+archivos[i]+" - clase cargada: "+pf.getPluginName());
					}
					
				}
			}else {
				System.out.print("NULO");
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String[] getNombresPlugins() {
		return archivos;
	}
	
	public void runPlugin(int num1, int num2, String operacion) {
		Iterator it = plugins.iterator();
		PluginFunction plug;
		String nombre_plug;
		
		while(it.hasNext()) {
			plug=(PluginFunction) it.next();
			
			nombre_plug=plug.getPluginName();
			//System.out.println("comp: "+nombre_plug+" - "+operacion);
			if(operacion.contentEquals(nombre_plug)) {
				//System.out.println("entra");
				plug.setParameter(num1, num2);
				
				//Si hubo error en los parametros
				if(plug.hasError()) {
					ultima_operacion_error=true;
					descrip_error=plug.descripcionError();
				}else {
					ultimo_resultado=plug.getResult();
					ultima_operacion_error=false;
					descrip_error="";
				}
				
				
			}
			
		}

	}
	
	public int getUltimoResultado() {
		return ultimo_resultado;
	}
	
	public boolean getUltimaOperacionError() {
		return ultima_operacion_error;
	}
	
	public String getDescripcionError() {
		return descrip_error;
	}
}



