package logica;

public class Division implements PluginFunction{
	
	private int num1,num2;
	private boolean error=false;
	private String error_descripcion="";
	
	@Override
	public String getPluginName() {
		return "Division";
	}

	@Override
	public void setParameter(int n1, int n2) {
		error=false;
		num1=n1;
		num2=n2;
		
		if(n2==0) {
			error=true;
			error_descripcion="Division por 0.";
		}else if(n2>n1) {
			error=true;
			error_descripcion="Divisor mas grande que dividendo.";
		}
	}

	@Override
	public int getResult() {
		return num1/num2;
	}
	
	@Override
	public boolean hasError() {
		return error;
	}
	
	@Override
	public String descripcionError() {
		return error_descripcion;
	}
	
}