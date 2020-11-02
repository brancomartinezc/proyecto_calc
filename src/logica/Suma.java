package logica;

public class Suma implements PluginFunction{
	
	private int num1,num2;
	
	@Override
	public String getPluginName() {
		return "Suma";
	}

	@Override
	public void setParameter(int n1, int n2) {
		num1=n1;
		num2=n2;
	}

	@Override
	public float getResult() {
		return num1+num2;
	}

	@Override
	public boolean hasError() {
		return false;
	}
	
	@Override
	public String descripcionError() {
		return "";
	}
	
}
