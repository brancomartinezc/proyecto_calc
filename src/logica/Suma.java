package logica;

public class Suma implements PluginFunction{
	
	@Override
	public String getPluginName() {
		return "Suma";
	}

	@Override
	public float doOperation(int num1, int num2){
		return num1+num2;
	}
	
}
