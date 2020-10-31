package logica;

public class Potencia implements PluginFunction{
	
	private int num1,num2;
	private boolean error=false;
	
	@Override
	public String getPluginName() {
		return "Suma";
	}

	@Override
	public void setParameter(int n1, int n2) {
		num1=n1;
		num2=n2;
		
		if(num2<0) {
			error=true;
		}
	}

	@Override
	public int getResult() {
		return (int) Math.pow(num1,num2);
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