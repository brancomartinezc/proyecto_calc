package logica;

public class Resta implements PluginFunction{
	
	@Override
	public String getPluginName() {
		return "Resta";
	}

	@Override
	public float doOperation(int num1, int num2){
		return num1-num2;
	}

}