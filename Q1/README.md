# Polynomial.java

The Polynomial class can be used to generate any degree Polynomial equation of the form:

## Ax<sup>n</sup> +Bx<sup>n-1</sup> … + Cx + D

Eg: 	2x + 1 or <br/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3x<sup>3</sup> - 4x or <br/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 10.98x<sup>100</sup> – 0.98x<sup>50</sup> + 10 <br/>
      
The Polynomial class make use of Map<Integer, Double> to store the equation, where Ax<sup>B</sup> is <br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Key(B) => degree of x <br/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Value(A) => coefficient value for x <br/>
  
The reason to use Map was because of its O(1) time to insert, delete and update data using HashMap. Also, using TreeMap we can sort the Polynomial equation based on its Key, which is helpful to Iterate when doing Addition, Subtraction or Multiplication operations. <br/>
<br/>The Polynomial class has 3 constructors
 * `Default Constructor : Polynomial()` - This is used to create Empty Polynomial.
 * `Polynomial(double[] coefficient)` - This constructor takes input in the form of an array of type double. These values are the coefficient values and index is the degree.
 * `Polynomial(Map<Integer, Double> poly)` - This constructor is used to directly apply the given Map containing Key Value pair to form Polynomial Equation.

 `Operations supported by this class:`
* Addition of 2 Polynomial
* Subtraction of 2 Polynomial
* Multiplication of 2 Polynomial
* Checking if 2 Polynomials are same
* Solving the Polynomial Equation for a given value of X

For more details, Please see Polynomial.java and Javadocs.
