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
 * - 16 - Player 1 doesn't implement the ActionControllerInterface
 * - 17 - Player 2 doesn't implement the ActionControllerInterface
 * - 18 - Player 3 doesn't implement the ActionControllerInterface
 * - 19 - Player 4 doesn't implement the ActionControllerInterface
 * <p>
 * - 28 - Player 1 Timed out during their turn
 * - 29 - Player 2 Timed out during their turn
 * - 30 - Player 3 Timed out during their turn
 * - 31 - Player 4 Timed out during their turn
 * - 32 - Player 1 Made an illegal move
 * - 33 - Player 2 Made an illegal move
 * - 34 - Player 3 Made an illegal move
 * - 35 - Player 4 Made an illegal move
 * - 36 - Player 1 ran out of memory on their turn
 * - 37 - Player 2 ran out of memory on their turn
 * - 38 - Player 3 ran out of memory on their turn
 * - 39 - Player 4 ran out of memory on their turn
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
