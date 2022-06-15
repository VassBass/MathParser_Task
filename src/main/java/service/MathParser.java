package service;

import java.util.ArrayList;

/**
 * The class needed to get the result of equation string
 * @see #calculate(String equation)
 */
public class MathParser {

    /**
     * Checks the equation for the absence of incorrect characters and incorrect combinations of characters
     *
     * @param equation to be tested
     *
     * @return true if equation can be calculated by {@link #calculate(String)}
     */
    public static boolean equationIsCorrect(String equation){
        if (equation == null) return false;

        equation = prepare(equation);
        char[]chars = equation.toCharArray();
        boolean dotHasAlreadyBeen = false;
        int openParentheses = 0;
        for (int index = 0;index < chars.length;index++){
            if (Character.isLetter(chars[index])){
                return false;
            }else if (chars[index] == '.'){
                if (!Character.isDigit(chars[index-1]) && !Character.isDigit(chars[index+1])){
                    return false;
                }else if (dotHasAlreadyBeen){
                    return false;
                }else {
                    dotHasAlreadyBeen = true;
                }
            }else if (chars[index] == '+' || chars[index] == '*' || chars[index] == '/'){
                if (chars[index+1] == '-'){
                    if (!Character.isDigit(chars[index+2])) return false;
                }

                dotHasAlreadyBeen = false;
            }else if (chars[index] == '-'){
                if (!Character.isDigit(chars[index+1])){
                    if (chars[index+1] == '-' && !Character.isDigit(chars[index+2])) return false;
                    if (chars[index+1] == ')') return false;
                }

                dotHasAlreadyBeen = false;
            }else if (chars[index] == '('){
                if (Character.isDigit(chars[index-1])) return false;

                openParentheses++;
                dotHasAlreadyBeen = false;
            }else if (chars[index] == ')'){
                if (openParentheses == 0) return false;
                openParentheses--;
                dotHasAlreadyBeen = false;
            }
        }

        return openParentheses == 0;
    }

    /**
     * Calculate result of equation
     * Can identify symbols '+', '-', '*', '/'
     * Can identify integers and doubles (doubles with dots and commas)
     * Can identify nesting levels created by symbols '(', ')'
     *
     * @param equation to calculate
     *
     * @return String with result in double
     * null if equation == null or if equation has incorrect characters
     *
     * Can be transformed to double
     * @see Double#parseDouble(String)
     * @see #equationIsCorrect(String)
     */
    public static String calculate(String equation){
        if (equation == null){
            return null;
        }else equation = prepare(equation);
        if (equationIsCorrect(equation)){
            char[]chars = equation.toCharArray();
            int left = -1;
            int right = -1;
            for (int index = 0;index< chars.length;index++){
                if (chars[index] == '('){
                    left = index;
                    right = -1;
                }else if (chars[index] == ')' && right == -1){
                    right = index;
                }
            }

            if (left == -1){
                return parseDouble(equation);
            }else {
                String s = parseDouble(equation.substring(left+1, right));
                return calculate(equation.substring(0, left) + s + equation.substring(right+1));
            }
        }else return null;
    }

    /**
     * Calculates the result of an equation without nesting level
     *
     * @param equation without parentheses
     *
     * @return result of equation (double in String)
     * null if equation == null
     *
     * @throws NumberFormatException if there are incorrect characters in the equation
     */
    private static String parseDouble(String equation){
        return equation == null ? null : secondaryActions(priorityActions(prepare(equation)));
    }

    /**
     * This method removes from String all spaces and replaces commas to dots
     * if the first character in the equation is '+' it will be removed
     * @see #parseDouble(String)
     *
     * @param equation before transformation
     *
     * @return String without spaces and with dots instead of commas
     * null if equation == null
     */
    public static String prepare(String equation){
        if (equation != null) {
            char[] chars = equation.toCharArray();
            ArrayList<Character> result = new ArrayList<>();
            for (int index = 0;index < chars.length; index++) {
                if (result.size() == 0 && chars[index] == '+') {
                    chars[index] = ' ';
                    --index;
                }else if (chars[index] == ',') {
                    result.add('.');
                } else if (chars[index] != ' ') {
                    result.add(chars[index]);
                }
            }

            StringBuilder builder = new StringBuilder(result.size());
            for (char c : result) builder.append(c);

            return builder.toString();
        }else return null;
    }

    /**
     * The method performs priority actions like "*" and "/"
     *
     * @param equation MUST! be after {@link #prepare(String)}
     *
     * @return equation after priority actions
     * null if equation == null
     *
     * @throws NumberFormatException if there are incorrect characters in the equation
     */
    private static String priorityActions(String equation){
        if (equation != null) {
            char[]chars = equation.toCharArray();

            int index = 0;
            int left = 0;
            int right = chars.length;
            Double result = null;
            while (index < chars.length) {
                if (chars[index] == '*' || chars[index] == '/') {

                    left = index-1;
                    while (true) {
                        if (left < 0 || chars[left] == '*' || chars[left] == '/' || chars[left] == '+') {
                            left++;
                            break;
                        } else if (chars[left] == '-') {
                            if (left-1 < 0) break;

                            if (chars[left - 1] != '+' && chars[left - 1] != '-'
                                    && chars[left - 1] != '*' && chars[left - 1] != '/') {
                                left++;
                            }
                            break;
                        } else left--;
                    }
                    double leftNumber = Double.parseDouble(equation.substring(left, index));

                    right = index+1;
                    while (true) {
                        if (right == chars.length
                                || chars[right] == '*' || chars[right] == '/' || chars[right] == '+') {
                            break;
                        } else if (chars[right] == '-') {
                            if (chars[right - 1] == chars[index]) {
                                right++;
                            }else break;
                        } else right++;
                    }
                    double rightNumber = Double.parseDouble(equation.substring(index+1, right));

                    if (chars[index] == '*'){
                        result = leftNumber * rightNumber;
                    }else {
                        result = leftNumber / rightNumber;
                    }

                    break;
                } else index++;
            }

            if (result == null){
                return equation;
            }else {
                String r = equation.substring(0, left) + result + equation.substring(right);
                return priorityActions(r);
            }
        } else return null;
    }


    /**
     * The method performs secondary actions like "+" and "-"
     *
     * @param equation MUST! be after {@link #prepare(String)}
     * For the correct solution of the equation it is necessary to perform after {@link #priorityActions(String)}
     *
     * @return equation after secondary actions
     * null if equation == null
     *
     * @throws NumberFormatException if there are incorrect characters in the equation
     */
    private static String secondaryActions(String equation){
        if (equation != null) {
            char[]chars = equation.toCharArray();

            int index = 0;
            int left = 0;
            int right = chars.length;
            Double result = null;
            while (index < chars.length) {
                if (chars[index] == '+' || (index > 0 && chars[index] == '-')) {
                    left = index-1;
                    while (true) {
                        if (left < 0 || chars[left] == '+') {
                            left++;
                            break;
                        } else if (chars[left] == '-') {
                            if (left-1 < 0) break;

                            if (chars[left - 1] != '+' && chars[left - 1] != '-') {
                                left++;
                            }
                            break;
                        } else left--;
                    }
                    double leftNumber = Double.parseDouble(equation.substring(left, index));

                    right = index+1;
                    while (true) {
                        if (right == chars.length || chars[right] == '+') {
                            break;
                        } else if (chars[right] == '-') {
                            if (chars[right - 1] == chars[index]) {
                                right++;
                            }else break;
                        } else right++;
                    }
                    double rightNumber = Double.parseDouble(equation.substring(index+1, right));

                    if (chars[index] == '+'){
                        result = leftNumber + rightNumber;
                    }else {
                        result = leftNumber - rightNumber;
                    }

                    break;
                } else index++;
            }

            if (result == null){
                return equation;
            }else {
                String r = equation.substring(0, left) + result + equation.substring(right);
                return secondaryActions(r);
            }
        } else return null;
    }
}
