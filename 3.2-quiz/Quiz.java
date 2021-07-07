
public class Quiz {
    enum Result {CORRECT, WRONG, UNANSWERED}

    // Constants
    private static final String[] ANSWERS = {"c", "a", "b", "d", "b", "c", "c", "a"};
    private static final String NO_ANSWER = "x";
    private static final int ANSWERS_TO_PASS = 5;

    // Program state
    private static int correctCount;
    private static int wrongCount;
    private static int unansweredCount;

    /**
     * Asses the correctness of the given answer.
     *
     * @param answer answer to assess.
     * @param answerIndex index of the answer in {@link #ANSWERS}.
     * @return answer status.
     */
    private static Result assessAnswer(String answer, int answerIndex) {
        if (answer.equalsIgnoreCase(ANSWERS[answerIndex])) {
            return Result.CORRECT;
        }

        if (answer.equalsIgnoreCase(NO_ANSWER)) {
            return Result.UNANSWERED;
        }

        return Result.WRONG;
    }

    /**
     * Increment a program state counter corresponding to the given result.
     *
     * E.g. if the argument is {@link Result#CORRECT}, then {@link #correctCount} will be incremented.
     *
     * @param result the result to check against.
     */
    private static void updateCounters(Result result) {
        switch (result) {
            case CORRECT -> correctCount++;
            case WRONG -> wrongCount++;
            case UNANSWERED -> unansweredCount++;
        }
    }

    /**
     * @return true if the test was passed with enough correct answers, false otherwise.
     */
    private static boolean hasPassed() {
        return correctCount >= ANSWERS_TO_PASS;
    }

    public static void main(String[] args) {
        if (args.length != ANSWERS.length) {
            System.out.printf("Invalid number of answers: %d. Please provide exactly %d answers.%n", args.length, ANSWERS.length);
            System.exit(1);
        }

        // Tabulate the results for each answer.
        System.out.println("| Question | Submitted Ans. | Correct Ans. | Result     |");
        for (int i = 0; i < args.length; i++) {
            Result result = assessAnswer(args[i], i);
            updateCounters(result);
            System.out.printf("| %8d | %14s | %12s | %-10s |%n", i + 1, args[i].toUpperCase(), ANSWERS[i].toUpperCase(), result);
        }

        // Print final summary.
        System.out.printf("%-28s %d%n", "No. of correct answers:", correctCount);
        System.out.printf("%-28s %d%n", "No. of wrong answers:", wrongCount);
        System.out.printf("%-28s %d%n", "No. of questions unanswered:", unansweredCount);
        System.out.printf("The candidate %s.%n", hasPassed() ? "PASSED" : "FAILED");
    }
}
