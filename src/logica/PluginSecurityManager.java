package logica;

import java.io.File;

public class PluginSecurityManager extends SecurityManager{
	private File ruta;
	
	public PluginSecurityManager(File ruta) {
		this.ruta=ruta;
	}
	
	/*private void trusted() {
		if(inClassLoader()) {
			throw new SecurityException();
		}
	}*/
	
	
	
	
}
