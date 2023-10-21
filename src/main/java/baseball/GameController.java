package baseball;

import java.util.List;

public class GameController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void start() {
        outputView.printStart();
        run();
    }

    private void run() {
        Retry retry = Retry.DEFAULT;
        while (retry != Retry.END) {
            Balls answerBalls = generateAnswerBalls();
            GameStatus gameStatus = GameStatus.PLAYING;
            play(answerBalls, gameStatus);
            retry = inputView.scanRetry();
        }
    }

    private void play(Balls answerBalls, GameStatus gameStatus) {
        while (gameStatus == GameStatus.PLAYING) {
            Balls playerBalls = inputView.scanBalls();
            GameResult gameResult = playerBalls.getTryResultList(answerBalls);
            outputView.printResult(gameResult);
            gameStatus = gameResult.checkGameWin();
        }
        outputView.printGameOver();
    }


    private Balls generateAnswerBalls() {
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        BallsGenerator randomBallsGenerator = new RandomBallsGenerator();
        List<Ball> answerBalls = randomBallsGenerator.generate(numberGenerator);
        return new Balls(answerBalls);
    }

    Balls generateAnswerBallsTest() {
        return generateAnswerBalls();
    }
}
