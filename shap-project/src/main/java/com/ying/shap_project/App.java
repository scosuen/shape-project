package com.ying.shap_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.exception.NoSuchShapeTypeException;
import com.ying.shap_project.service.ShapeService;
import com.ying.shap_project.utils.ObjectUtils;

/**
 * 
 * @author Ying
 *
 */
public class App {
	private static ShapeService shapeService = new ShapeService();

	public static void main(String[] args) {
		System.out.println(CommandConstants.COMMAND_WELCOME);
		Scanner scanner = new Scanner(System.in);

		while (true)
			parseCommand(scanner.nextLine().trim().toLowerCase());
	}

	private static void parseCommand(String command) {
		try {
			if (command.length() <= 0) {
				System.out.println(CommandConstants.COMMAND_ALERT_PLEASE_ENTER_A_COMMAND);
				return;
			}

			String[] commandArguments = command.split("\\s+");

			if (isEmptyCommand(commandArguments))
				System.out.println(CommandConstants.COMMAND_ALERT_PLEASE_ENTER_A_CORRECT_COMMAND);
			else if (isHelpCommand(commandArguments))
				help();
			else if (isExitCommand(commandArguments))
				exit();
			else if (isInputByCommand(commandArguments))
				inputByCommand(commandArguments);
			else if (isInputByFile(commandArguments))
				inputByFile(commandArguments);
			else if (isSearchByPointCommand(commandArguments))
				searchByPointCommand(commandArguments);
			else
				System.out.println(CommandConstants.COMMAND_ALERT_PLEASE_ENTER_A_CORRECT_COMMAND);

		} catch (IllegalShapeArgumentException | NoSuchShapeTypeException | NullPointerException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(CommandConstants.COMMAND_ALERT_UNKNOWN_EXCEPTION);
		}
	}

	private static void inputByFile(String[] commandArguments) {
		shapeService.inputByFile(commandArguments[3]);
	}

	private static void inputByCommand(String[] commandArguments) {
		Shape shape = shapeService.inputByCommand(getArgumentsList(commandArguments, 3));
		System.out.println(shape == null ? "" : shape.printShape());
	}

	private static void searchByPointCommand(String[] commandArr) {
		List<Shape> shapes = shapeService.searchByPoint(Double.valueOf(commandArr[2]), Double.valueOf(commandArr[3]));

		if (shapes == null || shapes.size() <= 0)
			System.out.println("No result found.");
		else {
			double totalArea = 0;
			for (Shape shape : shapes) {
				double area = shape.getArea();
				totalArea += area;
				System.out.println(shape.printShape() + " Area: " + area);
			}
			System.out.println("The total area is: " + totalArea);
		}
	}

	private static void exit() {
		System.exit(0);
	}

	private static void help() {
		System.out.println("Shape Help");
		System.out.println("");
		System.out.println("Input:");
		System.out.printf("%3s%s%n", " ", "Exampels:");
		System.out.printf("%6s%s%n", " ", "sp input donut 4.5 7.8 1.5 -1.8 (same as: sp input donut -c 4.5 7.8 1.5 -1.8)");
		System.out.printf("%6s%s%n", " ", "sp input -c circle 1.7 -5.05 6.9");
		System.out.printf("%6s%s%n", " ", "sp input -f F:\\shaps.csv");
		System.out.printf("%3s%s%n", " ", "Main operation mode:");
		System.out.printf("%6s%s%n", " ", "-c   input by commmad.");
		System.out.printf("%6s%s%n", " ", "-f   input by file.");
		System.out.println("");
		System.out.println("Search:");
		System.out.printf("%3s%s%n", " ", "Exampels:");
		System.out.printf("%6s%s%n", " ", "sp search 1.2 3.3");
		System.out.println("");
		System.out.println("Help:");
		System.out.printf("%3s%s%n", " ", "sp");
		System.out.printf("%3s%s%n", " ", "sp help");
		System.out.println("");
		System.out.println("Exit:");
		System.out.printf("%3s%s%n", " ", "sp exit");
		System.out.println("");
		System.out.println("Shape input pattern:");
		System.out.println("Circle:");
		System.out.printf("%3s%s%n", " ", "circle centreX centreY radiues");
		System.out.printf("%3s%s%n", " ", "Example:");
		System.out.printf("%6s%s%n", " ", "circle 1.7 5.05 6.9");
		System.out.println("");
		System.out.println("Square:");
		System.out.printf("%3s%s%n", " ", "square lowerLeftCornerX lowerLeftCornerY sideLength");
		System.out.printf("%3s%s%n", " ", "Example:");
		System.out.printf("%6s%s%n", " ", "square 3.55 4.1 2.77");
		System.out.println("");
		System.out.println("Rectangle:");
		System.out.printf("%3s%s%n", " ", "rectangle lowerLeftCornerX lowerLeftCornerY sideXLength sidYLength");
		System.out.printf("%3s%s%n", " ", "Example:");
		System.out.printf("%6s%s%n", " ", "rectangle 3.5 2.0 5.6 7.2");
		System.out.println("");
		System.out.println("Triangle:");
		System.out.printf("%3s%s%n", " ", "triangle vertex1X vertex1Y vertex2X vertex2Y vertex3X vertex3Y");
		System.out.printf("%3s%s%n", " ", "Example:");
		System.out.printf("%6s%s%n", " ", "triangle 4.5 1 -2.5 -33 23 0.3");
		System.out.println("");
		System.out.println("Donut:");
		System.out.printf("%3s%s%n", " ", "donut centreX centreY smallRadiues bigRadiues");
		System.out.printf("%3s%s%n", " ", "Example:");
		System.out.printf("%6s%s%n", " ", "donut 4.5 7.8 1.5 1.8");
	}

	private static boolean isEmptyCommand(String[] commandArguments) {
		return commandArguments.length <= 0 || 
				!commandArguments[0].equals(CommandConstants.COMMAND_ROOT);
	}

	private static boolean isHelpCommand(String[] commandArguments) {
		if (commandArguments.length == 2 && 
				commandArguments[1].equals(CommandConstants.COMMAND_HELP))
			return true;

		if (commandArguments.length == 1 && 
				commandArguments[0].equals(CommandConstants.COMMAND_ROOT))
			return true;

		return false;
	}

	private static boolean isExitCommand(String[] commandArguments) {
		return commandArguments.length == 2 && 
				commandArguments[1].equals(CommandConstants.COMMAND_EXIT);
	}

	private static boolean isInputByCommand(String[] commandArguments) {
		return commandArguments.length >= 3 && 
				commandArguments[1].equals(CommandConstants.COMMAND_INPUT) && 
				commandArguments[2].equals(CommandConstants.COMMAND_ARG_C);
	}

	private static boolean isInputByFile(String[] commandArguments) {
		return commandArguments.length >= 3 && 
				commandArguments[1].equals(CommandConstants.COMMAND_INPUT) && 
				commandArguments[2].equals(CommandConstants.COMMAND_ARG_F);
	}

	private static boolean isSearchByPointCommand(String[] commandArguments) {
		if (commandArguments.length < 3)
			return false;

		if (!commandArguments[1].equals(CommandConstants.COMMAND_SEARCH))
			return false;

		if (!ObjectUtils.isNumber(commandArguments[2]) || !ObjectUtils.isNumber(commandArguments[3])) {
			System.out.println(CommandConstants.COMMAND_ALERT_PLEASE_ENTER_A_CORRECT_COMMAND);
			return false;
		}

		return true;
	}

	private static List<String> getArgumentsList(String[] commandArguments, int startIndex) {
		List<String> args = new ArrayList<String>();
		for (int i = startIndex; i < commandArguments.length; i++) {
			args.add(commandArguments[i]);
		}
		return args;
	}
}
