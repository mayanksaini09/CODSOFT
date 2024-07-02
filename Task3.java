import java.util.*;

public class QuizApp {
    private static final int TIME_LIMIT = 10; // Time limit for each question in seconds
    private static final Scanner scanner = new Scanner(System.in);
    
    static class Question {
        String questionText;
        List<String> options;
        int correctOptionIndex;

        public Question(String questionText, List<String> options, int correctOptionIndex) {
            this.questionText = questionText;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
        }
    }

    public static void main(String[] args) {
        List<Question> questions = createQuestions();
        int score = 0;
        List<String> results = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            boolean answeredCorrectly = askQuestion(i + 1, question);
            if (answeredCorrectly) {
                score++;
                results.add("Question " + (i + 1) + ": Correct");
            } else {
                results.add("Question " + (i + 1) + ": Incorrect");
            }
        }

        displayResults(score, questions.size(), results);
    }

    private static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question(
            "What is the capital of France?",
            Arrays.asList("1. Berlin", "2. Madrid", "3. Paris", "4. Rome"),
            2
        ));

        questions.add(new Question(
            "What is the largest planet in our solar system?",
            Arrays.asList("1. Earth", "2. Jupiter", "3. Mars", "4. Saturn"),
            1
        ));

        questions.add(new Question(
            "Who wrote 'To Kill a Mockingbird'?",
            Arrays.asList("1. Harper Lee", "2. J.K. Rowling", "3. Ernest Hemingway", "4. Mark Twain"),
            0
        ));

        return questions;
    }

    private static boolean askQuestion(int questionNumber, Question question) {
        System.out.println("Question " + questionNumber + ": " + question.questionText);
        for (String option : question.options) {
            System.out.println(option);
        }

        System.out.println("You have " + TIME_LIMIT + " seconds to answer.");
        long startTime = System.currentTimeMillis();
        String answer = "";
        boolean timedOut = false;

        while ((System.currentTimeMillis() - startTime) / 1000 < TIME_LIMIT && !timedOut) {
            if (scanner.hasNextLine()) {
                answer = scanner.nextLine();
                timedOut = true;
            }
        }

        if (!timedOut) {
            System.out.println("Time's up! You did not answer in time.");
            return false;
        }

        int selectedOption;
        try {
            selectedOption = Integer.parseInt(answer.trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Answer should be a number.");
            return false;
        }

        if (selectedOption == question.correctOptionIndex) {
            System.out.println("Correct!");
            return true;
        } else {
            System.out.println("Incorrect. The correct answer was: " + (question.correctOptionIndex + 1));
            return false;
        }
    }

    private static void displayResults(int score, int totalQuestions, List<String> results) {
        System.out.println("\nQuiz Over!");
        System.out.println("Your score: " + score + "/" + totalQuestions);
        System.out.println("\nSummary:");
        for (String result : results) {
            System.out.println(result);
        }
    }
}
