package nlp;

public class phrase {
	public double info_entropy;
	public String name;
	
	public phrase(double info_entropy, String name){
	this.info_entropy=info_entropy;
	this.name=name;
	}
	public double getinfo_entropy() {
		return info_entropy;
	}

	public String getname() {
		return name;
	}
	public int compareTo(phrase other) {
		// TODO Auto-generated method stub
		double info_entropy2 = other.getinfo_entropy();
		// both lists are of same size, because itemsets are sorted within the same L cycle
		if (this.info_entropy > info_entropy2)
			return 1;
		
		else{
			return 0;
		}
		}
}

