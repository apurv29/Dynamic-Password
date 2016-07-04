package com.example.numericalpass.activities;

import net.objecthunter.exp4j.operator.Operator;


public class OpExtend extends Operator {
	
	public OpExtend(String symbol, int numberOfOperands,
			boolean leftAssociative, int precedence) {
		super(symbol, numberOfOperands, leftAssociative, precedence);
	}
	
	Operator div = new Operator("\u00F7", 2, true, Operator.PRECEDENCE_DIVISION) {

	    @Override
	    public double apply(double... args) {
	        final double arg1 = args[0];
	        final double arg2 = args[1];
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
	        if (arg2 == 0 || arg1 == 0) {
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
	        if (arg1 < 0) {
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
			if (arg2 == 0) {
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
			if (arg2 == 0) {
				throw new IllegalArgumentException("not allowed 0 as 2nd operand");
			}
			double resultmod = arg1 % arg2;
			return resultmod;
		}
	};

	@Override
	public double apply(double... arg0) {
		return 0;
	}
}
