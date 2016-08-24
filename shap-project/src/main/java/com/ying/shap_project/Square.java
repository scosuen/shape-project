package com.ying.shap_project;

import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.ObjectUtils;
import com.ying.shap_project.utils.math.Point;

public class Square extends Rectangle {
	
	public Square () {
		
	}
	
	public Square (List<String> args) {
		initializedShape(args);
	}
	
	@Override
	public boolean validateArgs(List<String> args) {
		if (args.size() != 4) {
			throw new IllegalShapeArgumentException("Wrong number of square arguments. Needs 4 but " + args.size() + " " + args);
		}
		
		for (int i = 1 ; i < args.size() ; i ++) {
			if (!ObjectUtils.isNumber(args.get(i))) {
				throw new IllegalShapeArgumentException(args.get(i) + " is not a number. " + args);
			}
		}
		if (Double.valueOf(args.get(3)) <= 0) 
			throw new IllegalShapeArgumentException("Side length (" + args.get(3) + ") can not be negative or 0. " + args);
		
		return true;
	}
	
	@Override
	public void convertArgs(List<String> args) {
		setLocatedPoint(new Point(Double.valueOf(args.get(1)), Double.valueOf(args.get(2))));
		setSideXLength(Double.valueOf(args.get(3)));
		setSideYLength(Double.valueOf(args.get(3)));
	}
	
	@Override
	public String printShape() {
		return getName() + ": square with one corner at(" + getLocatedPoint().getX() + ", " + getLocatedPoint().getY()+ ") and side " + getSideXLength();
	}

}
