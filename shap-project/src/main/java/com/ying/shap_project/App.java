package com.ying.shap_project;

import java.util.Scanner;

import com.ying.shap_project.utils.ObjectUtils;

/**
 * 
 * @author Ying
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Shape application");
		System.out.println("Please type \"sp help\" to see more details.");
		Scanner scanner = new Scanner(System.in);

		while (true) {
			parseCommand(scanner.nextLine().trim().toLowerCase());
		}
	}

	private static void parseCommand(String command) {
		// ShapeManager shapeManager = new ShapeManager();

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

		} catch (Exception e) {
			System.out.println(CommandConstants.COMMAND_ALERT_UNKNOWN_EXCEPTION);
		}
	}

	private static void inputByFile(String[] commandArguments) {
		System.out.println("InputByFile");
	}

	private static void inputByCommand(String[] commandArguments) {
		System.out.println("InputByCommand");
	}

	private static void searchByPointCommand(String[] commandArr) {
		System.out.println("searchByPointCommand");
	}

	private static void exit() {
		System.exit(0);
	}

	private static void help() {
		System.out.println("help");
	}

	private static boolean isEmptyCommand(String[] commandArguments) {
		return commandArguments.length <= 0 || !commandArguments[0].equals(CommandConstants.COMMAND_ROOT);
	}

	private static boolean isHelpCommand(String[] commandArguments) {
		if (commandArguments.length == 2 && commandArguments[1].equals(CommandConstants.COMMAND_HELP))
			return true;

		if (commandArguments.length == 1 && commandArguments[0].equals(CommandConstants.COMMAND_ROOT))
			return true;

		return false;
	}

	private static boolean isExitCommand(String[] commandArguments) {
		return commandArguments.length == 2 && commandArguments[1].equals(CommandConstants.COMMAND_EXIT);
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
}
