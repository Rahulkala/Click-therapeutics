import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
*	Polynomial class
*	Degree = Can only be Positive Integer
*	Coefficient = Can be any Real number
*/
public class Polynomial{
	
	private int size;
	private int degree;
	/*
		equation is a map which contains the coefficient and power value
		key = power or degree
		value = coefficient
	*/
	private Map<Integer, Double> equation;		
	
	/**
	 * Default Constructor
	 * Sets Equation to TreeMap and size = 0
	 */
	Polynomial(){
		this.equation = new TreeMap<Integer, Double>();
		this.size=0;
	}
	/**
	 * Constructor using double array containing coefficient value
	 * @param coefficient double array containing coefficient value for the polynomial 
	 * 
	 * Assuming the highest degree is at 0 index
	 *	
	 *	Eg 1: coefficient = {1,4.3,0,5}
	 *		=> 5x^3 + 4.3x + 1
	 *	
	 *	Eg 2: coefficient = {-1,2,-3,0,-1}
	 *		=> -x^4 -3x^2 + 2x - 1
	 */
	Polynomial(double[] coefficient){
		
		this.size = coefficient.length;
		create(coefficient);
	}
	
	/**
	 * Constructor using Map to create Polynomial
	 * @param poly Map containing degree as key and coefficient as value
	 */
	Polynomial(Map<Integer, Double> poly){
		
		this.setEquation(poly);
		TreeMap<Integer, Double> p = new TreeMap<>(poly);
		this.setDegree(p.lastKey());
		this.size = poly.size();
	}
	
	/**
	 * Private method to convert double array to Map and set as Equation of Polynomial
	 * @param coefficient double array containing coefficient value for the polynomial
	 */
	private void create(double[] coefficient){
		
		Map<Integer, Double> equation = new HashMap<>();
		int i = 0;
		int d = 0;
		for(double coeff: coefficient) {
			
			if(coeff==0) {
				i++;
				continue;
			}
			if(d<i)
				d=i;
			equation.put(i++,coeff);
		}	
		this.setDegree(d);
		this.setEquation(equation);
	}
	
	/**
	 * private method to set highest degree of Polynomial
	 * @param degree int value
	 */
	private void setDegree(int degree) {
		this.degree = degree;
	}
	/**
	 * To fetch Degree of Polynomial
	 * @return int value
	 */
	public int getDegree() {
		return this.degree;
	}
	/**
	 * private setter method for Equation
	 * @param equation Map<Integer, Double>
	 */
	private void setEquation(Map<Integer, Double> equation){
		this.equation = equation;
	}
	/**
	 * getter method for Equation
	 * @return Map<Integer, Double>
	 */
	public Map<Integer, Double> getEquation(){
		return this.equation;
	}
	
	/**
	 * setter method for size
	 * @param size int
	 */
	public void setSize(int size){
		this.size = size;
	}
	/**
	 * getter method to fetch Size of the Polynomial
	 * @return length of Polynomial
	 */
	public int getSize(){
		return this.size;
	}
	
	/**
	 * Overridden equals method to check if two Polynomial are same or not
	 */
	@Override
	public boolean equals(Object o){
		
		if(o == null)
			return false;
		if(!(o instanceof Polynomial))
			return false;
		Polynomial p1 = (Polynomial)o;
		
		// Check if the 2 are of same size
		if(this.getSize() != p1.getSize())		
			return false;
		TreeMap<Integer, Double> equation1 = new TreeMap<>(this.getEquation());
		TreeMap<Integer, Double> equation2 = new TreeMap<>(p1.getEquation());
		
		// Checking the coefficient for each degree
		// If not same, return false
		Iterator<Integer> key = equation1.keySet().iterator();
		while(key.hasNext()){
			
			int k = key.next().intValue();
			if(!equation2.containsKey(k))
				return false;
			else if(equation2.containsKey(k) && equation2.get(k).doubleValue()!=equation1.get(k).doubleValue())
				return false;
		}
		return true;
	}
	
	/**
	 * Method to display Polynomial Equation
	 */
	public void displayEquation(){
		
		TreeMap<Integer, Double> equation = new TreeMap<>(this.getEquation());
		List<Integer> listKey = new ArrayList<>(equation.keySet());
		Collections.reverse(listKey);
		Iterator<Integer> key = listKey.iterator();
		int i=0;
		while(key.hasNext()) {
			
			int k = key.next().intValue();
			if(i==0)
				System.out.print(equation.get(k).doubleValue());
			else {
				
				if(equation.get(k).doubleValue()>0)
					System.out.print(" +"+equation.get(k).doubleValue());
				else
					System.out.print(" "+equation.get(k).doubleValue());
			}
			if(i!=equation.size()-1)
				System.out.print("x^"+k);
			i++;
		}
		System.out.println();
	}
	
	/**
	 * Method to input value of x to solve the Polynomial Equation
	 * @param x double value for x in polynomial equation
	 * @return double value denoting the solution for given x value
	 */
	public double solveEquationForX(double x) {
		
		Map<Integer, Double> equation = this.getEquation();
		Iterator<Integer> key = equation.keySet().iterator();
		double sum = 0;
		while(key.hasNext()) {
			
			int power = key.next().intValue();
			sum+= Math.pow(x, power)*equation.get(power);
		}
		return sum;
	}
	
	// 
	// 
	/**
	 * Addition operation between 2 Polynomial equations
	 * @param p Second Polynomial to be added to current Polynomial
	 * @return New Polynomial containing sum of the 2 given Polynomials
	 * 
	 * Example p1.addition(p2) => p1 + p2
	 */
	public Polynomial addition(Polynomial p) {
		
		TreeMap<Integer, Double> e1 = new TreeMap<>(this.getEquation());
		TreeMap<Integer, Double> e2 = new TreeMap<>(p.getEquation());
		Iterator<Integer> key1 = e1.keySet().iterator();
		Iterator<Integer> key2 = e2.keySet().iterator();
		int size = this.getSize()>p.getSize()?this.getSize():p.getSize();
		double[] newEq = new double[size];
		
		while(key1.hasNext() && key2.hasNext()) {
			
			int k1 = key1.next().intValue();
			int k2 = key2.next().intValue();
			if(k1!=k2) {

				while(k1<k2) {
					newEq[k1] = e1.get(k1).doubleValue();
					if(!key1.hasNext())
						break;
					k1 = key1.next().intValue();
				}
				while(k2<k1) {
					newEq[k2] = e2.get(k2).doubleValue();
					if(!key2.hasNext())
						break;
					k2 = key2.next().intValue();
				}
			}	
			if(k1==k2) {
				newEq[k1] = e1.get(k1).doubleValue() + e2.get(k1).doubleValue();
			}
		}
		Polynomial newPoly = new Polynomial(newEq);
		return newPoly;
	}
	
	/**
	 * Subtraction operation between 2 Polynomial equations
	 * @param p Second Polynomial to be subtracted from current Polynomial
	 * @return New Polynomial containing difference of the 2 given Polynomials
	 * 
	 * Example: p1.subtraction(p2) => p1 - p2
	 */
	public Polynomial subtraction(Polynomial p) {
		
		TreeMap<Integer, Double> e1 = new TreeMap<>(this.getEquation());
		TreeMap<Integer, Double> e2 = new TreeMap<>(p.getEquation());
		Iterator<Integer> key1 = e1.keySet().iterator();
		Iterator<Integer> key2 = e2.keySet().iterator();
		int size = this.getSize()>p.getSize()?this.getSize():p.getSize();
		double[] newEq = new double[size];
		
		while(key1.hasNext() && key2.hasNext()) {
			
			int k1 = key1.next().intValue();
			int k2 = key2.next().intValue();
			if(k1!=k2) {
				
				while(k1<k2) {
					newEq[k1] = e1.get(k1).doubleValue();
					if(!key1.hasNext())
						break;
					k1 = key1.next().intValue();
				}
				while(k2<k1) {
					newEq[k2] = -e2.get(k2).doubleValue();
					if(!key2.hasNext())
						break;
					k2 = key2.next().intValue();
				}
			}
			if(k1==k2) {
				newEq[k1] = e1.get(k1).doubleValue() - e2.get(k1).doubleValue();
			}
		}
		Polynomial newPoly = new Polynomial(newEq);
		return newPoly;
	}
	
	/**
	 * Multiplication operation between 2 Polynomial equations
	 * @param p Second Polynomial to be multiplied to current Polynomial
	 * @return New Polynomial formed from multiplication of the 2 given Polynomials
	 * 
	 * Example: p1.multiply(p2) => p1 * p2
	 */
	public Polynomial multiply(Polynomial p) {
		
		TreeMap<Integer, Double> e1 = new TreeMap<>(this.getEquation());
		TreeMap<Integer, Double> e2 = new TreeMap<>(p.getEquation());
		Integer[] key1 = e1.keySet().toArray(new Integer[e1.size()]);
		Integer[] key2 = e2.keySet().toArray(new Integer[e2.size()]);
		
		TreeMap<Integer, Double> ans = new TreeMap<>();
		for(int i=0;i<e1.size();i++) {
			
			for(int j=0;j<e2.size();j++) {
				
				int degree = key1[i] + key2[j];
				double coeff = e1.get(key1[i])*e2.get(key2[j]);
				if(ans.containsKey(degree)) {
					
					ans.put(degree, ans.get(degree)+coeff);
				}
				else {
					ans.put(degree, coeff);
				}
			}
		}
		Polynomial product = new Polynomial(ans);
		return product;
	}
}