package logica;
import java.io.*;

import javax.swing.JOptionPane;

public class PluginClassLoader extends ClassLoader{
	private File ruta;
	
	public PluginClassLoader(File ruta) {
		this.ruta=ruta;
	}
	
	public Class loadClass (String nombre) throws ClassNotFoundException { 
		return loadClass(nombre, true); 
    }
	
	public Class loadClass(String nombre_clase, boolean resolve) throws ClassNotFoundException {
		Class c;
		String nombre_archivo;
		File f;
		int longitud;
		byte[] classbytes;
		DataInputStream in;
		
		try {
			c = findLoadedClass(nombre_clase);
			
			if(c==null) {
				try {
					c= findSystemClass(nombre_clase);
				}catch(Exception e) {}
			}
			
			if(c==null) {
				//JOptionPane.showMessageDialog(null, "cargando clase: "+nombre_clase);
				nombre_archivo= nombre_clase.replace('.', File.separatorChar)+".class";
				
				f= new File(ruta,nombre_archivo);
				
				longitud= (int)f.length();
				classbytes= new byte[longitud];
				in= new DataInputStream(new FileInputStream(f));
				in.readFully(classbytes);
				in.close();
				//JOptionPane.showMessageDialog(null, "defineClass: "+nombre_clase);
				c=defineClass(null, classbytes, 0, longitud);
				//JOptionPane.showMessageDialog(null, "Sale de defineClass: "+nombre_clase);
			}
			
			if(resolve) {
				resolveClass(c);
			}
			
			return c;
			
		}catch(Exception e) {
			throw new ClassNotFoundException(e.toString());
		}
		
	}
	
	
}
