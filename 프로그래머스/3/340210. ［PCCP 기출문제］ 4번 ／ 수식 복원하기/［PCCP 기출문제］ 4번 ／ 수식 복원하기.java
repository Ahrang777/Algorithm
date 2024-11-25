import java.util.*;

class Solution {
    static List<String> incomplete;
    public String[] solution(String[] expressions) {
        incomplete = new ArrayList<>();
        for(String expression : expressions) {
            if(expression.charAt(expression.length()-1)=='X') {
                incomplete.add(expression);
            }
        }
        List<Integer> radixList = new ArrayList<>();
        for(int radix = 2; radix<10; radix++) {
            if(validate(radix,expressions)) {
                radixList.add(radix);
            }
        }
        String[] answer = new String[incomplete.size()];
        for(int i = 0; i<incomplete.size(); i++) {
            String expression = incomplete.get(i);
            String[] splits = expression.split("\\s*=\\s*");
            Set<String> resultSet = new HashSet<>();
            String result = "";
            for(int radix : radixList) {
                try {
                    int value = calc(splits[0], radix);
                    result = Integer.toString(value,radix);
                    resultSet.add(result);
                } catch(NumberFormatException e) {
                    continue;
                }
            }
            if(resultSet.size()==1) {
                answer[i] = splits[0] + " = " + result;
            } else {
                answer[i] = splits[0] + " = ?";
            }
        }

        return answer;
    }

    private boolean validate(int radix, String[] expressions) {
        for(String expression : expressions) {
            try {
                String[] splits = expression.split("\\s*=\\s*");
                int result = calc(splits[0], radix);
                if(!splits[1].equals("X")) {
                    int expected = Integer.parseInt(splits[1],radix);
                    if(result!=expected) {
                        return false;
                    }
                }
            } catch(NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private int calc(String expression, int radix) {
        String[] splits = expression.split(" ");
        int operand1 = Integer.parseInt(splits[0],radix);
        String operator = splits[1];
        int operand2 = Integer.parseInt(splits[2],radix);

        if(operator.equals("+")) {
            return operand1 + operand2;
        } else {
            return operand1 - operand2;
        }
    }
}