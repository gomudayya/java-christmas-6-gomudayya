package validator;

public class CommonValidator {

    public static void validateIsInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
