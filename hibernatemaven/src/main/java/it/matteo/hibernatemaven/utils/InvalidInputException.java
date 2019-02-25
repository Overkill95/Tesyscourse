package it.matteo.hibernatemaven.utils;

public class InvalidInputException extends Exception {
	public InvalidInputException(String error) {
		super(error);
	}
}
