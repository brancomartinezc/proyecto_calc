package logica;

public class Potencia implements PluginFunction{
	
	@Override
	public String getPluginName() {
		return "Potencia";
	}

	@Override
	public float doOperation(int num1, int num2){
		return (float) Math.pow(num1,num2);
	}
	
}