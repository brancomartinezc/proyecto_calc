package logica;

public class Division implements PluginFunction{
	
	@Override
	public String getPluginName() {
		return "Division";
	}

	@Override
	public float doOperation(int num1, int num2){
		float res;
		
		if(num2!=0) {
			res=(float) num1/num2;
		}else {
			throw new ArithmeticException();
		}
		
		return res;
	}
	
}