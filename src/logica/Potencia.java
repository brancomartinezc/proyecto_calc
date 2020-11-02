package logica;

public class Potencia implements PluginFunction{
	
	private int num1,num2;
	private boolean error=false;
	
	@Override
	public String getPluginName() {
		return "Potencia";
	}

	@Override
	public void setParameter(int n1, int n2) {
		num1=n1;
		num2=n2;
	}

	@Override
	public float getResult() {
		return (float) Math.pow(num1,num2);
	}

	@Override
	public boolean hasError() {
		return error;
	}
	
	@Override
	public String descripcionError() {
		return "";
	}
}