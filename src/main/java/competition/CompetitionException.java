package competition;

/**
 * Exception to be thrown when a player fails to load
 * <p>
 * Exit code mapping:
 * <p>
 * - 02 - Failed to load arguments
 * - 03 - An error has occured but the code is bad enough that we don't know what it is
 * <p>
 * - 04 - Player 1 failed to compile
 * - 05 - Player 2 failed to compile
 * - 06 - Player 3 failed to compile
 * - 07 - Player 4 failed to compile
 * - 08 - Player 1 failed to load class
 * - 09 - Player 2 failed to load class
 * - 10 - Player 3 failed to load class
 * - 11 - Player 4 failed to load class
 * - 12 - Player 1 used an illegal import
 * - 13 - Player 2 used an illegal import
 * - 14 - Player 3 used an illegal import
 * - 15 - Player 4 used an illegal import
 * - 16 - Player 1 doesn't implement the ActionControllerInterface
 * - 17 - Player 2 doesn't implement the ActionControllerInterface
 * - 18 - Player 3 doesn't implement the ActionControllerInterface
 * - 19 - Player 4 doesn't implement the ActionControllerInterface
 * <p>
 * - 29 - Player 1 Timed out during their turn
 * - 30 - Player 2 Timed out during their turn
 * - 31 - Player 3 Timed out during their turn
 * - 32 - Player 4 Timed out during their turn
 * - 33 - Player 1 Made an illegal move
 * - 34 - Player 2 Made an illegal move
 * - 35 - Player 3 Made an illegal move
 * - 36 - Player 4 Made an illegal move
 * - 37 - Player 1 Made an illegal move
 * - 38 - Player 2 Made an illegal move
 * - 39 - Player 3 Made an illegal move
 * - 40 - Player 4 Made an illegal move
 * <p>
 * - 40 - InterruptionException during player decision
 */
public class CompetitionException extends RuntimeException {

    final int exitCode;

    public CompetitionException(String message, int exitCode) {
        super(message);
        this.exitCode = exitCode;
    }

    public int getExitCode() {
        return exitCode;
    }
}
