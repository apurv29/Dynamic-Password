package com.example.numericalpass;

import android.widget.Toast;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.operator.Operator;


public class OpExtend extends Operator {
	
	public OpExtend(String symbol, int numberOfOperands,
			boolean leftAssociative, int precedence) {
		super(symbol, numberOfOperands, leftAssociative, precedence);
		// TODO Auto-generated constructor stub
	}
	
	Operator div = new Operator("\u00F7", 2, true, Operator.PRECEDENCE_DIVISION) {

	    @Override
	    public double apply(double... args) {
	        final double arg1 = args[0];
	        final double arg2 = args[1];
	       /* if ((double) arg1 != args[0] && (double) arg2 != args[1]) {
	            throw new IllegalArgumentException("Operand for division has to be an integer");
	        }*/

				if (arg2 == 0) {

					throw new IllegalArgumentException("The operand of the division can not be equal to zero");
				}


	       double resultdiv = arg1/arg2;
	        return resultdiv;
	    }
	};

	Operator mul = new Operator("\u00D7", 2, true, Operator.PRECEDENCE_MULTIPLICATION) {

	    @Override
	    public double apply(double... args) {
	        final double arg1 = args[0];
	        final double arg2 =  args[1];
	      /*  if ((double) arg1 != args[0] && (double) arg2 != args[1]) {
	            throw new IllegalArgumentException("Operand for division has to be an integer");
	        }*/
	        if (arg2 == 0 || arg1 == 0) {
	            //throw new IllegalArgumentException("The operand of the division can not be equal to zero");
	        	return 0.0;
	        }
	       double resultmul = arg1*arg2;
	        return resultmul;
	    }
	};
	Operator sqroot = new Operator("\u221A", 1, true, Operator.PRECEDENCE_POWER+1) {

	    @Override
	    public double apply(double... args) {
	        final double arg1 = args[0];
	       // final int arg2 = (int) args[1];
	      /*  if ((double) arg1 != args[0] && (double) arg2 != args[1]) {
	            throw new IllegalArgumentException("Operand for division has to be an integer");
	        }*/
	        if (arg1 < 0) {
	            //throw new IllegalArgumentException("The operand of the division can not be equal to zero");
	        	return 0.0;
	        }
	       double resultsqroot = Math.sqrt(arg1);
	        return resultsqroot;
	    }
	};
	Operator powsymbol = new Operator("\u005E", 2, true, Operator.PRECEDENCE_POWER) {

		@Override
		public double apply(double... args) {
			final double arg1 = args[0];
			final double arg2 = args[1];
			// final int arg2 = (int) args[1];
	      /*  if ((double) arg1 != args[0] && (double) arg2 != args[1]) {
	            throw new IllegalArgumentException("Operand for division has to be an integer");
	        }*/
			if (arg2 == 0) {
				//throw new IllegalArgumentException("The operand of the division can not be equal to zero");
				return 1.0;
			}
			double resultpow = Math.pow(arg1, arg2);
			return resultpow;
		}
	};
	Operator modulo = new Operator("%", 2, true, Operator.PRECEDENCE_POWER) {

		@Override
		public double apply(double... args) {
			final double arg1 = args[0];
			final double arg2 = args[1];
			// final int arg2 = (int) args[1];
	      /*  if ((double) arg1 != args[0] && (double) arg2 != args[1]) {
	            throw new IllegalArgumentException("Operand for division has to be an integer");
	        }*/
			if (arg2 == 0) {
				throw new IllegalArgumentException("not allowed 0 as 2nd operand");
				//return 1.0;
			}
		//	int dvd = (int)(arg1/arg2);
			//double resultmod = arg2 - (arg1*dvd);
			double resultmod = arg1 % arg2;
			return resultmod;
		}
	};

	

/*	double resultdiv = new ExpressionBuilder("3\u00F72")
	        .operator(div)
	        .build()
	        .evaluate();*/

	//double expected = 6d;
	//assertEquals(expected, result, 0d);


	@Override
	public double apply(double... arg0) {
		// TODO Auto-generated method stub
		return 0;
	}}
